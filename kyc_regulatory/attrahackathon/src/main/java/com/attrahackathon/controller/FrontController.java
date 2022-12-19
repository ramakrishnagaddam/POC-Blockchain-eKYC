package com.attrahackathon.controller;


import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;

import com.attrahackathon.connectBlockChain.ConnectBlockchainService;
import com.attrahackathon.ipfs.IPFSImplimentation;
import com.attrahackathon.repository.KycHexMappingRepository;
import com.attrahackathon.repository.KycRepository;
import com.attrahackathon.request.IdType;
import com.attrahackathon.request.KycMapping;
import com.attrahackathon.request.KycRequest;
import com.attrahackathon.request.Response;

import io.ipfs.multihash.Multihash;


@CrossOrigin("*")
@RestController
@RequestMapping("/hackathon/ekyc")
public class FrontController {
	
	@Autowired
	private IPFSImplimentation ipfsImplimentation;
	
	@Autowired
	private KycRepository kycRepository;
	
	@Autowired
	private KycHexMappingRepository kycHexMappingRepository;
	
	@Autowired
	private ConnectBlockchainService connectBlockchainService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * It will be called after USER/REG-BODY/BANK submits KYC requests
	 * @param kycRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/submiteKyc")
	public ResponseEntity<Response<String>> executeSubmiteKyc(@RequestBody KycRequest kycRequest) throws Exception {
		LOGGER.info("Inside Submit KYC");
		Response<String> response = new Response<>();
		for (IdType idType : kycRequest.getIdType()) {
			if (idType.getType().equalsIgnoreCase("AADHAR")) {
				kycRequest.setPrimaryIdNumber(idType.getIdNumber());
				kycRequest.setPrimaryIdType(idType.getType());
			} 
		}
		Optional<KycMapping> kycMapping = kycHexMappingRepository.findById(kycRequest.getPrimaryIdNumber());
		Optional<KycRequest> storedKyc = kycRepository.findById(kycRequest.getPrimaryIdNumber());
		if(kycMapping.isPresent()) {
			response.setData("KYC for this user is already done. Please get the token details from customer");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}
		if (!storedKyc.isPresent()) {
			kycRequest.setState("PENDING");
			kycRepository.save(kycRequest);
			response.setData("Your eKYC request is sent for verification");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}
		response.setData("Request forwarded to verification officer for validation.");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
	
	/**
	 * 
	 * @param kycRequest
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/verifyKyc/{status}")
	public ResponseEntity<Response<String>> executeVerifyKyc(@RequestBody KycRequest kycRequest, @PathVariable String status) throws Exception {
		Response<String> response = new Response<>();
		LOGGER.info("Inside VERIFY KYC");
		// For getting kyc object of the user please refer the primaryIdNumber and primaryIdType
		Optional<KycRequest> storedKyc = kycRepository.findById(kycRequest.getPrimaryIdNumber());
		if (status.equalsIgnoreCase("APPROVED")) {
			Multihash multiHash = ipfsImplimentation.saveFileUsingByteArray(storedKyc.get());
			KycMapping kycMapping = new KycMapping();
			kycRepository.deleteById(kycRequest.getPrimaryIdNumber());
			
			//call blockchainservice for setting information
			BigInteger blockNumber = connectBlockchainService.setInfo(kycRequest.getPrimaryIdNumber(), "abcd@123", multiHash.toString());
			kycMapping.setBlockNumber(blockNumber);
			kycMapping.setUniqueHex(String.valueOf( Math.round(Math.random()*1000000 ))); // Need to encrypt it.
			kycMapping.setUniqueHexCode(multiHash.toBase58());
			kycHexMappingRepository.save(kycMapping);
			response.setData(kycMapping.getUniqueHex());
		} else if (status.equalsIgnoreCase("REJECTED")) {	
			storedKyc.get().setStatus("REJECTED");
			kycRepository.save(storedKyc.get());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getAllUnverified")
	public ResponseEntity<Response<List<KycRequest>>> executeGetAllUnverifiedKyc() throws Exception {
		LOGGER.info("Inside get All unverified data");
		Response<List<KycRequest>> response = new Response<>();
		response.setData(kycRepository.findAll());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * 
	 * @param hexCode
	 * @return
	 * @throws Exception
	 */
	@PostMapping("getDataUsingHex/{id}")
	public ResponseEntity<Response<KycRequest>> executeGetDataUsingHex(@PathVariable String id) throws Exception {
		LOGGER.info("Inside get All unverified data");
		Response<KycRequest> response = new Response<>();
		KycMapping data = kycHexMappingRepository.findById(id).get();
		response.setData(ipfsImplimentation.getData(data.getUniqueHexCode()));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
	
	@PostMapping("saveHexToBlockChain/{hexCode}")
	public KycRequest executeSaveDataInBlockChain(@PathVariable String hexCode) throws Exception {
		connectBlockchainService.setInfo("1234567890", "abcd@123", hexCode);
		return null;
	}
	
	@PostMapping("getBlockData/{blockNumber}")
	public ResponseEntity<Response<TransactionResult>> executeGetBlockData(@PathVariable String blockNumber) throws Exception {
		Response<TransactionResult> response = new Response<>();
		response.setData(connectBlockchainService.getBlockData(new BigInteger(blockNumber)));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
	
	@PostMapping("getLatestBlock")
	public String executeGetLatestBlock() throws Exception {
		return connectBlockchainService.getLatestBlock();
	}
	
}
