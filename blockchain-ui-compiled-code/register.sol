pragma solidity ^0.5.7;

contract Register {

    address public owner;
    bytes32 private info;

    event InfoChanged(bytes32 _info);

    constructor() public {
        owner = msg.sender;
    }

    function setInfo(bytes32 _info) public {
        info = _info;
        emit InfoChanged(_info);
    }

    function getInfo() public view returns (bytes32) {
        return info;
    }
    
    function bytes32ArrayToString (bytes32[] memory data) public pure returns (string memory) {
        bytes memory bytesString = new bytes(data.length * 32);
        uint urlLength;
        for (uint i=0; i<data.length; i++) {
            for (uint j=0; j<32; j++) {
                byte char = byte(bytes32(uint(data[i]) * 2 ** (8 * j)));
                if (char != 0) {
                    bytesString[urlLength] = char;
                    urlLength += 1;
                }
            }
        }
        bytes memory bytesStringTrimmed = new bytes(urlLength);
        for (uint i=0; i<urlLength; i++) {
            bytesStringTrimmed[i] = bytesString[i];
        }
        return string(bytesStringTrimmed);
    }
}