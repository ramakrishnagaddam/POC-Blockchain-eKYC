var registerContract = web3.eth.contract([{"constant":true,"inputs":[],"name":"getInfo","outputs":[{"name":"","type":"bytes32"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"owner","outputs":[{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_info","type":"bytes32"}],"name":"setInfo","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"inputs":[],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"name":"_info","type":"bytes32"}],"name":"InfoChanged","type":"event"}]);
var register = registerContract.new(
   {
     from: web3.eth.accounts[0], 
     data: '0x608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506101af806100606000396000f3fe608060405260043610610051576000357c0100000000000000000000000000000000000000000000000000000000900480635a9b0b89146100565780638da5cb5b14610081578063ed56900f146100d8575b600080fd5b34801561006257600080fd5b5061006b610113565b6040518082815260200191505060405180910390f35b34801561008d57600080fd5b5061009661011d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100e457600080fd5b50610111600480360360208110156100fb57600080fd5b8101908080359060200190929190505050610142565b005b6000600154905090565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b806001819055507f04269fac0e9720ef5744e835021906ade21efc145e319694e90a5e807275573e816040518082815260200191505060405180910390a15056fea165627a7a72305820124bd10c517049b81ef12265d53793c13f0c6431a811f14f8c5bd4f8d5e7d1040029', 
     gas: '4700000'
   }, function (e, contract){
    console.log(e, contract);
    if (typeof contract.address !== 'undefined') {
         console.log('Contract mined! address: ' + contract.address + ' transactionHash: ' + contract.transactionHash);
    }
 })