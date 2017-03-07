# json23plet
A command-line tool for generating RDF triplets from Jsons input

## What is this?
json23plet is a linux command line tool to generate [RDF triplets](https://en.wikipedia.org/wiki/Resource_Description_Framework) in TURTLE (.ttl) format from json files. <br/>
You can write your own generators for your specific json format files and according to your own ontology
and run it through json23plet

## Background
This project was devleoped as part of the JBS (Jewish Book Shelf) project in the [TDK lab](http://tdk.net.technion.ac.il/).

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
   
## Working with windows
We recommend you to develop and edit files in the project on linux machine, as where the project developed, but it can be also done on windows machines.

### IDE 
We recommed to use [IntelliJ IDEA](https://www.jetbrains.com/idea/) while developing, but you can also use [Eclipse](https://www.eclipse.org/downloads/) or any other IDE you prefer, but our guide is on IntelliJ only.

#### IntelliJ Guid
* **Clone the repository** <br/>
     Go to File->New->Project From Version Control->GitHub, Fill the details of this repository and click "Clone". <br/>

* **Configure the arguments** <br/>
     1. Right click on src/main/java->Mark Directory as->Sources Root
     1. Build The project
     1. Right click on src/main/java/Json23plet.java->Create 'Json23plet.main()' and configure your input
     1. Run or Debug

## json23plet commands

### Build the project

      ./json23plet.sh -b
      
### Init the project directories
The command initializes the direcories tree of the project

      ./json23plet.sh -init

### Config the output root directory
The command sets up the output roor directory. <br />
This setting will be saved in the config.json file. <br/>
The input directories tree will be reflected in this directory.

      ./json23plet.sh -config outputDir=myOutputDir

#### Run a single generator
The command run recursively a specific [generator](README.md#generators) on a specific input directory (or a specific single file)

      ./json23plet.sh -generate generatorName dataInputRootDir
      (./json23plet.sh -generate generatorName inputFile.json)
In case of using the [basic](README.md#json-files-format) generator, specify "basic" as the generator
      
      ./json23plet.sh -generate basic dataInputRootDir

##### Run multiple generators
Json23plet allows you to config a scheme of running multiple different generators on diffrent directories (or files). <br/>
To do so you need to [config](README.md#add-configuration-for-a-new-generator) your scheme and run:
      
      ./json23plet.sh -generateAll


##### Create and install a new generator
***This is not a command but a basic part of working with the Json23plet tool***
Json23plet allows you to write and deploy your own generator for more unique json formats. <br/>
*To do so*:<br/>
Create your [generator](README.md#generators) and drop it in jbs-json23plet/src/main/java/json23plet/generators/customGenerators directory.<br/> For [regExGenerator](README.md#regexgenerator) drop it in jbs-json23plet/src/main/java/json23plet/generators/regexGenerators directory.<br/> Rebuild the project using:
            
      ./json23plet.sh -b
            
Now you can [run](README.md#run-a-single-generator) your generator as decribed above

##### Create and update an ontology
Json23plet using the same logic to generate ontology.ttl files, that means that to generate new ontology you just have to define it in a json file and run the json23plet ontology generator.

1. Define the ontology in myOntology.json in [this](README.md#ontologies) format
1. To create the ontology run:

            ./json23plet.sh -ontology myOntology

      an myOntology.ttl file will be created in **jbs-json23plet/ontologies/ttl**, you can load this file to your server.<br/>
      an myOntology.java will be created in **jbs-json23plet/src/main/java/json23plet/ontologies**, this class contains some       of the ontology metadata and allow you to include them in your generators.
1. Rebuild using:

            ./json23plet.sh -b 

***Note***: While changing an existing ontology, there might  be some generators that use the old ontology.java properties, if so you have to update them, otherwise the project wont build due to compilations errors.

##### Add&Edit configuration for a new generator
As [mentioned](README.md#run-multiple-generators) above, you can config a scheme of run.<br/>
The command allow you to config a single generator in the scheme, of course, you can update the schema manually.

* To config via commandline run:

            ./json23plet -config  genName=generatorName inputPath=MyInputPath active=activeState
 (the activeState field gets either true or false depends on the current scheme)
* To config manually:<br/>
  Go to **jbs-json23plet/config.json** and set the fileds manually

##### Edit configuration of global setting
Json23plet use some [setting](README.me#globalsetting) while runnig.
* To config output dir run:
      
            ./json23plet -config outputDir=myOutputDir
* To config error level run:

            ./json23plet -config errorLevel=level
 Errors checked on the json file while generating, the action on error depeneds on errorLevel: 
 * low - nothing happen
 * medium - error will display while detected
 * high - on error json23plet will stop

## Development and maintenance
In this section we will review the code components for future maintenance.<br/>
See the [code documentation](https://techniontdk.github.io/jbs-json23plet/).

### Overview
json23plet is simply an engine which know to run user defined generators. <br/>
Generator is a piece of code that know (with some usege of our modules) to generate RDF triplets from json file.<br/>
After the generator finished the engine print the results to an output with the same name as the origin one.

### Ontologies
Source code:
      
      src/main/java/json23plet/generators/ontologyGenerator/OntologyClassGenerator.java
      src/main/java/json23plet/generators/ontologyGenerator/OntologyGenerator.java
      src/main/java/json23plet/generators/ontologyGenerator/OntologyTTLGenerator.java
      
As mentioned before json23plet handle ontologies on thw same way of regular json data. we worte a speiceal ontology generator which have two purpose:
1. create the ttl's files of the ontology. thos are the files you will load to your server.
1. create a java class which will allow you to referece to your ontology definitions as java objects.
The generator require the ontology in a very specific format:<br/>

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
              "rdf:type" : "owl:Class", //<----required predicate//
              "rdfs:label" : "Tanach",
              "rdfs:subClassOf" : "owl:Thing"
              ...
            },
            ...
        ]
    }
(The uri and rdf:type predicate are required).

#### Usege
Drop your ontology.json file in *jbs-json23plet/ontologies/json* and run:

      ./json23plet -ontology myOntology
      
After generating an ontology.ttl file will be created in *jbs-json23plet/ontologies/ttl*, (and you can load it to your server).<br/>
**Note:** Do not remove this file, because json23plet uses it to load your ontology during generating new ttl files in your project.

The ontology.java class file will be created in *jbs-json23plet/src/main/java/json23plet/ontologies*, this file contains some definitions of your ontology and can be used during writing a new generator.<br/>

***Note:*** After changing an existing ontology there might have some generators which are still using the old ontology definitions. If so, you have to update them or you will get compilation errors.

Rebuild the project using:

      ./json23plet.sh -b

### Json
Source code:

      src/main/java/json23plet/modules/Json.java
Json is a module for parsing json files. json23plet engine assume you using this tool, but its not a duty - you can use your own json parser but we highly recommend you to use this module.<be/>
The engine load staticly (per thread) the current working json file. and you can access (in your generator) to the parsed file via json23plet.Json module.

#### Usege

    Json
    .json() // return the current working file already parsed.
    .getAsSOmeObject("property") // read the code documentation foe more details.

### Generators
By using this, you can generate a new RDF triplet file from your json file.

#### Json files format
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





1. Write MyGenerator.java class and drop it in jbs-json23plet/src/main/java/json23plet/generators directory.
<br />the generator has to extend the generator class and implement the generate function:

        public void generate();

1. Run "./json23plet -b" to rebuild the project. 
1. Run "./json23plet.sh -generate MyGenerator \<dataInputRootDir\>".
 
(See example at jbs-json23plet/src/main/java/json23plet/generators/ExampleGenerator.java).

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
 

 



