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
    print('_post_install complete')

class my_install(install):
    def run(self):
        print('in my_install')
        install.run(self)
        self.execute(_post_install, [], msg='running _post_install task')

class my_develop(develop):
    def run(self):
        develop.run(self)

short_description='NLTK endpoint for TIKA Named Entity Recognition '
long_description=short_description
with open('README.md') as fp:
    long_description = fp.read()
LICENSE='Avl2'
with open('LICENSE') as fp:
    LICENSE = fp.read()
setup(
        name='uscir.nltkrest',  
        version='0.0.10',
        description=short_description,
        long_description=long_description,
        author='Manali Shah',
        author_email='manalids@usc.edu',
        license=LICENSE,
        install_requires=['nltk','numpy','egenix-mx-base','flask'],
        packages=find_packages(),
        cmdclass={'install':my_install,'develop':my_develop},
        )
