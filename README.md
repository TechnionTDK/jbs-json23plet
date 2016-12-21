# json23plet
A tool for creating and running generators of triplets (in ttl format) from given jsons files.

## Installation

1. install maven on your machine. to do so, type mvn in your command line and follow instructions

1. git clone jbs-text repository to a directory

1. git clone jbs-json23plet repository to a directory

1. go into the jbs-json23plet directory

1. run "./json23plet.sh -b" to build the json23plet project

1. in order to rebuild the project run again the "./json23plet.sh -b" command

## Usage

##### Init the project output directory
* run "./json23plet.sh -init \<myOutputDir\>"

##### Run an existing generator:
* run "./json23plet.sh -generate \<generatorName\> \<dataInputRootDir\>" 

##### Run multiple generators:
1. config the generators that you want to run in jbs-json23plet/src/main/java/json23plet/generators/config.json
1. run "./json23plet.sh -generateAll"

##### Run a new generator:
1. create your generator and drop it in jbs-json23plet/src/main/java/json23plet/generators directory
1. run "./json23plet.sh -b" to rebuild the json23plet project
1. run "./json23plet.sh -generate \<generatorName\> \<dataInputRootDir\>"

##### Create a new ontology from a json file:
1. drop myOntology.json in jbs-json23plet/ontologies/json
1. run "./json23plet.sh -ontology \<myOntology\>"
1. the myOntology.ttl file will be created in jbs-json23plet/ontologies/ttl
1. the myOntology.java will be created in jbs-json23plet/src/main/java/json23plet/ontologies





