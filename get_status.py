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


res = face_client.person_group.get_training_status(personGroupId)
print(res)
