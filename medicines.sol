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



pragma solidity ^0.4.21;
contract Medicine {

 
    mapping(address => string) public medicines;
    mapping(address => uint) public status;
    

    function addmedicines (string memory medicine) public {
       medicines[msg.sender] = medicine;
    }
    
    function updatestatus (uint stat) public {
        medicines[msg.sender] = stat;
    }
    
    
    function getmedicines () public {
        return medicines[msg.sender]
    }
    
    function getstatus () public {
        return status[msg.sender]
    }


}
