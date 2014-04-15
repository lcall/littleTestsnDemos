#!/bin/bash -eux

rm /tmp/g1.log

mvn clean package && java -jar target/a1-jar-1.0-SNAPSHOT.jar 

echo;echo;echo
echo "log file contents:"
cat /tmp/g1.log
echo;echo
echo "json file contents:"
cat /tmp/some-json.log
