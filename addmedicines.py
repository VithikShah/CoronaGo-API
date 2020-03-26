import json
from web3 import Web3
ganache_url = "http://ec2-54-175-197-129.compute-1.amazonaws.com:8545"
web3 = Web3(Web3.HTTPProvider(ganache_url))
abi = json.loads('[{"constant":false,"inputs":[{"name":"medicine","type":"string"}],"name":"addmedicines","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"stat","type":"int256"}],"name":"updatestatus","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"getmedicines","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"getstatus","outputs":[{"name":"","type":"int256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"medicines","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"status","outputs":[{"name":"","type":"int256"}],"payable":false,"stateMutability":"view","type":"function"}]')
address=web3.toChecksumAddress("0xC70003Ef71fdEa97A785183615B871A99cFB73b7")
contract = web3.eth.contract(address=address, abi=abi)
publickey = str(input("Enter public key"))
web3.eth.defaultAccount = publickey
s = contract.functions.getmedicines().call()
print(s)
med = str(input('Enter medicines'))
tx_hash = contract.functions.addmedicines(med).transact()
web3.eth.waitForTransactionReceipt(tx_hash)
s = contract.functions.getmedicines().call()
print(s)

publickey = str(input("Enter public key"))
web3.eth.defaultAccount = publickey
s = contract.functions.getstatus().call()
print(s)
med = int(input('Enter status'))
tx_hash = contract.functions.updatestatus(med).transact()
web3.eth.waitForTransactionReceipt(tx_hash)
s = contract.functions.getstatus().call()
print(s)
