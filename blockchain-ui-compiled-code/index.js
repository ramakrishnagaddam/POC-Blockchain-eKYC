var web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:8545"));

var defaultAccount;
var contractAddress = "0x3c537e01317d9dd6792be803659d60aee2bfdb24";
var abi = JSON.parse('[ { "constant": true, "inputs": [], "name": "getInfo", "outputs": [ { "name": "", "type": "bytes32" } ], "payable": false, "stateMutability": "view", "type": "function" }, { "constant": true, "inputs": [], "name": "owner", "outputs": [ { "name": "", "type": "address" } ], "payable": false, "stateMutability": "view", "type": "function" }, { "constant": false, "inputs": [ { "name": "_info", "type": "bytes32" } ], "name": "setInfo", "outputs": [], "payable": false, "stateMutability": "nonpayable", "type": "function" }, { "inputs": [], "payable": false, "stateMutability": "nonpayable", "type": "constructor" }, { "anonymous": false, "inputs": [ { "indexed": false, "name": "_info", "type": "bytes32" } ], "name": "InfoChanged", "type": "event" } ]');

RegisterContract = web3.eth.contract(abi);
Register = RegisterContract.at(contractAddress);

// Checks if exists accounts and gets the first one
web3.eth.getAccounts(function(err, accounts) {
    if (err != null) {
        //alert("Error searching accounts.");
        console.log("Error searching accounts.");
        return;
    }

    if (accounts.length == 0) {
        //alert("No account! Check if Ethereum client is set correctly.");
        console.log("No account! Check if Ethereum client is set correctly.");
        return;
    }

    //console.log(accounts);
    defaultAccount = accounts[0];
    web3.eth.defaultAccount = accounts[0];
});

// Changes the info data on deployed contract
function newRegister(event) {
    event.preventDefault();

    var info = event.target.elements['info'].value;
    Register.setInfo(info, {from: defaultAccount, gas: 1000000});
    
    event.target.elements['info'].value = '';
    event.target.elements['info'].focus(); 
}

// Working with events
var events = [];

// Gets the InfoChanged event history
Register
    .InfoChanged({}, {fromBlock: 0, toBlock: 'latest'})
    .get((error, history) => {
        if (! error) {
            history.forEach(function(event) {
                events.push({
                    blockNumber: event.blockNumber,
                    date: getDateFromBlock(event.blockNumber),
                    account: event.args._sender,
                    info: event.args._info
                });
                updateTableBody(events);
            });
        } else {
            console.log(error);
        }
    });

// Watches the event InfoChanged
var infoEvent = Register.InfoChanged();
infoEvent.watch((error, result) => {
    if (! error) {
        events.push({
            blockNumber: result.blockNumber,
            date: getDateFromBlock(result.blockNumber),
            account: result.args._sender,
            info: result.args._info
        });
        updateTableBody(events);
    } else {
        console.log(error);
    }
});

// Returns the approximate date and time
function getDateFromBlock(blockNumber) {
    var timestamp = web3.eth.getBlock(blockNumber).timestamp;
    var date = new Date(timestamp * 1000);

    return date.toLocaleString();
}

// Updates the table
function updateTableBody(events) {
    var tableBody = document.querySelector("#transactions");
    tableBody.innerHTML = renderedTableLines(events);
}

// Just a facility
function renderedTableLines(events) {
    return events.map(event => `
        <tr>
            <td>${event.blockNumber}</td>
            <td>${event.date}</td>
            <td>${event.account}</td>
            <td>${event.info}</td>
        </tr>
    `).join("");
}