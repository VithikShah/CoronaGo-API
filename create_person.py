import sqlite3
import asyncio
import io
import glob
import os
import asyncio
import io
import glob
import os
import sys
import time
import json
import uuid
import http
import http.client, urllib.request, urllib.parse, urllib.error, base64
import requests
from urllib.parse import urlparse
from io import BytesIO
from PIL import Image, ImageDraw
from azure.cognitiveservices.vision.face import FaceClient
from msrest.authentication import CognitiveServicesCredentials
from azure.cognitiveservices.vision.face.models import TrainingStatusType, Person, SnapshotObjectType, OperationStatusType
from global_variables import personGroupId
import sys
import time
import uuid
import requests
from urllib.parse import urlparse
from io import BytesIO
from PIL import Image, ImageDraw
from azure.cognitiveservices.vision.face import FaceClient
from msrest.authentication import CognitiveServicesCredentials
from azure.cognitiveservices.vision.face.models import TrainingStatusType, Person, SnapshotObjectType, OperationStatusType
from global_variables import personGroupId



# KEY = '2cfe4243d20342c2b49aeda55a4647f8'
# ENDPOINT = 'https://faceapi0811.cognitiveservices.azure.com/'  # Replace with your regional Base URL

# face_client = FaceClient(ENDPOINT, CognitiveServicesCredentials(KEY))
if len(sys.argv) is not 1:
	headers = {
    # Request headers
    'Content-Type': 'application/json',
    'Ocp-Apim-Subscription-Key': '2698a28d0b3a47be9a0177011b4fca38',
	}
	body = {}
	body['name'] = 'Person1'

	params = urllib.parse.urlencode({

	})
	conn = http.client.HTTPSConnection('southeastasia.api.cognitive.microsoft.com')
	# conn.request("POST", "/face/v1.0/persongroups/test1/persons?%s" % params, payload, headers)
	conn.request("POST", "/face/v1.0/persongroups/test2/persons" ,json.dumps(body) , headers)

	response = conn.getresponse()
	resp = response.read()
	res= json.loads(resp)
	print(res)
	extractId = str(sys.argv[1])[-2:]
	publickey=input('Enter the public key')
	privatekey=input('Enter the private key')
	# print(res['personId'])
	connect = sqlite3.connect("Face-DataBase")
	cmd = "SELECT * FROM Students WHERE ID = " + extractId
	cursor = connect.execute(cmd)
	isRecordExist = 0
	for row in cursor:                                                          # checking wheather the id exist or not
	    isRecordExist = 1
	if isRecordExist == 1:                                                      # updating name and roll no
	    connect.execute("UPDATE Students SET personID = ?, Publickey = ?, Privatekey = ?  WHERE ID = ?",(res['personId'], publickey,privatekey, extractId))
	connect.commit()                                                            # commiting into the database
	connect.close()
	print("Person ID successfully added to the database")
