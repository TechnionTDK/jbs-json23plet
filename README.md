# json23plet
A tool for creating and running generators of triplets (in ttl format) from given jsons files.

## Installation

1. install maven on your machine. to do so, type mvn in your command line and follow instructions

1. git clone jbs-text repository to a directory

1. git clone jbs-json23plet repository to a directory

1. run json23plet.sh with the appropriate params.

1. on first use the project will install on the working dir, to rebuild it use -b as first param

## usage

##### Init 
* run json23plet.sh -init [-b] myOutputDir

##### run existing single generator:
*  run json23plet.sh [-b] -generate generatorName dataInputRootDir
*  note that the input dir have to be absolute or relative to the working directory (jbs-json23plet) 

##### run new single generator:
1. create your generator and drop it in src/main/java/json23plet/generators directory
1. run json23plet.sh -b -generate generatorName dataInputRootDir

##### create new ontology from json file:
1. drop myOntology.json in ontologies/json
1. run json23plet.sh [-b] -ontology myOntology
1. the ttl file will created in ontologies/ttl
1. the myOntology.java will created in src/main/java/json23plet/ontologies

##### run all generators:
1. config the generators you want to run in src/main/java/json23plet/generators/config.json
2. run json23plet.sh [-b] -generateAll





