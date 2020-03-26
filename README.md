# CoronaGo  - Powered By Ethereum Blockchain 

We all know how much the world is suffering from COVID-19 as of now with lakhs infected and thousands deeased with the numbers rising everyday. We took an initative of building an application which can help in dealing with this problem even in any samllest way possible. 

## Starting the app

**For running the app download the apk-debug.apk and install it.**
- To login as a doctor, use images/test.jpg 
- To login as a chemist, use images/test1.jpg
- To login as a normal citizen, use images/test2.jpg

[More details about the android application](https://github.com/VithikShah/CoronaGo-API/tree/master/CoronaGo-Android)

This application can address follwing mentioned issues :

**Finding a person who is Quarantined or Positive** : One main problem faced in these days is if a person is given orders to self-isolate/qurantine themself , they still roam out even by hiding the quarantine stamps put on their hands, so just by scanning a person's face(clicking there picture) it can be found that if they are quarantined/positive/normal. Only trusted authorities(doctors) can mark a person quarantined/positive/normal through there accounts. This will help normal citizens or police in finding out if a person is quarantined/positive/normal very easily. 

**Reducing the physical contact** : We usually see the doctors give out prescription to patients on a paper and that paper goes through various hands and touches various surfaces which can lead to infection to a healthy person from unhealthy one. So whenever the doctor enters a medicine in prescription , it gets stored on blockchain ( its benefit discussed below). And whenever a patient goes to a chemist , chemist can know the prescriptions just by scanning the persons face and provide them with medicines without the need of any physical contact. The Payment can also be made by just scanning the face , no need of touching the currency. And everything is safe , as is it decentralised, Thanks to blockchain!.

**A new hands-free Payment System** : As discussed above , there is a new payments system developed which works by just scanning a persons a face to pay them. And this whole system is decentralised on ethereum. So every transaction is recorded and is verified. 

**An informative chat-bot** : This app also includes a chat-bot which can answer your questions regarding any doubts related to COVID-19. 

**Public Health Record System** : As it is a decentralised platform on blockchain all medical records are saved. 

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


 


## Contents 
* [End Users and accesible features](#end-users-)
* [Modules](#modules-)
* [Setting Up](#setting-up-)

## End Users and accesible features [&uarr;](#contents)
- There are three kind of end users of the application
- **Chemist** : A chemist can see what medicines are prescibed to a particular patient by just scanning a person's face using facial identification. He/She can only see the medicines but not change them.  
- **Doctor** : Can add prescription of a particular patient and mark them qurantined/positive/normal .
- **Citizen** : These include the features available to all. These features are checking if a person is qurantined/positive/normal using facial identification, no physical contact payment system, dashboard and a chat-bot.

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

### Prescription Module based on Face Recognition
- Doctors can add prescription for a patient.
- Chemists can view the prescription of a particular patient by face recognition

### Face Recognition based payment system
- Online Wallet system based on face recognition

### Marking person type 
- Doctors can mark a person as corona positive/ meant to be quarantined/ normal

### Chat-bot 
- A chat-bot which can clear doubts regarding Covid-19 based on WHO's database
 
## Setting Up [&uarr;](#contents) 

This application basically can two kind of interfaces that are accessible , one is the anroid app and the other is through command line on which more users can be added. For the android app , all of its features are explained as above. For setting up this application , you will only need to clone this repo for adding more users according to your choice and just download the android app frm tis link (insert google drive link here) and the rest all is done cloud. The endpoints of all the cloud services used by us (like Azure,AWS,Heroku) can be changed by the person who wants to use this project. 

Now for adding users into the database , you need to follow the follwig steps: 

- Download and install all requirements by **pip install -r requirements.txt**

- You will need to change the end point and the provided key the in code to run it on your systems  if the ones provided by us have stopped working.

- If you want to totally crteate new and your own data then you need to run the file create_person_group.py file by hardcoding the group name. In our case it was named test2.

- Otherwise if you want to use just previous database ,Run the file **add_student.py** , and add the details such as name , email, unique number (any random number) etc to register a new user in the database. Pictures will be clicked during this time for training the model.

- Now run **create_person.py** along with agrument like User78 ( create_person.py User78) User78 which is created folder of face data in dataset folder. 78 in this case are the last two digits of unique number entered in previous step. You will  also be prompted to enter public key and private key of the ganache blockchain systems at this stage. Each pair of key must be used for one person only few of these keys for your testing are as given below:

Public Keys : 

(0) 0x2b2DE785Bace5D4A20cfDB96083F9F378A88918b (100 ETH) - currently used for yuvraj 

(1) 0xCf8042790381055fDBc22D709dE49f24a4c963a6 (100 ETH) - currently used for vithik

(2) 0xbfD11E0314035e68471804626802759E54c526af (100 ETH)- currently used for manas

(3) 0x5e65ED58D512ffB1Fe0e52Cd62997736499f8871 (100 ETH)

(4) 0xbb8889a2DFBa8F28A7Ab5a598237fc0651D439b2 (100 ETH)

(5) 0x585b001795da51417ccA2d75912572601d147a3C (100 ETH)

(6) 0x1f3FF2e0CF12107a7c26b067623c711AdE8FCF12 (100 ETH)

(7) 0x8cEAFccf737c7C9f2F4aC21FE0702D12Ad21294b (100 ETH)

(8) 0x638017C8443005D8A74C81A76fEE2Cf6E1DF6e97 (100 ETH)

(9) 0x4A60eCB846335df0321267426880F9aEad3aF569 (100 ETH)

Private Keys : 

(0) 0xd9ee17bd6a58e2514033425de61cc6f452eec902c4eb232be393be28b78c64bc

(1) 0x8da286497a3af3443a81d0c89db483c0782a41516ab4db778cbce58922d5a3e8

(2) 0x71c63d975a83d6c324b86836cccd084dcb0f6228162a9bb69ccc48f9efd8f73b

(3) 0x070f9333922f03957aebd08a958b1e71e05704c0a1fb39aeb96efe7495f9d414

(4) 0x27358a48182780c276894bfa3aa56e21d8e590724a57fdbc6342816eece0f174

(5) 0xf5cc26f92f6fbaecfc69ca38e821aa3da4c46265fc3b9a92c502efa2b8bb38de

(6) 0xc57f0a019a529c73e66332f69c6a3cedf4dec17712066a667472b5eb35ee6075

(7) 0x5f69585af4f4dc620f952ada83f5598a953fe60dbb97a1fa594188f0bff5f925

(8) 0xd3b9ee785fab818d95c5987c12eb930a1a6d48756b4b91e7fb21ad311e04ce4f

(9) 0x556cbe1d98273e17512fb1ea3d6c9c8dd10279af89e18078eb6a8555c1dc6ae5


- Now run **add_person_faces.py** with argument (ex- User78) to add the face data in Face API .

- Now run **train.py** to train the model .

- Now all the face data is fed to the API and model is train to identify. It can be tested using identify.py and giving the path of the image of person to be identified

When the users are entered , android app can be used to access all the features related to them. 

All of the code is run by cloud services used by us so you just need to enter a new user from command line everything will be automatically updated in the databse and vailable for your use.

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

