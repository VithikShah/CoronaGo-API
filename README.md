# PayBlocks - Data Storage and smart payments using Blockchain

- Step 1: Training the model 
For this you will need to add face data to the cognitive services . You will need to change the end point and the provided key the in code to run it on your systems ./
a.First run the file add_student.py , and add the details . Pictures will be clicked during this time ./
b.Then run the file create_person_group.py . this will create a person group , in our case it was test1 . 
c.Now run create_person.py along with agrument like User78 ( which is created folder of face data in dataset folder) , to create a person the local as well as cloud database . You also be prompted to enter public key of the ganache blockchain systems at this stage .
d. Now run add_person_faces.py to add the faces in Face API . 
e. Now run train.py to train the model .

Now all the face data is fed to the API and model is train to identify 

- Step2 : 


For this step you need to have flask installed in your system as we will be creating Flask API for the android app .
a. Run identify_api.py to set up the API server . 

Also you need to host your localhost on web using Pagekite like platforms , as it will support our blockchain based platform . 


- Step3 :

a.Next you need to run the apk in your mobile from anroid studio . 
b. Now the person needs to click his/her picture to login .
c. Now you will have options such as pay someone or buy medicines . 
d. You may choose anyone of them to proceed further . These are blockchain based features 


This project was done in HackVerse by :
Vithik Shah
Manas Gupta 
Yuvraj Dalia ( Team Admin)
