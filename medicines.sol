pragma solidity ^0.4.21;
contract Medicine {

 
    mapping(address => string) public medicines;
  

    function addmedicines (string memory medicine) public {
       medicines[msg.sender] = medicine;
    }
    
    function getmedicines () public {
        
        return medicines[msg.sender]
    }

}