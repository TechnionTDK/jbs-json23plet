# json23plet
A tool for creating and running generators of triplets (in ttl format) from given jsons files.

# Installation

1. Install maven on your machine. to do so, type mvn in your command line and follow instructions

1. Git clone jbs-json23plet repository to a directory

1. Go into the jbs-json23plet directory

1. Run "./json23plet.sh -b" to build the json23plet project

# Usage

##### Init the project directories
* Run "./json23plet.sh -init

##### Config the output root directory
* Run "./json23plet -config -setGlobal genOutputDir \<myOutputDir\>"

##### Run single generator:
* Run "./json23plet.sh -generate \<generatorName\> \<dataInputRootDir\>"
* In case of using the basic generator (see [Json File Formats](README.md#json-files-format)). <br /> Run "./json23plet.sh -generate basic \<dataInputRootDir\>" 

##### Run multiple generators:
1. [Config](README.md#add-configuration-for-a-new-generator) the generators that you want to run in jbs-json23plet/src/main/java/json23plet/generators/config.json
1. Run "./json23plet.sh -generateAll"

##### Create a new generator:
1. Create your [generator](README.md#generators) and drop it in jbs-json23plet/src/main/java/json23plet/generators directory.<br /> For [regExGenerator](README.md#regexgenerator) drop it in jbs-json23plet/src/main/java/json23plet/generators/regExGenerators directory
1. Run "./json23plet.sh -b" to rebuild the json23plet project

##### Create a new ontology from a json file:
1. Drop myOntology.json in jbs-json23plet/ontologies/json
1. Run "./json23plet.sh -ontology \<myOntology\>"
1. The myOntology.ttl file will be created in jbs-json23plet/ontologies/ttl
1. The myOntology.java will be created in jbs-json23plet/src/main/java/json23plet/ontologies
1. Run "./json23plet.sh -b" to rebuild the json23plet project

##### Add configuration for a new generator
* Run "./json23plet -config -add \<generatorName\> \<inputPath\> \<activeState\>" (the \<activeState\> field gets either true or false)

##### Edit configuration for an existing generator
1. Run "./json23plet -config -editGen \<generatorName\> \<propertyName\> \<newPropertyValue\>"
1. The propertyName can be one of the following only: {name, input, active}

##### Edit configuration of global setting
1. Run "./json23plet -config -setGlobal \<settingPropertyName\> \<settingValue\>"
1. The settingPropertyName can be one of the following only: {errorLevel, genOutputDir}

# Components

### Ontologies
To generate new ontology you should create ontology.json file. <br />
the format of the json should be as described:

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
(See example in jbs-json23plet/ontologies/json/JbsOntology.json)

After you generate the ontology (using -ontology flag), an ontology.ttl file will be created in jbs-json23plet/ontologies/ttl, (and you can load it to your server).<br />
Note: do not remove this file, because json23plet uses it to load your ontology while generate new ttl files in your project.<br />The ontology.java class file will be created in jbs-json23plet/src/main/java/json23plet/ontologies, this file contains some definitions of your ontology and can be used while writing a new generator.<br />
Note: you have to rebuild the project using "./json23plet.sh -b" command.

### Triplet
While creating a new generator you may use the Triplet class.<br />
Each triplet represents a triplet in your rdf model<br /> 
Use as follow:

    Triplet
    .triplet()
    .subject("subjectUri")
    .preficate(Predicate) // taken from yourOntology.java class might be also uri.
    .object(Resource) // taken from yourOntology.java class might be also uri.

### Json files format
Basically you can choose your own foramt and write your own generator for it ([as explained later](README.md#generators)).
<br />However we recommend using the following format that avoids creating a new generator:

    {
        "subjects" : [
            { "uri" : subjectUri,
              Property : Object, // the object can be also list of objects : [object1, object2, ...] 
              ...
            },
            ....
        ]
    }

Using this format allow you to run "./json23plet.sh -generate basic \<dataInputRootDir\>" instead of creating your own generator.

### Generators
Allows to generate a new ttl file from json file.<br />
Using:

1. Write MyGenerator.java class and drop it in jbs-json23plet/src/main/java/json23plet/generators directory.
<br />the generator has to implement the generate function:

    public void generate();

1. Run "./json23plet -b" to rebuild the project. 
1. Run "./json23plet.sh -generate MyGenerator \<dataInputRootDir\>".
 
(See example at jbs-json23plet/src/main/java/json23plet/generators/ExampleGenerator.java).

### Json
In order to write your own generator, you will need to parse your json with a framework.<br />
Our framework knows to load your json file and therefore it simplifies the usage compared to other libraries (as Gson or Jackson).<br />
Using as follow:

    Json
    .json()
    .getAsSomeObject(String) // might also be void, read the code.
    
(See example at jbs-json23plet/src/main/java/json23plet/generators/ExampleGenerator.java) <br />
This way you have a great flexibility in the creation of your own json format. 

### JsonValidator
A framework to validate your json before you generate it. <br />
Each validator is build from "registerdValidators" (rules) and two getors 
to get the json data from the file or the Json object. <br />
To create your own validator do as followed:
1. Create yourValidator.java class which extend JsonValidator class and drop it in 
jbs-json23plet/src/main/java/json23plet/JsonValidators/
1. Call your validator before you generate as followed:

        JsonValidator v = new MyValidator();
        v.registerValidators();
        v.validateSingleJson(Json.json());
        
(See example at jbs-json23plet/src/main/java/json23plet/generators/ExampleGenerator.java).

* You can choose your errorLevel:
    1. none - nothing happens.
    1. info -  will print for each uri if its O.K or EROOR
    1. stop - stop the execution if error detected.

    To set error level run "./json23plet -config -setGlobal errorLevel \<errorLevel\>"
    
### Regex
A lite and nice module representing a java regex. <br />
Example of using: <br />

    Regex
    .regex()
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

To create new RegExGenerator:

1. Create MyRegExGenerator.java class and drop it in jbs-json23plet/src/main/java/json23plet/generators/regExGenerators
directory, the generator have to extend BaseRegExGenerator class.
1. Create rules and assign them.
1. Implement the generator function (including your validation etc). 
1. Rebuild the project using "json23plet.sh -b" . 
1. Run "./json23plet.sh -generate MyGenerator \<dataInputRootDir\>".

See Example in jbs-json23plet/src/main/java/json23plet/generators/regExGenerators/ExampleRegExGenerator.java.
 
 

 



