from flask import Flask, redirect, url_for,render_template,request, jsonify
import flask
import asyncio
import io
import glob
import os
from flask_uploads import UploadSet, configure_uploads, IMAGES, patch_request_class
from flask_wtf import FlaskForm
from flask_wtf.file import FileField, FileRequired, FileAllowed
from wtforms import SubmitField
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
import werkzeug
import json

app = Flask(__name__)
basedir = os.path.abspath(os.path.dirname(__file__))
app.config["IMAGE_UPLOADS"] = "/home/yuvi/projects/cv/Autoattendance-Cognitive/uploads"
app.config["LOGIN"] = "/home/yuvi/projects/cv/Autoattendance-Cognitive/login"
app.config["IMG_MEDICINES"] = "/home/yuvi/projects/cv/Autoattendance-Cognitive/medicines"




@app.route('/identify', methods=['POST', 'GET'])
def identify():
	if request.method == 'POST' and request.files:
		imagex = request.files["image"]
		imagex.save(os.path.join(app.config["IMAGE_UPLOADS"], imagex.filename))

		KEY = '2698a28d0b3a47be9a0177011b4fca38'
		ENDPOINT = 'https://hackcovid.cognitiveservices.azure.com/'  # Replace with your regional Base URL

		face_client = FaceClient(ENDPOINT, CognitiveServicesCredentials(KEY))
		test_image_array = glob.glob(os.path.join(app.config["IMAGE_UPLOADS"], imagex.filename))		
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
		    print('No person identified in the person group for faces from {}.'.format(os.path.basename(image.name)))
		res=0
		for person in results:
		    print('Person for face ID {} is identified in {} with a confidence of {}.'.format(person.face_id, os.path.basename(image.name), person.candidates[0].confidence)) # Get topmost confidence score
		    print(person.candidates[0].person_id)
		    res=person.candidates[0].person_id

		print(res)
		connect = sqlite3.connect("Face-DataBase")
		c=connect.cursor()
		c.execute("SELECT * FROM Students WHERE personID = ?", (res,))
		row = c.fetchone()
		print(row[1] + " recognized")

		c=connect.cursor()
		c.execute("SELECT * FROM Students WHERE personID = ?", (res,))
		row = c.fetchone()

		account_1 = request.form.get('public')
		account_1 = account_1[1:-1]
		account_2 = row[4]

		private_key = request.form.get('private')
		private_key = private_key[1:-1]
		print('private_key', private_key)

		amount = int(request.form.get('amount'))
		print(amount)
		connect.commit() 
		connect.close()
		ganache_url = "http://127.0.0.1:8545"
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
		return jsonify({"name":row[1], "tx_hash": web3.toHex(tx_hash)})
	else:
		return render_template('index.html')

@app.route('/getpublickey', methods=['POST'])
def getpublickey():
	if request.method == 'POST' and request.files:
		imagex = request.files["image"]
		imagex.save(os.path.join(app.config["LOGIN"], imagex.filename))

		KEY = '2698a28d0b3a47be9a0177011b4fca38'
		ENDPOINT = 'https://hackcovid.cognitiveservices.azure.com/'  

		face_client = FaceClient(ENDPOINT, CognitiveServicesCredentials(KEY))
		test_image_array = glob.glob(os.path.join(app.config["LOGIN"], imagex.filename))		
		image = open(test_image_array[0], 'r+b')

		# Detect faces
		face_ids = []
		faces = face_client.face.detect_with_stream(image)
		for face in faces:
		    face_ids.append(face.face_id)

		print(face_ids)
		# Identify faces
		results = None
		if face_ids:
			results = face_client.face.identify(face_ids, personGroupId)
			print('Identifying faces in {}'.format(os.path.basename(image.name)))
		if not results:
		    print('No person identified in the person group for faces from {}.'.format(os.path.basename(image.name)))
		    return jsonify({"error":"none"})
		    return
		res=0
		for person in results:
		    print('Person for face ID {} is identified in {} with a confidence of {}.'.format(person.face_id, os.path.basename(image.name), person.candidates[0].confidence)) # Get topmost confidence score
		    print(person.candidates[0].person_id)
		    res=person.candidates[0].person_id

		print(res)
		connect = sqlite3.connect("Face-DataBase")
		c=connect.cursor()
		c.execute("SELECT * FROM Students WHERE personID = ?", (res,))
		row = c.fetchone()
		print(row[1] + " recognized")

		c=connect.cursor()
		c.execute("SELECT * FROM Students WHERE personID = ?", (res,))
		row = c.fetchone()

		return jsonify({"publickey":row[4]})

# @app.route('/getmedicines', methods=['POST'])

@app.route('/getmedicines', methods=['POST'])
def getmedicines():
	if request.method == 'POST' and request.files:
		imagex = request.files["image"]
		imagex.save(os.path.join(app.config["IMG_MEDICINES"], imagex.filename))

		KEY = '2698a28d0b3a47be9a0177011b4fca38'
		ENDPOINT = 'https://hackcovid.cognitiveservices.azure.com/'  

		face_client = FaceClient(ENDPOINT, CognitiveServicesCredentials(KEY))
		test_image_array = glob.glob(os.path.join(app.config["IMG_MEDICINES"], imagex.filename))		
		image = open(test_image_array[0], 'r+b')

		# Detect faces
		face_ids = []
		faces = face_client.face.detect_with_stream(image)
		for face in faces:
		    face_ids.append(face.face_id)

		print(face_ids)
		# Identify faces
		results = None
		if face_ids:
			results = face_client.face.identify(face_ids, personGroupId)
			print('Identifying faces in {}'.format(os.path.basename(image.name)))
		if not results:
		    print('No person identified in the person group for faces from {}.'.format(os.path.basename(image.name)))
		    return jsonify({"error":"none"})
		    return
		res=0
		for person in results:
		    print('Person for face ID {} is identified in {} with a confidence of {}.'.format(person.face_id, os.path.basename(image.name), person.candidates[0].confidence)) # Get topmost confidence score
		    print(person.candidates[0].person_id)
		    res=person.candidates[0].person_id

		print(res)
		connect = sqlite3.connect("Face-DataBase")
		c=connect.cursor()
		c.execute("SELECT * FROM Students WHERE personID = ?", (res,))
		row = c.fetchone()
		print(row[1] + " recognized")

		c=connect.cursor()
		c.execute("SELECT * FROM Students WHERE personID = ?", (res,))
		row = c.fetchone()
		publickey = row[4]
		ganache_url = "http://127.0.0.1:8545"
		web3 = Web3(Web3.HTTPProvider(ganache_url))
		abi = json.loads('[{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"medicines","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"medicine","type":"string"}],"name":"addmedicines","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"_getmedicines","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"}]')
		address=web3.toChecksumAddress("0x8Fe18Bf092473B98024166e7683ad3D9312DCB41")
		contract = web3.eth.contract(address=address, abi=abi)
		web3.eth.defaultAccount = publickey
		medicines = contract.functions._getmedicines().call()

		return jsonify({"medicines":str(medicines)})





if __name__ == '__main__':
   app.run(debug = True)
