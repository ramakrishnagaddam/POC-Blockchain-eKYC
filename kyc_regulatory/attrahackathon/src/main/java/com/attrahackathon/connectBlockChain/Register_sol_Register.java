package com.attrahackathon.connectBlockChain;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class Register_sol_Register extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610387806100326000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80635a9b0b89146100515780638da5cb5b1461006b5780639cbc87a31461008f578063ed56900f146101a7575b600080fd5b6100596101c6565b60408051918252519081900360200190f35b6100736101cc565b604080516001600160a01b039092168252519081900360200190f35b610132600480360360208110156100a557600080fd5b8101906020810181356401000000008111156100c057600080fd5b8201836020820111156100d257600080fd5b803590602001918460208302840111640100000000831117156100f457600080fd5b9190808060200260200160405190810160405280939291908181526020018383602002808284376000920191909152509295506101db945050505050565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561016c578181015183820152602001610154565b50505050905090810190601f1680156101995780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101c4600480360360208110156101bd57600080fd5b5035610320565b005b60015490565b6000546001600160a01b031681565b60608082516020026040519080825280601f01601f19166020018201604052801561020d576020820181803883390190505b5090506000805b84518110156102995760005b60208110156102905760008160080260020a87848151811061023e57fe5b60209081029190910101510290506001600160f81b0319811615610287578085858151811061026957fe5b60200101906001600160f81b031916908160001a9053506001840193505b50600101610220565b50600101610214565b506060816040519080825280601f01601f1916602001820160405280156102c7576020820181803883390190505b50905060005b82811015610317578381815181106102e157fe5b602001015160f81c60f81b8282815181106102f857fe5b60200101906001600160f81b031916908160001a9053506001016102cd565b50949350505050565b60018190556040805182815290517f04269fac0e9720ef5744e835021906ade21efc145e319694e90a5e807275573e9181900360200190a15056fea165627a7a72305820451cd5ef504bde184ae22a25dcaee179d7d4d5dda55570c671617582005f14710029";

    public static final String FUNC_GETINFO = "getInfo";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_BYTES32ARRAYTOSTRING = "bytes32ArrayToString";

    public static final String FUNC_SETINFO = "setInfo";

    public static final Event INFOCHANGED_EVENT = new Event("InfoChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    @Deprecated
    protected Register_sol_Register(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Register_sol_Register(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Register_sol_Register(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Register_sol_Register(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<byte[]> getInfo() {
        final Function function = new Function(FUNC_GETINFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> bytes32ArrayToString(List<byte[]> data) {
        final Function function = new Function(FUNC_BYTES32ARRAYTOSTRING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.datatypes.generated.Bytes32.class,
                        org.web3j.abi.Utils.typeMap(data, org.web3j.abi.datatypes.generated.Bytes32.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setInfo(byte[] _info) {
        final Function function = new Function(
                FUNC_SETINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_info)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<InfoChangedEventResponse> getInfoChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(INFOCHANGED_EVENT, transactionReceipt);
        ArrayList<InfoChangedEventResponse> responses = new ArrayList<InfoChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InfoChangedEventResponse typedResponse = new InfoChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._info = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<InfoChangedEventResponse> infoChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, InfoChangedEventResponse>() {
            @Override
            public InfoChangedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(INFOCHANGED_EVENT, log);
                InfoChangedEventResponse typedResponse = new InfoChangedEventResponse();
                typedResponse.log = log;
                typedResponse._info = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<InfoChangedEventResponse> infoChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INFOCHANGED_EVENT));
        return infoChangedEventFlowable(filter);
    }

    @Deprecated
    public static Register_sol_Register load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Register_sol_Register(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Register_sol_Register load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Register_sol_Register(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Register_sol_Register load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Register_sol_Register(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Register_sol_Register load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Register_sol_Register(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Register_sol_Register> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Register_sol_Register.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Register_sol_Register> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Register_sol_Register.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Register_sol_Register> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Register_sol_Register.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Register_sol_Register> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Register_sol_Register.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class InfoChangedEventResponse {
        public Log log;

        public byte[] _info;
    }
}
