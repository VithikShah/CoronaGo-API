import asyncio
import io
import glob
import os
import sys
import time
import uuid
import requests
import sqlite3
from urllib.parse import urlparse
from io import BytesIO
from PIL import Image, ImageDraw
from azure.cognitiveservices.vision.face import FaceClient
from msrest.authentication import CognitiveServicesCredentials
from azure.cognitiveservices.vision.face.models import TrainingStatusType, Person, SnapshotObjectType, OperationStatusType
from global_variables import personGroupId
from web3 import Web3


KEY = '2698a28d0b3a47be9a0177011b4fca38'
# Replace with your regional Base URL
ENDPOINT = 'https://hackcovid.cognitiveservices.azure.com/'

face_client = FaceClient(ENDPOINT, CognitiveServicesCredentials(KEY))

group_photo = 'test.jpg'
PARENT_FOLDER = os.path.join(os.path.dirname(os.path.realpath(__file__)))
IMAGES_FOLDER = os.path.join(PARENT_FOLDER, 'images')
# Get test image
test_image_array = glob.glob(os.path.join(IMAGES_FOLDER, group_photo))
image = open(test_image_array[0], 'r+b')

# Detect faces
face_ids = []
faces = face_client.face.detect_with_stream(image)
for face in faces:
    face_ids.append(face.face_id)

print(face_ids)
# Identify faces
results = face_client.face.identify(face_ids, personGroupId)
print('Identifying faces in {}'.format(os.path.basename(image.name)))
if not results:
    print('No person identified in the person group for faces from {}.'.format(
        os.path.basename(image.name)))
res = 0
for person in results:
    print('Person for face ID {} is identified in {} with a confidence of {}.'.format(person.face_id,
                                                                                      os.path.basename(image.name), person.candidates[0].confidence))  # Get topmost confidence score
    print(person.candidates[0].person_id)
    res = person.candidates[0].person_id

connect = sqlite3.connect("Face-DataBase")
c = connect.cursor()
c.execute("SELECT * FROM Students WHERE personID = ?", (res,))
row = c.fetchone()
print(row[1] + " recognized")
if(row[8] == 0):
    print("Is Not Quarantined")
else:
    print("Is Quarantined")
if(row[9] == 0):
    print("Is Doctor")
elif (row[9] == 1):
    print("Is Chemist")
else:
    print("Is Citizen")
c = connect.cursor()
c.execute("SELECT * FROM Students WHERE personID = ?", (res,))
row = c.fetchone()

account_1 = '0x4c494B4b07C2feb7819eB59FA3E98D922c2Cf017'
account_2 = row[4]
private_key = str(input('Enter Private Key'))
print('private_key', private_key)

amount = int(input('Enter Ethers'))
print(amount)
connect.commit()
connect.close()
ganache_url = "http://127.0.0.1:7545"
web3 = Web3(Web3.HTTPProvider(ganache_url))
nonce = web3.eth.getTransactionCount(account_1)
print(nonce)
tx = {
    'nonce': nonce,
    'to': account_2,
    'value': web3.toWei(amount, 'ether'),
    'gas': 2000000,
    'gasPrice': web3.toWei('50', 'gwei'),
}

signed_tx = web3.eth.account.signTransaction(tx, private_key)


tx_hash = web3.eth.sendRawTransaction(signed_tx.rawTransaction)
print(web3.toHex(tx_hash))

