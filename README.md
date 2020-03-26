# CoronaGo - Flask API

We all know how much the world is suffering from COVID-19 as of now with lakhs infected and thousands deeased with the numbers rising everyday. We took an initative of building an application which can help in dealing with this problem even in any samllest way possible. 

This application can address follwing mentioned issues :

**Finding a person who is Quarantined or Positive** : One main problem faced in these days is if a person is given orders to self-isolate/qurantine themself , they still roam out even by hiding the quarantine stamps put on their hands, so just by scanning a person's face(clicking there picture) it can be found that if they are quarantined/positive/normal. Only trusted authorities(doctors) can mark a person quarantined/positive/normal through there accounts. This will help normal citizens or police in finding out if a person is quarantined/positive/normal very easily. 

**Reducing the physical contact** : We usually see the doctors give out prescription to patients on a paper and that paper goes through various hands and touches various surfaces which can lead to infection to a healthy person from unhealthy one. So whenever the doctor enters a medicine in prescription , it gets stored on blockchain ( its benefit discussed below). And whenever a patient goes to a chemist , chemist can know the prescriptions just by scanning the persons face and provide them with medicines without the need of any physical contact. The Payment can also be made by just scanning the face , no need of touching the currency. And everything is safe , as is it decentralised, Thanks to blockchain!.

**A new hands-free Payment System** : As discussed above , there is a new payments system developed which works by just scanning a persons a face to pay them. And this whole system is decentralised on ethereum. So every transaction is recorded and is verified. 

**An informative chat-bot** : This app also includes a chat-bot which can answer your questions regarding any doubts related to COVID-19. 

**Current Situation Dashboard** :  It features a dashboard which gives current situation of Infected/Cured/Deceased Persons.

All the above issues are addressed in the Application in a very systematic, clear and a safe manner using the below mentioned Technologies:
- Flask
- Ganache (Ethereum Blockchain)
- Docker
- AWS
- Android Studio 
- Computer Vision
- Azure 
- Dialogflow


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


## Contents 
* [End Users](#end-users-)
* [Modules](#modules-)

## End Users [&uarr;](#contents)
- There are three kind of end users of the application
- **Chemist** 
- **Doctor**
- **Citizen**

## Modules [&uarr;](#contents)
### Live COVID-19 Global Status
- Live updates on the statistics related to COVID-19 cases worldwide.
- Accessible by anyone, no need for signin.

### Quarantine Check
- Face scanner based check the status of a person: If a person is (corona positive/ meant to be quarantined/ normal).
- Accessible by anyone, no need for signin.

### Visual Base Authentication
- Face scanner based authentications and login.
- Can login with email and password too.

### Face Recognition based payment system
- Online Wallet system based on face recognition

### Prescription Module based on Face Recognition
- Doctors can add prescription for a patient, also they can change status of a person as  positive/ meant to be quarantined/ normal.
- Chemists can view the prescription of a particular patient by face recognition

## Contributors and maintainers [&uarr;](#contents)

This project and repository is created and maintained by:

### Project created under the [HackCovid19](https://hackcovid19.devfolio.co) hackathon

### Team name: codeonkailash
### Team members:

* **Yuvraj Dalia (Team Lead)**

 Email: yuvrajdalia98@gmail.com
    
 Github: [yuvrajdalia](https://github.com/yuvrajdalia)

* **Manas Gupta**

 Email: manasgupta1109@gmail.com
    
 Github: [manas11](https://github.com/manas11)
    
* **Vithik Shah**

 Email: vithikshah@gmail.com
    
 Github: [VithikShah](https://github.com/VithikShah)

