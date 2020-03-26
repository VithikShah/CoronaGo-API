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


KEY = '2698a28d0b3a47be9a0177011b4fca38'
ENDPOINT = 'https://hackcovid.cognitiveservices.azure.com/'  # Replace with your regional Base URL

face_client = FaceClient(ENDPOINT, CognitiveServicesCredentials(KEY))



res = face_client.person_group.create('test2', "cvproject")
print(res.person_id)
print(res.person_id)

