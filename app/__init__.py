from flask import Flask
app = Flask(__name__)
from app import ner
from app import index
