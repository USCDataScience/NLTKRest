
Mac OS

Requirements:
1. python
2. pip
2. virtualenv

	If virtualenv is not present:
	$ pip install virtualenv


Open Terminal and paste the following commands:

$ mkdir Flask_Server (Directory name must not contain spaces)
$ cd Flask_Server
$ git clone https://github.com/manalishah/Flask_REST_Server.git
$ virtualenv <Name_Of_VirtualEnv>
$ source <Name_Of_VirtualEnv>/bin/activate
$ pip install flask
$ pip install nltk numpy
$ chmod a+x run.py

In order to start the server simply paste
$ ./run.py



Now you will see the that the server is up and ready at localhost:5000

Open another Terminal and paste this line to test the server returns the Named Entities Using NLTK library.

$ java -cp TikaNER-0.0.1-SNAPSHOT.jar usc.cs599.TikaNLTK “<TEXT to be recognized>”

