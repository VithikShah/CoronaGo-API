import json
from web3 import Web3
ganache_url = "http://127.0.0.1:8545"
web3 = Web3(Web3.HTTPProvider(ganache_url))
abi = json.loads('[{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"medicines","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"medicine","type":"string"}],"name":"addmedicines","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"_getmedicines","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"}]')
address=web3.toChecksumAddress("0x8Fe18Bf092473B98024166e7683ad3D9312DCB41")
contract = web3.eth.contract(address=address, abi=abi)
publickey = str(input("Enter public key"))
web3.eth.defaultAccount = publickey
s = contract.functions._getmedicines().call()
print(s)
med = str(input('Enter medicines'))
tx_hash = contract.functions.addmedicines(med).transact()
web3.eth.waitForTransactionReceipt(tx_hash)
s = contract.functions._getmedicines().call()
print(s)