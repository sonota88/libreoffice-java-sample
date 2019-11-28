#!/bin/bash

classpath="/usr/lib/libreoffice/program/classes/juh.jar"
classpath="/usr/lib/libreoffice/program/classes/ridl.jar:${classpath}"
classpath="/usr/lib/libreoffice/program/:${classpath}"
classpath="/usr/share/libreoffice/program/classes/unoil.jar:${classpath}"
classpath="target/libo-sample-a-0.0.1-SNAPSHOT.jar:${classpath}"

java -cp "$classpath" sample.Main \
  ./sample.fods
