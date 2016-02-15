from flask import Flask
import nltk
import json
import timex
import time

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/nltk')
def namedEntityRecognizer(content):
    start = time.time()
    timer = timex.tag(content)
    tokenized = nltk.word_tokenize(content)
    tagged = nltk.pos_tag(tokenized)
    namedEnt = []
    Entities = {'PERSON':set(), 'LOCATION':set(), 'GPE':set(), 'FACILITY':set()}
    Entities['TIME'] = timer
    NE = set()
    namedEnt = nltk.ne_chunk(tagged, binary=True)
    for each_ne in namedEnt:
        if isinstance(each_ne, nltk.tree.Tree):
            value = ''
            for i in range(0,len(each_ne)):
                value += each_ne[i][0] + ' '
            value = value.strip()
            NE.add(value)
    # namedEnt = nltk.ne_chunk(tagged)
    # for each_ne in namedEnt:
    #     if isinstance(each_ne, nltk.tree.Tree):
    #         value = ''
    #         for i in range(0,len(each_ne)):
    #             value += each_ne[i][0] + ' '
    #         value = value.strip()
    #         if value in NE:
    #             NE.remove(value)
    # 	    if each_ne.label() in Entities:
    #                 Entities[each_ne.label()].add(value)
    # 	    else:
    # 		  Entities[each_ne.label()] = set()
    # 		  Entities[each_ne.label()].add(value)
    # Entities['NE'] = NE
    result = {}
    for each in Entities:
        if len(Entities[each]) is 0:
            continue
        else:
            result[each] = list(Entities[each])
    j = json.dumps(result)
    end = time.time()
    print(end - start)
    return j

if __name__ == '__main__':
    app.run(debug=True)
