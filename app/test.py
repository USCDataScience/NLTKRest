# -*- coding: utf-8 -*-
import nltk

def processSpecific(content):
    tokenized = nltk.word_tokenize(content)
    tagged = nltk.pos_tag(tokenized)
    namedEnt = []
    Entities = {'PERSON':set(), 'LOCATION':set(), 'GPE':set(), 'FACILITY':set()}
    NE = set()
    namedEnt = nltk.ne_chunk(tagged, binary=True)
    for each_ne in namedEnt:
        if isinstance(each_ne, nltk.tree.Tree):
            value = ''
            for i in range(0,len(each_ne)):
                value += each_ne[i][0] + ' '
            value = value.strip()
            NE.add(value)
       
    namedEnt = nltk.ne_chunk(tagged)
    for each_ne in namedEnt:
        if isinstance(each_ne, nltk.tree.Tree):
            value = ''
            for i in range(0,len(each_ne)):
                value += each_ne[i][0] + ' '
            value = value.strip()
            if value in NE:
                NE.remove(value)
            Entities[each_ne.label()].add(value)
    Entities['NE'] = NE

    return str(Entities)

#print(processSpecific('Barack Obama must win again',''))
#content = 'Barack Obama must win against Bill Clinton in the America. Brazil won the world cup last year.'

 
