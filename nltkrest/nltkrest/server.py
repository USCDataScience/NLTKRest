#!/usr/bin/env python
# encoding: utf-8
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

from flask import Flask, request
import nltk
import json
#import timex
import time
import sys
import getopt

USAGE = """
nltk-rest --port -p <port> -v [--help -h]

Expose NLTK over REST as a server using Python Flask. Submit content to the
`/nltk` endpoint in the REST body request. 

-h, --help Prints this message.
-p, --port Sets the port for the REST server, default is 8881.
"""
Verbose = 0
Port = 8881 #default port
def echo2(*s): sys.stderr.write('server.py [NLTK]: ' + ' '.join(map(str, s)) + '\n')
app = Flask(__name__)

@app.route('/')
def status():
    msg = '''
       <html><head><title>NLTK REST Server</title></head><body><h3>NLTK REST server</h3>
       <p>This app exposes the Python <a href="http://nltk.org/">Natural Language Toolkit (NLTK)</a>
       as a REST server.</p>
       <h2>Status: Running</h2>
       <p>More apps from the <a href="//irds.usc.edu/">USC Information Retrieval & Data Science Group</a>.</p>
    '''
    return msg

@app.route('/nltk', methods=["PUT", "POST"])
def namedEntityRecognizer():
    echo2("Performing NER on incoming stream")
    content = request.stream.read()
    if Verbose:
        echo2("Incoming content is "+content)
    start = time.time()
    #timer = timex.tag(content)
    tokenized = nltk.word_tokenize(content)
    tagged = nltk.pos_tag(tokenized)
    namedEnt = nltk.ne_chunk(tagged, binary=True)
    names = extract_entity_names(namedEnt)
    result = {"result" : "success", "names" : names}
    jsonDoc = json.dumps(result, sort_keys=True, indent=4, separators=(',', ': '))
    end = time.time()
    print "NER took "+str(end - start)+" seconds"
    return jsonDoc


# Based on example from:
# https://gist.github.com/onyxfish/322906
def extract_entity_names(t):
    entity_names = []
    if hasattr(t, 'label') and t.label:
        if t.label() == 'NE':
            entity_names.append(' '.join([child[0] for child in t]))
        else:
            for child in t:
                entity_names.extend(extract_entity_names(child))
    return entity_names

def main(argv=None):
    """Run NLTK REST server from command line according to USAGE."""
    global Verbose
    if argv is None:
        argv = sys.argv

    try:
        opts, argv = getopt.getopt(argv[1:], 'hp:v',
          ['help', 'port=', 'verbose'])
    except getopt.GetoptError, (msg, bad_opt):
        die("%s error: Bad option: %s, %s" % (argv[0], bad_opt, msg))
        
    port = Port
    for opt, val in opts:
        if opt   in ('-h', '--help'):    echo2(USAGE); sys.exit()
        elif opt in ('--port'):          port = int(val)
        elif opt in ('-v', '--verbose'): Verbose = 1
        else: die(USAGE)

    app.run(debug=Verbose, port=port)

if __name__ == '__main__':
    main(sys.argv)
