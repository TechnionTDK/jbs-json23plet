#!/bin/bash
cd $(dirname "$0")
if [[ $1 == "-b" ]] || [[ ! -d "./target" ]]; then
    mvn install
fi
jargs="$@"
if [[ $1 == "-b" ]]; then
    jargs=${@:2}
fi
java -jar ./target/json23plet-1.0-jar-with-dependencies.jar ${jargs[*]}