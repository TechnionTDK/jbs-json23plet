# json23plet
A command-line tool for generating RDF triplets from Jsons input

## Getting Started

### Prerequisites
Install maven on your machine.

      apt-get install maven
      
### Installation

1. Git clone jbs-json23plet repository to a directory

         git clone git@github.com:TechnionTDK/jbs-json23plet.git

1. Go into jbs-json23plet/ and build the project

         ./json23plet.sh -b
      
1. Untrack the configuration file from git

         git update-index --assume-unchanged config.json
   this command will prevent from your local config.json to be pushed into the GitHub repository

# Usage

##### Init the project directories
* Run "./json23plet.sh -init <br />
The -init command initiate the folders tree of the project.

##### Config the output root directory
* Run "./json23plet.sh -config outputDir=myOutputDir" <br />
This command initiate the root directory where the output will be generated.

##### Run single generator:
* Run "./json23plet.sh -generate generatorName dataInputRootDir"
* In case of using the basic generator (see [Json File Formats](README.md#json-files-format)). <br /> Run "./json23plet.sh -generate basic \<dataInputRootDir\>" 

##### Run multiple generators:
1. [Config](README.md#add-configuration-for-a-new-generator) the generators that you want to run in jbs-json23plet/config.json
1. Run "./json23plet.sh -generateAll"

##### Create a new generator:
1. Create your [generator](README.md#generators) and drop it in jbs-json23plet/src/main/java/json23plet/generators/customGenerators directory.<br /> For [regExGenerator](README.md#regexgenerator) drop it in jbs-json23plet/src/main/java/json23plet/generators/regexGenerators directory
1. Run "./json23plet.sh -b" to rebuild the json23plet project

##### Create a new ontology from a json file:
1. Drop myOntology.json in jbs-json23plet/ontologies/json
1. Run "./json23plet.sh -ontology myOntology"
1. The myOntology.ttl file will be created in jbs-json23plet/ontologies/ttl
1. The myOntology.java will be created in jbs-json23plet/src/main/java/json23plet/ontologies
1. Run "./json23plet.sh -b" to rebuild the json23plet project

Note: While changing an existing ontology, there might  be some generators that use the old ontology properties, if so you have to update them, otherwise the project wont build due to compilations errors.

##### Add configuration for a new generator
* Run "./json23plet -config  genName=generatorName inputPath=MyInputPath active=activeState" (the activeState field gets either true or false) <br />
This command will create a new configuration for your generator in the jbs-json23plet/config.json file. <br />
You can also edit it manually. 

##### Edit configuration for an existing generator
1. Run "./json23plet -config genName=generatorName inputPath=MyInputPath active=activeState" <br />
If a generator with this name is already exist the command will edit the generator fields, else it will create a new configuration.

##### Edit configuration of global setting
1. Run "./json23plet -config outputDir=myOutputDir" to set a new output directory
1.  Run "./json23plet -config errorLevel=level" to set a new errorLevel. <br /> ErrorLevel indicate eaht would happens if the validator facing an error: <br />
    * low - Nothing will happen.
    * medium - The errors will display.
    * high - On error stop execution.

# Ontologies
To generate new ontology you should create ontology.json file. <br />
the format of the json should be as described:

    {
        "prefixes" : [
            {
              "prefix" : "jbo",
              "uri" : "http://jbs.technion.ac.il/ontology/"
            },
            {
              "prefix" : "jbr",
              "uri" : "http://jbs.technion.ac.il/resource/"
            },
            ...
          ],
          "metadata" : [
            {
              "uri" : "jbo:Tanach",
              "rdf:type" : "owl:Class", //<----require predicate//
              "rdfs:label" : "Tanach",
              "rdfs:subClassOf" : "owl:Thing"
              ...
            },
            ...
        ]
    }
(See example in jbs-json23plet/ontologies/json/JbsOntology.json) <br />
The uri and rdf:type predicate are required.

After you generate the ontology (using -ontology flag), an ontology.ttl file will be created in jbs-json23plet/ontologies/ttl, (and you can load it to your server).<br />

Note: Do not remove this file, because json23plet uses it to load your ontology while generate new ttl files in your project.<br />The ontology.java class file will be created in jbs-json23plet/src/main/java/json23plet/ontologies, this file contains some definitions of your ontology and can be used while writing a new generator.<br />

Note: After changing an existing ontology there might have some generators which are still using the old ontology definitions. If so you have to update them or you will get compilation errors.

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
<br />the generator has to extend the generator class and implement the generate function:

        public void generate();

1. Run "./json23plet -b" to rebuild the project. 
1. Run "./json23plet.sh -generate MyGenerator \<dataInputRootDir\>".
 
(See example at jbs-json23plet/src/main/java/json23plet/generators/ExampleGenerator.java).

### Json
In order to write your own generator, you will need to parse your json.<br />
Our library knows to load your json file and therefore it simplifies the usage compared to other libraries (as Gson or Jackson).<br />
Using as follow:

    Json
    .json()
    .getAsSomeObject(String) // might also be void, read the code.
    
(See example at jbs-json23plet/src/main/java/json23plet/generators/ExampleGenerator.java)

### JsonValidator
A library to validate your json before you generate triplets from it.<br />
Each validator is build from "registerdValidators" (rules) and two getters to get the json data from the file or the Json object.<br />
To create your own validator do as follow:

1. Create yourValidator.java class which extend JsonValidator class and drop it in jbs-json23plet/src/main/java/json23plet/JsonValidators/
1. Call your validator before you generate triplets as follow:

        JsonValidator v = new MyValidator();
        v.registerValidators();
        v.validateSingleJson(Json.json());
        
(See example at jbs-json23plet/src/main/java/json23plet/generators/ExampleGenerator.java)

* You can choose your errorLevel:
    1. none - nothing happens.
    1. info - will print each uri that has an EROOR
    1. stop - stop the execution if error detected.

    To set the error level run "./json23plet -config -setGlobal errorLevel \<errorLevel\>"
    
### Regex
A lite component to create a java regex.<br />
Example of usage:

    regex("jbr:tanach-(.*)-(\\d+)-(\\d+)-(\\d+)")
    .match(json.value("uri"))
(See Example in jbs-json23plet/src/main/java/json23plet/generators/regexGenerators/RdfTypeGenerator.java)

### RegexGenerator
In case that you want to add triplets for a specific json object (e.g. for adding rdf:type to all jbr-tanach\* json objects)<br />
For this purpose we created a component called RegExGenerator:<br />
It allows you to activate a series of rules on a selected json objects.<br />
Each RegExGenerator contains the following:

1. Set of rules (implements of IRegExGenerator interface)
1. Function that defines how to get the json objects to work on
1. Getter function that defines the generator name

##### Creating a new RegExGenerator:

1. Create MyRegExGenerator.java class and drop it in jbs-json23plet/src/main/java/json23plet/generators/regExGenerators directory, the generator have to extend BaseRegExGenerator class.
1. Create rules and assign them
1. Implement the generator function (including your validation etc.)
1. Run "json23plet.sh -b" to rebuild the project
1. Run "./json23plet.sh -generate MyRegExGenerator \<dataInputRootDir\>"

(See Example in jbs-json23plet/src/main/java/json23plet/generators/regExGenerators/RdfTypeGenerator.java)


### Testing
The tool contains an auto testing framwork.
For testing your generator do as follow:

1. Add myGenerator.input.json to jbs-json23plet/src/test/testsFiles 
1. Add myGenerator.expected.ttl to jbs-json23plet/src/test/testsFiles 
1. Run AutoTest
 

 



