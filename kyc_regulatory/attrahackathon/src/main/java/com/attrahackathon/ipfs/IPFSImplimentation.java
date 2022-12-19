package com.attrahackathon.ipfs;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multiaddr.MultiAddress;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.attrahackathon.request.KycRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IPFSImplimentation {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	IPFS ipfs = null;
	public IPFS getIPSFObject() {
		try{
			ipfs = new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001/"));
		} catch(Exception e) {
			LOGGER.error("Error in IPFS service runner----->"+e.toString());
		}
		return ipfs;
	}
	
	public Multihash saveFileUsingByteArray(KycRequest kycRequest) throws IOException {
		if (ipfs == null) {
			ipfs = getIPSFObject();
		}
		ObjectMapper Obj = new ObjectMapper(); 
		String jsonStr = Obj.writeValueAsString(kycRequest);
		byte[] contents = jsonStr.getBytes();
		String fileName = "kycData.txt";
		NamedStreamable raw = new NamedStreamable.ByteArrayWrapper(fileName, contents);
		List<MerkleNode> addResult = ipfs.add(raw);
		return addResult.get(0).hash;
	}
	
	private void saveFile(IPFS ipfs) throws IOException {
		NamedStreamable file = new NamedStreamable.FileWrapper(new File("C:\\Taufik\\hello.txt"));
		List<MerkleNode> addResult = ipfs.add(file);
		Multihash pointer = addResult.get(0).hash;
	}
	
	public KycRequest getData(String hexCode) throws IOException {
		Multihash filePointer = Multihash.fromBase58(hexCode);
		if (ipfs == null) {
			ipfs = getIPSFObject();
		}
		byte[] contents = ipfs.cat(filePointer);
		byte[] decode = DatatypeConverter.parseBase64Binary(DatatypeConverter.printBase64Binary(contents));
		KycRequest kycRequest = new ObjectMapper().readValue(new String(decode,"UTF-8"), KycRequest.class);
		return kycRequest;
	}
	
	/*
	public static void main(String[] args) throws IOException {
		IPFS ipfs = new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/8080/"));
		saveFileUsingByteArray(null);
	}*/

}
