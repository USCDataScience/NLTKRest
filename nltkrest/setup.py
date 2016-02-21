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

from setuptools import setup, find_packages
from setuptools.command.install import install
from setuptools.command.develop import develop

# requirements = ['nltk','numpy','egenix-mx-base','flask']
def _post_install():
    import site
    reload(site)
    import nltk
    nltk.download('brown')
    print('downloaded brown')
    from nltk.corpus import brown
    print(brown.words()[0:4])
    nltk.download('punkt')
    print('Downloaded punkt tokenizer and corpus')
    print('_post_install complete')
    nltk.download('maxent_treebank_pos_tagger')
    print ('Downloaded Maximum Extent Treebank Position Tagger')
    nltk.download('maxent_ne_chunker')
    print ('Downloaded Maximum Extent Named Entity Chunker')
    nltk.download('words')
    print ('Downloaded NLTK corpora/words')

class my_install(install):
    def run(self):
        print('in my_install')
        install.run(self)
        self.execute(_post_install, [], msg='running _post_install task')

class my_develop(develop):
    def run(self):
        develop.run(self)

import os.path

try:
    from ez_setup import use_setuptools
    use_setuptools()
except ImportError:
    pass

try:
    from setuptools import setup, find_packages
except ImportError:
    from distutils.core import setup, find_packages

version = '0.10'

_descr = u'''
**********
nltkREST
***************

.. contents::
NLTK as a REST based library.
'''
_keywords = 'nltk REST'
_classifiers = [
    'Development Status :: 3 - Alpha',
    'Environment :: Console',
    'Intended Audience :: Developers',
    'Intended Audience :: Information Technology',
    'Intended Audience :: Science/Research',
    'License :: OSI Approved :: Apache Software License',
    'Operating System :: OS Independent',
    'Programming Language :: Python',
    'Topic :: Database :: Front-Ends',
    'Topic :: Scientific/Engineering',
    'Topic :: Software Development :: Libraries :: Python Modules',
]

def read(*rnames):
    return open(os.path.join(os.path.dirname(__file__), *rnames)).read()

long_description = _descr

setup(
    name='nltkrest',
    version=version,
    description='NLTK as a REST service',
    long_description=long_description,
    classifiers=_classifiers,
    keywords=_keywords,
    author='Manali Shah',
    author_email='manalids@usc.edu',
    url='https://github.com/manalishah/NLTKRest',
    download_url='https://github.com/manalishah/NLTKRest',
    license=read('LICENSE.txt'),
    packages=find_packages(exclude=['ez_setup']),
    include_package_data=True,
    zip_safe=True,
    test_suite='nltk.tests',
    entry_points={
        'console_scripts': [
            'nltk-server = nltkrest.server:main'
        ],
    },
    package_data = {
        # And include any *.conf files found in the 'conf' subdirectory
        # for the package
    },
    cmdclass={'install':my_install,'develop':my_develop},
    install_requires=[
        'setuptools',
        'nltk',
        'numpy',
        'egenix-mx-base',
        'flask'
    ],
    extras_require={
    },
)

