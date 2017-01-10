# json23plet
A tool for creating and running generators of triplets (in ttl format) from given jsons files.

# Installation

1. install maven on your machine. to do so, type mvn in your command line and follow instructions

1. git clone jbs-json23plet repository to a directory

1. go into the jbs-json23plet directory

1. run "./json23plet.sh -b" to build the json23plet project

# Usage

##### Init the project output directory
* run "./json23plet.sh -init 

##### Run an existing generator:
1. run "./json23plet.sh -generate \<generatorName\> \<dataInputRootDir\>" 
1. run "./json23plet.sh -generate basic \<dataInputRootDir\> to generate with the basic generator (explained later). 
##### Run multiple generators:
1. config the generators that you want to run in jbs-json23plet/src/main/java/json23plet/generators/config.json
1. run "./json23plet.sh -generateAll"

##### Run a new generator:
1. create your generator and drop it in jbs-json23plet/src/main/java/json23plet/generators directory. <br />
For regExGenerator drop it in jbs-json23plet/src/main/java/json23plet/generators/regExGenerators directory.
1. run "./json23plet.sh -b" to rebuild the json23plet project
1. run "./json23plet.sh -generate \<generatorName\> \<dataInputRootDir\>"

##### Create a new ontology from a json file:
1. drop myOntology.json in jbs-json23plet/ontologies/json
1. run "./json23plet.sh -ontology \<myOntology\>"
1. the myOntology.ttl file will be created in jbs-json23plet/ontologies/ttl
1. the myOntology.java will be created in jbs-json23plet/src/main/java/json23plet/ontologies
1. run "./json23plet.sh -b"

##### Add configuration for new generator
1. run "./json23plet -config -add \<generatorName\> \<inputPath\> \<active\>"

##### Edit configuration for existing generator
1. run "./json23plet -config -editGen \<generatorName\> \<propertyName\> \<newPropertyValue\>"
1. the propertyName can be one of the following only: {name, input, active}

##### Edit configuration of global setting
1. run "./json23plet -config -setGlobal \<settingPropertyName\> \<settingValue\>"
1. the settingPropertyName can be one of the following only: {errorLevel, genoutputDir}

##### Add configuration for new generator
1. run "./json23plet -config -addGen \<generatorName\> \<inputPath\> \<active\>"


### Ontologies
To generate new ontology you should create ontology.json file. 
the format of the json should be as we will describe:

    {
        "prefixes" : [ 
            {   
                "prefix" : yourPrefix,
                "uri" : prefixUri
            },
            ...             
        ]
        "metadata" : [
            {
                "uri" : subjectUri,
                property : object,
                ...
            },
            ...
        ]
    }
(See example in jbs-json23plet/ontologies/json/JbsOntology)

After you generate the ontology (using -ontology flag), an ontology.ttl will create in jbs-json23plet/ontologies/ttl,
you can take this file and load it to your server. <br />
Note: do not remove this file, json23plet use it to load your ontology while generate new ttl files in your project.
The ontology.java class file will create in jbs-json23plet/src/main/java/json23plet/ontologies,
this file contains some definition of your ontology and you can use them while generating new ttl files. <br />
Note: you have to rebuild the project using "./json23plet.sh -b" command.

### Triplet
While creating new generator you might using the Triplet class. <br />
each triplet represent a triplet in your rdf model, therefor use the as followed:

    Triplet
    .triplet()
    .subject("subjectUri")
    .preficate(Predicate) // taken from yourOntology.java class might be also uri.
    .object(Resource) // taken from yourOntology.java class might be also uri.

### Json files format
basically you can choose your own foramt and write your own generator for it 
as explained later. <br />
However we recommend to use the following format and avoid from create new generator:

    {
        "subject" : [
            { "uri" : subjectUri,
              Property : Object, // the object can be also list of objects : [object1, object2, ...] 
              ...
            },
            ....
        ]
    }

Using this format allow you to call "./json23plet.sh -generate basic \<dataInputRootDir\>" instead of creating your own generator.
### Json
While parse your json you will need a frameWork to do it <br />
The frameWOrk knows to self load your json file and therefor is simplify the 
using over other libraries as Gson or Jackson. <br />
Using as followed:

    Json
    .json()
    .getAsSomeObject(String) // might also be void, read the code.
    
(See example at jbs-json23plet/src/main/java/json23plet/generators/ExampleGenerator.java) <br />
This way you have a great flexibility in the creation of your own json format. 

### JsonValidator
A frameWork to validate your json before you generate it. <br />
Each validator is build from "registerdValidators" (rules) and two getors 
to get the json data from the file or the Json object. <br />
To create your own validator do as followed:
1. create yourValidator.java class which extend JsonValidator class and drop it in 
jbs-json23plet/src/main/java/json23plet/JsonValidators/
1. Call your validator before you generate as followed:

        JsonValidator v = new MyValidator();
        v.registerValidators();
        v.validateSingleJson(Json.json());

    (See example at jbs-json23plet/src/main/java/json23plet/generators/ExampleGenerator.java) <br />

1. You can choose your errorLevel:
    1. none - nothing happens.
    1. info -  will print for each uri if its O.K or EROOR
    1. stop - stop the execution if error detected.

    To set error level run "./json23plet -config -setGlobal errorLevel \<errorLevel\>"
    
### Regex
A lite and nice module representing a java regex. <br />
Example of using: <br />

    regex()
    .sequence("jbr:tanach-")
    .all()
    .sequence("-")
    .onOf(regex().range(0,9).toRexString())
    .sequence("-")
    .onOf(regex().range(0,9).toRexString())
    .sequence("-")
    .onOf(regex().range(0,9).toRexString())

Read the code for more details.

### RegExGenerator
Sometimes we need to generate a small portion of the json. <br />
For example lets say after using the basic generator you wish to add some properties to 
one of the generated ttl files without recreate it. <br />
For this purpose we created a framework called RegExGenerator: <br />
The framework allow you to activate a series of rules on a selected json objects. < br />
Each RegExGenerator contains the following component:
1. Set of rules (implements of IRegExGenerator interface)
1. A function define how to get the json objects to work with.
1. A gator define the generator id.

To create new RegExGenerator: <br />
1. create MyRegExGenerator.java class and drop it in jbs-json23plet/src/main/java/json23plet/generators/regExGenerators
directory, the generator have to extend BaseRegExGenerator class.
1. Create rules and assign them.
1. Implement the generator function (including your validation etc). 
1. Rebuild the project using "json23plet.sh -b" . 

See Example in jbs-json23plet/src/main/java/json23plet/generators/regExGenerators/ExampleRegExGenerator.java.
 
 

 



