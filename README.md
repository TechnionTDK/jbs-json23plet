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

1. Git clone jbs-json23plet repository to a directory.

        git clone git@github.com:TechnionTDK/jbs-json23plet.git

1. Go into jbs-json23plet/ and build the project.

        ./json23plet.sh -b
   This command will call `mvn install`.
      
1. Untrack the configuration file from git.

        git update-index --assume-unchanged config.json
   This command will make your local `config.json` untracked from the GitHub repository.

## Json23plet configuration
Json23plet has some configurations, all defined in `config.json` file.<br/>
The default configurations are:

      {
        "setting": {
          "globalSetting": {
            "genOutputDir": "output", // the output root dir
            "errorLevel": "low",
            "genInputDir": "" 
          },
          "generators": [] // configuration scheme to run multile generators
        }
      }
      
## json23plet commands

### Build the tool

      ./json23plet.sh -b
This command will call `mvn install`.
      
### Initialize the project directories
The command initializes the direcories tree of the project.

      ./json23plet.sh -init
This command creates `ontologies/json`, `ontologies/ttl` and `src/test/testsFiles` directories. During running, the tool assumes those directories exist.

### Configure the output root directory
The command sets up the output root directory and saves it in `config.json` file.<br/>
The input directories tree will be reflected in this directory and will have an identical directory structure.

      ./json23plet.sh -config outputDir=myOutputDir

### Configure the default input directory
The command sets up the default input directory and saves it in `config.json` file.<br/>

      ./json23plet.sh -config genInputDir=myInpuputDir

### Run a single generator
The command runs a specific [generator](README.md#generators) on a specific input directory (or a specific single file) recursively.

      ./json23plet.sh -generate generatorName dataInputRootDir
or

      ./json23plet.sh -generate generatorName inputFile.json
If you are using the [basic](README.md#the-basic-generator) generator, specify `basic` as the generator name.
      
      ./json23plet.sh -generate basic dataInputRootDir
      
This command can also be used without specifying the input directory.

      ./json23plet.sh -generate generatorName
In this case it runs `generatorName` on the default input directory that was [configured](README.md#configure-the-default-input-directory).

### Run multiple generators
Json23plet enables you to configure a scheme of running multiple different generators on diffrent directories (or files). <br/>
To do so, you need to [configure your scheme](README.md#add-and-edit-configuration-for-a-generator) and run this command:
      
      ./json23plet.sh -generateAll


### Create and install a new generator
***This is not a command but a basic part of working with the Json23plet tool***

json23plet enables you to write and deploy your own generator for unique json formats.<br/>
*To do so:*<br/>
* Create your [generator](README.md#generators) and drop it in `src/main/java/json23plet/generators/customGenerators` directory.<br/>
* For [regexGenerator](README.md#regexgenerator), drop it in `src/main/java/json23plet/generators/regexGenerators` directory.<br/>
* Rebuild the project using:

        ./json23plet.sh -b
            
* Now you can [run](README.md#run-a-single-generator) your generator.

### Create and update an ontology
json23plet uses the same logic to generate ontology.ttl files. Therefore, to generate a new ontology it's required to define a json file with the ontolgy and run the json23plet ontology generator.

1. Define the ontology in myOntology.json by using [this format](README.md#ontologies).
1. To create the ontology run:

            ./json23plet.sh -ontology myOntology

    A `myOntology.ttl` file will be created in `ontologies/ttl`.<br/>
    A generated class `myOntology.java` will be created in `src/main/java/json23plet/ontologies`. This class represents the ontology as a java object which enables you to use it in a convenient way.
1. Rebuild using:

            ./json23plet.sh -b 

***Note:*** While changing an existing ontology, there might be some generators that use the old `ontology.java` properties. Therefore, be aware of compilation errors.

### Add and edit configuration for a generator
When running the `generateAll` command, the tool will run all the configured generators in the `config.json` file.<br/>
For example, to add(remove) a generator named `myGen` to the scheme of `generateAll` use:

        
        ./json23plet -config  genName=myGen inputPath=MyGenInputPath active=true(false)
This command will add the following lines to the `config.json` file:
       
       {
          "generators": [
                            {
                                "genName": "myGen",
                                "inputPath":"myGenINputPath",
                                "active": "true"("false")
                            }
                        ] 
      }
Be aware that it is possible to update the schema manually.
For more infomartion, see [setting](README.md#json23plet-configuration).

### Edit configuration of global setting
json23plet uses some [settings](README.md#json23plet-configuration) while running.
* To configure output dir, run:
      
            ./json23plet -config outputDir=myOutputDir
  
* To configure [error level](README.md#jsonvalidator), run:

            ./json23plet -config errorLevel=level
 
## Testing
json23plet has a simple and efficient testing framework.
To test your generator:

1. Add myGenerator.input.json to `src/test/testsFiles`.
1. Add myGenerator.expected.ttl to `src/test/testsFiles`.

To run the tests, rebuild the project using:

      ./json23plet.sh -b 
maven will run the tests.<br/>
It is also possible to run the tests without rebuild as explained in this [guide](http://junit.sourceforge.net/doc/faq/faq.htm#running_4).

## Development enviorment
It's preferred to develope and use the tool on a Linux machine where you can run the tool through the command line.<br/>
Working on Windows requires an IDE.

### Devloping with an IDE 
It's recommeded to use [IntelliJ IDEA](https://www.jetbrains.com/idea/), and our guide will focus on it.

### Getting started with Intellij
* *Clone the repository*<br/>
     Go to File->New->Project From Version Control->GitHub. In the repository field, enter `git@github.com:TechnionTDK/jbs-json23plet.git`and click `Clone`.<br/>

* *Configure tool arguments*<br/>
     1. Right click src/main/java->Mark Directory as->Sources Root.
     1. Build the project
     1. Right click src/main/java/Json23plet.java->Create 'Json23plet.main()' and configure the tool arguments.
     1. Run or debug the tool.


## Development and maintenance
In this section, we will review the code components for future maintenance.<br/>
See the [code documentation](https://techniontdk.github.io/jbs-json23plet/).

### Overview
json23plet is an engine which runs user defined generators. <br/>
It uses reflection and the Apache jena library and loads file statically.<br/>
By specifing a generator name, the tool uses reflection mechanism to activate the generator on the input directory. <br/>
The tool contains some modules which simplify working with it.

### Ontologies
Source code:
      
      src/main/java/json23plet/generators/ontologyGenerator/OntologyClassGenerator.java
      src/main/java/json23plet/generators/ontologyGenerator/OntologyGenerator.java
      src/main/java/json23plet/generators/ontologyGenerator/OntologyTTLGenerator.java 
As mentioned before, json23plet generates ontologies in the same way of regular json data. The tool contains a special ontology generator which has two purposes:

1. Create the ttl files of the ontology.
1. Create a java class which will enable you to reference your ontology definitions as java objects.

The generator requires the ontology json to be in a very specific format:<br/>

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
              "uri" : "jbo:Tanach", //<-----requierd predicate//
              "rdf:type" : "owl:Class", //<----required predicate//
              "rdfs:label" : "Tanach",
              "rdfs:subClassOf" : "owl:Thing"
              ...
            },
            ...
        ]
    }
(The uri and rdf:type predicates are required).

#### Usage example
* Drop your `myOntology.json` file in `ontologies/json` and run:

        ./json23plet -ontology myOntology
      
  After generating, an `myOntology.ttl` file will be created in `ontologies/ttl`.<br/>
  ***Note:*** Do not remove this file, because json23plet uses it to load the ontology during generating new ttl files in the project.

  The `myOntology.java` class file will be created in `src/main/java/json23plet/ontologies`. This file contains some definitions of the ontology and can be used during writing a new generator.<br/>

  ***Note:*** After changing an existing ontology there might have some generators which are still using the old ontology definitions. If so, they need to be updated or they might cause compilation errors.

* Rebuild the project using:

        ./json23plet.sh -b

### Json
Source code file:

      src/main/java/json23plet/modules/Json.java
Json is a module for parsing json files.<br/>
json23plet engine assumes that you use this module, but it's not a mandatory.<br/>
The engine loads statically (per thread) the current working json file, and it can be accessed (in the generator) as a parsed json through Json module.

#### Usage example

    Json
    .json() // return the current working file already parsed.
    .getAsSomeObject("property") // read the code documentation for more details.

### Triplet
Source code file:

      src/main/java/json23plet/modules/Triplet.java
The Triplet module is a simple wrapper for the [Apache Jena library](https://jena.apache.org/).<br/>
json23plet engine loads an RDF model before calling the generator and enables adding statements to that model by using the Triplet module.

#### Usage example

    Triplet
    .triplet()
    .subject("subjectUri")
    .preficate(Predicate) // taken from Ontology.java class might be also uri.
    .object(Resource) // taken from Ontology.java class might be also uri.
    
### Regex 
Source code file:

      src/main/java/json23plet/modules/Regex.java
A simple implementation of wrapper to java regex

#### Usage example
      
      regex(a.*) // load the regex
      .match("abc") // check if the string match to the regex.

### DataPublisher
Source code file:

      src/main/java/json23plet/modules/DataPublisher.java
A simple module to publish RDF model into ttl file

As described, the output directory reflects the input directory. The tool initiates the DataPublisher before calling the generators. 

### Generators
As mentioned, json23plet runs a specific generator given by the generator name.
This enables generating new RDF triplet filed from json files.

#### Usage example
1. Write your own generator. A generator needs to extend `Generator` class and implement the `generate` method.<br/>
      The `generate` code typically looks like this:

            for (Json j : json().getAsArray(propertyOfJsonArray)) {
                  triplet()
                        .subject(j.value("uri"))
                        .predicate(j.value("key"))
                        .object(s); // s is an object in ontology.java class
            }
      (In this example, we loaded the parsed json file and for each json in the list we create one triplet).<br/>
1. Drop the `generator.java` in `src/main/java/json23plet/generators` 
1. Rebuild the tool by using:
 
            ./json23plet.sh -b

### The basic generator
Source code file:

      src/main/java/json23plet/generators/customGenerators/BasicJsonGenerator.java
To simplify using the tool and to avoid creating a new generator for each type of json file, the tool has a `BasicJsonGenerator`.<br/>
This generator assumes the json file has a specific format, and by activating it on this file it will generate triplets generically without any more knowledge about the json file format.

#### Basic Json files format
To use the basic generator, you need to create the json file in the following format:

    {
        "subjects" : [
            { "uri" : subjectUri,
              Property1 : Object1, // the object can be also list of objects : [object1, object2, ...] ,
              Property2 : Object2, // the object can be also list of objects : [object1, object2, ...] 
              ...
            },
            ....
        ]
    }

#### Usage example
Simply run:

      ./json23plet.sh -generate basic inputDir

### RegexGenerator
Source code files:

      src/main/java/json23plet/generators/regexGenerators/BaseRegexGenerator.java
      src/main/java/json23plet/generators/regexGenerators/IRegExGenerator.java
The basic generator is a powerful and generic tool for any data you wish to generate, but sometimes some jsons need special treatment.<br/>
One possible solution is to write a special generator that will handle those cases, but the tool has an easier way to do it by using this module.<br/>
Let's start with an example:<br/>
Assume we want that every json object whose `uri` starts with `"jbr":"tanach"` to have a triplet with `rdf:type, jbo:Tanach` triplet. The regexGenerator framework enables you to do this easily.<br/>
A regex generator checks for every json object in the input if it matchs to the rule, and if so, it adds the appropriate triplet to the model.

#### Build a regex generator
1. Build myRegexGeneraor class, the class needs to extend the BaseRegexGenerator class.
1. Implement the abstract methods as described in the [code documentation](https://techniontdk.github.io/jbs-json23plet/json23plet/generators/regexGenerators/BaseRegexGenerator.html).
1. Drop your generator in `src/main/java/json23plet/generators/regexGenerators` and rebuild the tool by:

            ./json23plet.sh -b
1. Run the generator in the [regular way](README.md#run-a-single-generator).
      
### JsonValidator
Source code files:

      src/main/java/json23plet/JsonValidators/JsonValidator.java
      src/main/java/json23plet/JsonValidators/IJsonValidator.java
To validate the json input files before generating triplets from them, the tool contains a framework that enables defining and implementing any validation in a simple way.
Its mechanism is much like the [regexGenerators](README.md#regexgenertor).

#### Build a jsonValidator
1. Create `myValidator` class, the class has to extend the `JsonValidator` class.
1. Implement the abstract methods as describe in the [code documentation](https://techniontdk.github.io/jbs-json23plet/json23plet/JsonValidators/JsonValidator.html).

#### Error level
The action that the tool operates on error detection depends on `errorLevel` value (which is defined in `config.json`):<br/>

* `low` - ignore

* `medium` - display the error

* `high` - stop running

To set the error level, [see here](README.md#edit-configuration-of-global-setting).

#### Usage example
A typical usage looks like this:

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
In the example, we validate our json file before generating it.<br/>
As described in the [code documentation](https://techniontdk.github.io/jbs-json23plet/json23plet/JsonValidators/JsonValidator.html), you can also validate only one json object each time.
