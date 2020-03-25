import sys
import os, time
from global_variables import personGroupId
import urllib
import sqlite3
import asyncio
import io
import glob
import os
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


KEY = '2cfe4243d20342c2b49aeda55a4647f8'
ENDPOINT = 'https://faceapi0811.cognitiveservices.azure.com/'  # Replace with your regional Base URL

face_client = FaceClient(ENDPOINT, CognitiveServicesCredentials(KEY))

def get_person_id():
	person_id = ''
	extractId = str(sys.argv[1])[-2:]
	connect = sqlite3.connect("Face-DataBase")
	c = connect.cursor()
	cmd = "SELECT * FROM Students WHERE ID = " + extractId
	c.execute(cmd)
	row = c.fetchone()
	person_id = row[3]
	connect.close()
	return person_id

if len(sys.argv) is not 1:
    images = glob.glob('/home/yuvi/projects/cv/Autoattendance-Cognitive/dataset/'+ str(sys.argv[1]) + '/*.jpg')
    person_id = get_person_id()
    for image in images:
        w = open(image,'r+b')
        print("reading")
        result = face_client.person_group_person.add_face_from_stream(personGroupId, person_id, w)
        print(result)	
        time.sleep(6)
