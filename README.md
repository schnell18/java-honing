# Introduction
Java programming playground.
Practise some basic Java programming topics like:
- Thread
- Generics
- Annotation
- Java Collection Framework
- Algorithm

## MemoryHog
This example is to demonstrate the garbage collection internal. To see
this demo, launch the following command under to project root directory:

~~~~bash
java -cp build/classes/main    \
     -Xms15M                   \
     -Xmx15M                   \
     -Xloggc:hog_gc.log        \
     -XX:+UseGCLogFileRotation \
     -XX:NumberOfGCLogFiles=5  \
     -XX:GCLogFileSize=81920   \
     org.home.hone.gc.MemoryHog 1>/dev/null &

# get the PID of MemoryHog program display live GC stat
pid=$(jcmd -l | grep org.home.hone.gc.MemoryHog | awk '{print $1}')
jstat -gcutil $pid 250
~~~~
