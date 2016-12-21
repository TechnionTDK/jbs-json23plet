#!/bin/bash
cd $(dirname "$0")
echo "Start activate maven"
echo "Build Json23plet project"
mvn install
echo -n "Run Json23plet whith args: "
echo "$@"
java -jar ./target/json23plet-1.0-jar-with-dependencies.jar "$@"