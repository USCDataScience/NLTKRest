
Mac OS

Requirements:
1. python
2. pip
2. virtualenv

	If virtualenv is not present:
	$ pip install virtualenv


Open Terminal and paste the following commands:


$ git clone https://github.com/manalishah/Flask_REST_Server.git
$ cd Flask_REST_Server
$ virtualenv <Name_Of_VirtualEnv>

For the server to run with the right configurations, your virtualenv must be activated:
$ source <Name_Of_VirtualEnv>/bin/activate
$ pip install flask nltk numpy
$ python -m nltk.downloader all
$ chmod a+x run.py

In order to start the server anytime simply paste this command:
$ ./run.py


Now you will see the that the server is up and ready at localhost:5000

Open another Terminal and paste run these commands to test the server returns the Named Entities Using NLTK library.

Compile:
$ cd NlTKClient
$ javac -d bin -sourcepath src -cp lib/httpclient-4.5.1.jar:lib/httpcore-4.4.3.jar:lib/commons-logging-1.2.jar src/usc/cs599/TikaNLTK.java

Run: <Supply any text that you want to tag withing the quotes>
$ java -cp bin:lib/httpclient-4.5.1.jar:lib/httpcore-4.4.3.jar:lib/commons-logging-1.2.jar usc.cs599.TikaNLTK "America is huge."

