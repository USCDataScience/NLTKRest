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
Generating JSON for comparing NLTK and CoreNLP 
==============================================
NLTKandCoreNLP compares the results of Named Entities recognized by Stanford CoreNLP against those extracted by Tika trunk containing ability to use NLTKNeRecignizer Parser.

1. `java -cp target/nltkrest-examples-1.0-SNAPSHOT.jar edu.usc.ir.visualization.NLTKandCoreNLP "url/to/solr/dev" "username" "password" "path/to/destination/folder"`

Which should return:

```
Json ready for Visualization: path/to/destination/folder/nltk_vs_corenlp.json
```
You can then follow the procedure to see a beautiful visualization using instructions from [Tika-NLTKvsCoreNLP](https://github.com/manalishah/Tika-NLTKvsCoreNLP.git)

Questions, comments?
===================
Send them to [Manali Shah](manalids@usc.edu) or [Chris A. Mattmann](mailto:chris.a.mattmann@jpl.nasa.gov).

Contributors
============
* Manali Shah, USC
* Chris A. Mattmann, USC & JPL

License
=======
[Apache License, version 2](http://www.apache.org/licenses/LICENSE-2.0)
