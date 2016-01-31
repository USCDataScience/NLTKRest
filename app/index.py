# -*- coding: utf-8 -*-
"""
Created on Sat Jan 30 18:46:21 2016

@author: manali
"""
from app import app
@app.route('/')
@app.route('/index')

def index():
    return '<html><head><title>INDEX</title></head><body><h1>Welcome to FLask Rest Service for NLTK</h1></body></html>'