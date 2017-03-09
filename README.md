# json23plet
A command-line tool for generating RDF triplets from Jsons input

## What is this?
json23plet is a linux command line tool to generate [RDF triplets](https://en.wikipedia.org/wiki/Resource_Description_Framework) in TURTLE (.ttl) format from json files.<br/>
You can write your own generators for your specific json format files, according to your own ontology
and run it through json23plet.

## Background
This tool was devleoped as part of the JBS (Jewish Book Shelf) project in the [TDK lab](http://tdk.net.technion.ac.il/).

# Getting Started

## Prerequisites
Install [Maven](https://maven.apache.org/) on your machine.

    apt-get install maven

## Installation

1. Git clone jbs-json23plet repository to a directory

        git clone git@github.com:TechnionTDK/jbs-json23plet.git

1. Go into jbs-json23plet/ and build the project

        ./json23plet.sh -b
   this command will call `mvn install`
      
1. Untrack the configuration file from git

        git update-index --assume-unchanged config.json
   this command will prevent from your local `config.json` to be untracked from the GitHub repository

## json23plet commands

### Build the tool

      ./json23plet.sh -b
this command will call `mvn install`
      
### Initialize the project directories
The command initializes the direcories tree of the project

      ./json23plet.sh -init
this command creates `ontologies/json`, `ontologies/ttl` and `src/test/testsFiles` directories. During running, the tool assumes those directories exist.

### Configure the output root directory
The command sets up the output root directory and saves it in `config.json` file.<br/>
The input directories tree will be reflected in this directory and will have an identical directory structure.

      ./json23plet.sh -config outputDir=myOutputDir

### Run a single generator
The command runs a specific [generator](README.md#generators) on a specific input directory (or a specific single file) recursively.

      ./json23plet.sh -generate generatorName dataInputRootDir
      (./json23plet.sh -generate generatorName inputFile.json)
In case of using the [basic](README.md#the-basic-generator) generator, specify `basic` as the generator name
      
      ./json23plet.sh -generate basic dataInputRootDir

### Run multiple generators
Json23plet allows you to config a scheme of running multiple different generators on diffrent directories (or files). <br/>
To do so you need to [configure your scheme](README.md#addedit-configuration-for-a-new-generator) and run this command.
      
      ./json23plet.sh -generateAll


### Create and install a new generator
***This is not a command but a basic part of working with the Json23plet tool***
json23plet allows you to write and deploy your own generator for unique json formats.<br/>
*To do so:*<br/>
* Create your [generator](README.md#generators) and drop it in `src/main/java/json23plet/generators/customGenerators` directory.<br/>
* For [regexGenerator](README.md#regexgenerator) drop it in `src/main/java/json23plet/generators/regexGenerators` directory.<br/>
* Rebuild the project using:
            
      ./json23plet.sh -b
            
* Now you can [run](README.md#run-a-single-generator) your generator as decribed above

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
See also [setting](README.md#json23plet-configuration).

##### Edit configuration of global setting
Json23plet use some [setting](README.md#json23plet-configuration) while runnig.
* To config output dir run:
      
            ./json23plet -config outputDir=myOutputDir
* To config error level run:

            ./json23plet -config errorLevel=level
 Errors checked on the json file while generating, the action on error depeneds on errorLevel: 
 * low - nothing happen
 * medium - error will display while detected
 * high - on error json23plet will stop
 
## Json23plet configuration
Json23plet have some configuration, all defined in *config.json* file.<br/>
The default configurations are:

      {
        "setting": {
          "globalSetting": {
            "genOutputDir": "output", // the output root dir
            "errorLevel": "low" 
          },
          "generators": [] // configuration scheme to run multile generators
        }
      }
      
## Testing
We build a simple and efficient testing framework.
to test your generator do as follow:

1. Add myGenerator.input.json to jbs-json23plet/src/test/testsFiles 
1. Add myGenerator.expected.ttl to jbs-json23plet/src/test/testsFiles

To run the Tests simply rebuild the project using:

      ./json23plet.sh -b 
and maven will run the tests.<br/>
If you wish only to run the tests pleses see this [guide](http://junit.sourceforge.net/doc/faq/faq.htm#running_4)

## Devlopment enviorment
It's prefered to devolpe and use the tool on a Linux machine where you can run the tool through the command line.<br\>
Working on windows requires an IDE.

### Devloping with an IDE 
It's recommeded to use [IntelliJ IDEA](https://www.jetbrains.com/idea/) and our guide will focus on it.

### Getting started with Intellij
* *Clone the repository*<br/>
     Go to File->New->Project From Version Control->GitHub, Fill `git@github.com:TechnionTDK/jbs-json23plet.git` in the repository field and click on `Clone`.<br/>

* *Configure tool arguments*<br/>
     1. Right click on src/main/java->Mark Directory as->Sources Root
     1. Build The project
     1. Right click on src/main/java/Json23plet.java->Create 'Json23plet.main()' and configure the tool arguments
     1. Run or Debug the tool


## Development and maintenance
In this section we will review the code components for future maintenance.<br/>
See the [code documentation](https://techniontdk.github.io/jbs-json23plet/).

### Overview
json23plet is simply an engine which know to run user defined generators. <br/>
It uses reflaction, static loading files and the Apache jena library.<br/>
By specify your generator name, jsob23plet uses relaction to find it and activate it on the input directory. <br/>
We also supply some modules to work with json23plet comfortably and with simpler way.

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

#### Usage
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
Json is a module for parsing json files.<br/>
json23plet engine assume you using this tool, but its not a duty - you can use your own json parser but we highly recommend you to use this module.<be/>
The engine load staticly (per thread) the current working json file, and you can access (in your generator) to the parsed file via Json module.

#### Usage

    Json
    .json() // return the current working file already parsed.
    .getAsSOmeObject("property") // read the code documentation foe more details.

### Triplet
Source code:

      src/main/java/json23plet/modules/Triplet.java 
The Triplet module is a simple wrraper for the papche jena library.<br/>
json23plet's engine load an RDF model before calling your generator and allow you to add triplets to that model using the Triplet module.

#### Usage

    Triplet
    .triplet()
    .subject("subjectUri")
    .preficate(Predicate) // taken from Ontology.java class might be also uri.
    .object(Resource) // taken from Ontology.java class might be also uri.
    
### Regex
Source code:

      src/main/java/json23plet/modules/Regex.java
A simple implemantion of java regex.

#### Usage
      
      regex(a.*) // load the regex
        .match("abc") // check if the string match to the regex.

### DataPublisher
Source code:

      src/main/java/json23plet/modules/DataPublisher.java
A simple module to publish rdf module into file.
As described earlier the output reflect the input directory recursively, json23plet init the DataPublisher before calling to your generator, by using the DataPublisher.publish function you specify the output file extenstions and format.

### Generators
As mentioned above, json23plet know to take a specific user generator and run it.
By using this, you can generate a new RDF triplet file from your json file.

#### Usage
1. Write your own generator, a generator have to extends *Generator* class and to implament the *generate* function.<br/>
      The code of *generate* is typically looks like this:

            for (Json j : json().getAsArray(propertyOfJsonArray)) {
                  triplet()
                        .subject(j.value("uri"))
                        .predicate(j.value("key"))
                        .object(s); // s is an object in ontology.java class
            }
      (In this example we loaded the parsed json file and for each json in the list we create one triplet).<br />
2. Drop your generator in:
       
            src/main/java/json23plet/generators
3. Rebuild using:
 
            ./json23plet.sh -b
And now you can run your new generator.

### The basic generator
Source code:

      src/main/java/json23plet/generators/customGenerators/BasicJsonGenerator.java

To simplify the using and to avoid creating new generator for each type of json we build the BasicJsonGenerator.<br/>
This generator assume you created a json files with a specific format, and by activating it on thos files it will generate a triplets generically and independently on their content.

#### Usage
##### Json files format
To use the basic generator you have to create your json in the following format:

    {
        "subjects" : [
            { "uri" : subjectUri,
              Property : Object, // the object can be also list of objects : [object1, object2, ...] 
              ...
            },
            ....
        ]
    }

Now simply run:

      ./json23plet.sh -generate basic inputDir

### RegexGenerator
Source code:

      src/main/java/json23plet/generators/regexGenerators/BaseRegexGenerator.java
      src/main/java/json23plet/generators/regexGenerators/IRegExGenerator.java
The basic generator is a powerfool  and generic tool for every data you wish to generate, but sometimes some jsons needs a special treatment.<br/>
One possible solution is to write a speicial generator that will handle thos cases, but we developed a much comfortable framework to do it.<br/>
Lets start with an example:<br/>
Assume we want that every triplet who its uri starts with "jbr:tanach*" will also contains the [rdf:type, jbo:Tanach] triplet, the regexGenerator framework allow you to do that easily.<br/>
a regex generator checks for every json object of the input if it match to some rule, and if so he process it and creating a new triplet for this json object.<br/>

#### Build a regex generator
1. Build myRegexGeneraor class, the class have to extends the BaseRegexGenerator class.
1. Implemant the abstruct methods as describe in the  [code documentation](https://techniontdk.github.io/jbs-json23plet/).
1. Drop your generator in **src/main/java/json23plet/generators/regexGenerators** and rebuild using:

            ./json23plet.sh -b
Now you can run your generaote the same [way](README.md#run-a-single-generator) as regular generators.
      
### JsonValidator
Source code:

      src/main/java/json23plet/JsonValidators/JsonValidator.java
      src/main/java/json23plet/JsonValidators/IJsonValidator.java
To validate your jsons before generate them we build a framework that allow you to define and implement any validation you wish in a simple way.
It technolify is much like the [regexGenerators](README.md#regexgenertor).

#### Build a jsonValidator
1. Create your myValidator class, the class have to extends the JsonValidator class.
1. Implemant the abstruct methods as describe in the  [code documentation](https://techniontdk.github.io/jbs-json23plet/).

#### Error level
The action performed on error detection depends on errorLevel (define in *config.json*):<br/>
* low - nothing happen
* medium - error will display while detected
* high - on error json23plet will stop
To set the error level see [here](README.md#edit-configuration-of-global-setting)

#### Usage
A tipically use will look like this:

      public void generate() {
        JsonValidator v = new PsukimTagsValidator();
        v.registerValidators();
        try {
            v.validateSingleJson(Json.json());
        } catch (JsonValidator.JsonValidatorException e) {
            e.printStackTrace();
        }
        for (Json j : json().getAsArray("subjects")) {
            String subjectUri = j.value(URI);
            for (Json tag : j.getAsArray("tags")) {
                triplet()
                        .subject(subjectUri)
                        .predicate(JBO_P_MENTIONS)
                        .object(tag.value(URI));
            }
        }
        DataPublisher.publish("", "." + getID() + ".ttl", "TURTLE");
    }
Here we validate our json file before generating it.<br/>
As described in the  [code documentation](https://techniontdk.github.io/jbs-json23plet/) you can also validate only one json object each time.
