NLTK REST Java Example Client
=============================

This is an example client for [NLTK REST](http://github.com/manalishah/NLTKRest/nltkrest) using
the [Apache CXF](http://cxf.apache.org/) library.

To test it, start up the NLTK REST server:

## Installation for NLTK REST 

If you need to install NTK REST:

 1. `pip install nltkrest`

Then, start the server:

 1. `nltk-server -p 8881 -v`

Then, try the client by first building it:

 1. `mvn install` (requires Maven)

Then, run:

1. `java -jar target/nltkrest-examples-1.0-SNAPSHOT.jar "This is Chris Mattmann. I live in Los Angeles, California."`

Which should return:

```
Performing NLTK analysis on text: This is Chris Mattmann. I live in Los Angeles, California. 
Connecting to NLTKRest at: [http://localhost:8881/nltk]
{
    "names": [
        "Chris Mattmann",
        "Los Angeles",
        "California"
    ],
    "result": "success"
}
```
