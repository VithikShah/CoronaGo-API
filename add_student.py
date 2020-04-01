# openCV
import cv2
# for numpy arrays
import numpy as np
import sqlite3
import dlib
import hashlib
# for creating folders
import os
cap = cv2.VideoCapture('./ab.mp4')
detector = dlib.get_frontal_face_detector()


# this function is for database
def insertOrUpdate(Id, Name, roll, persontype, email, passhash):
    # connecting to the database
    connect = sqlite3.connect("Face-DataBase")
    # selecting the row of an id into consideration
    cmd = "SELECT * FROM Students WHERE ID = " + Id
    cursor = connect.execute(cmd)
    isRecordExist = 0
    # checking wheather the id exist or not
    for row in cursor:
        isRecordExist = 1
    if isRecordExist == 1:                                                      # updating name and roll no
        connect.execute(
            "UPDATE Students SET Name = ? WHERE ID = ?", (Name, Id))
        connect.execute(
            "UPDATE Students SET Roll = ? WHERE ID = ?", (roll, Id))
        connect.execute(
            "UPDATE Students SET perswontype = ? WHERE ID = ?", (persontype, Id))
        connect.execute(
            "UPDATE Students SET email = ? WHERE ID = ?", (email, Id))
        connect.execute(
            "UPDATE Students SET passowrd = ? WHERE ID = ?", (passhash, Id))
        connect.execute(
            "UPDATE Students SET quarantine = ? WHERE ID = ?", (0, Id))

    else:
        # insering a new student data
        params = (Id, Name, roll, persontype, email, passhash, 0)
        connect.execute(
            "INSERT INTO Students(ID, Name, Roll,perswontype,email,passowrd,quarantine) VALUES(?, ?, ?, ?, ?, ?, ?)", params)
    # commiting into the database
    connect.commit()
    # closing the connection
    connect.close()


name = input("Enter  name : ")
roll = input("Enter Unique id : ")
print("Citizen - 0")
print("Chemist - 1")
print("Doctor - 2")
print("Person Type")
persontype = int(input())
email = input("Enter  email : ")
password = input("Enter  password : ")
hashcode = hashlib.md5(password.encode())
passhash = hashcode.hexdigest()
Id = roll[-2:]
# calling the sqlite3 database
insertOrUpdate(Id, name, roll, persontype, email, passhash)


# creating the person or user folder
folderName = "user" + Id
folderPath = os.path.join(os.path.dirname(
    os.path.realpath(__file__)), "dataset/"+folderName)
if not os.path.exists(folderPath):
    os.makedirs(folderPath)

sampleNum = 0
while(True):
    # reading the camera input
    ret, img = cap.read()
    # Converting to GrayScale
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    dets = detector(img, 1)
    # loop will run for each face detected
    for i, d in enumerate(dets):
        sampleNum += 1
        cv2.imwrite(folderPath + "/User." + Id + "." + str(sampleNum) + ".jpg",
                    img[d.top():d.bottom(), d.left():d.right()])                                                # Saving the faces
        cv2.rectangle(img, (d.left(), d.top()), (d.right(),
                                                 d.bottom()), (0, 255, 0), 2)  # Forming the rectangle
        # waiting time of 200 milisecond
        cv2.waitKey(200)
    # showing the video input from camera on window
    cv2.imshow('frame', img)
    cv2.waitKey(1)
    if(sampleNum >= 20):                                                        # will take 20 faces
        break

# turning the webcam off
cap.release()
# Closing all the opened windows
cv2.destroyAllWindows()
