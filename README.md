# Overview

This project uses the Geode `org.apache.geode.examples.security.ExampleSecurityManager` as example.   

# How to Run
```$bash
cd scripts
./generateCerts.sh geode
./startGeode.sh
cd ..
./gradlew test --rerun-tasks -i
cd scripts
./shutdownGeode.sh
./clearGeodeData.sh
```