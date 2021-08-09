# Display Statistics
## Description
This project provides several ways to display statistics.


The **GetStatisticValueFunction** retrieves the value of a specific statistic.

The **LogAllStatisticValuesFunction** logs the values of all statistics.

## Initialization
Modify the **GEODE** environment variable in the *setenv.sh* script to point to a Geode installation directory.

## Build
Build the Spring Boot Client Application and Geode Server Function classes using gradle like:

```
./gradlew clean jar bootJar
```
## Run Example
### Start and Configure Locator and Servers
Start and configure the locator and 3 servers using the *startandconfigure.sh* script like:

```
./startandconfigure.sh
```
### Create Entries
Run the client to create Trade instances using the *runclient.sh* script like below.

```
./runclient.sh load-region 10000
```
The parameters are:

- operation (e.g. load-region)
- number of entries (e.g. 10000)

### Get Specific Statistic Value
Run the client to get a specific statistic value using the *runclient.sh* script like below.

```
./runclient.sh get-statistic VMMemoryPoolStats 'CMS Old Gen-Heap memory' currentUsedMemory
```

```
./runclient.sh get-statistic PartitionedRegionStats /Trade dataStoreBytesInUse
```

```
./runclient.sh get-statistic PartitionedRegionStats /Trade dataStoreEntryCount
```
The parameters are:

- operation (e.g. get-statistic)
- category (e.g. PartitionedRegionStats)
- instance (e.g. /Trade)
- statistic (e.g. dataStoreBytesInUse)

### Log All Statistics Values
Run the client to log all statistics values in each server using the *runclient.sh* script like below.

```
./runclient.sh log-all-statistics
```

### Shutdown Locator and Servers
Execute the * shutdownsites.sh* script to shutdown the servers and locators in both WAN sites like:

```
./shutdownsites.sh
```
### Remove Locator and Server Files
Execute the *cleanupfiles.sh* script to remove the server and locator files like:

```
./cleanupfiles.sh
```
## Example Output
### Start and Configure Locator and Servers
Sample output from the *startandconfigure.sh* script is:

```
./startandconfigure.sh
1. Executing - start locator --name=locator

....................
Locator in <working-dir>/locator on xxx.xxx.x.x[10334] as locator is currently online.
Process ID: 8557
Uptime: 26 seconds
Geode Version: 1.13.1-build.0
Java Version: 1.8.0_151
Log File: <working-dir>/locator/locator.log
JVM Arguments: <jvm-arguments>
Class-Path: <classpath>

Successfully connected to: JMX Manager [host=xxx.xxx.x.x, port=1099]

Cluster configuration service is up and running.

2. Executing - set variable --name=APP_RESULT_VIEWER --value=any

Value for variable APP_RESULT_VIEWER is now: any.

3. Executing - configure pdx --read-serialized=true --auto-serializable-classes=.*

read-serialized = true
ignore-unread-fields = false
persistent = false
PDX Serializer = org.apache.geode.pdx.ReflectionBasedAutoSerializer
Non Portable Classes = [.*]
Cluster configuration for group 'cluster' is updated.

4. Executing - start server --name=server-1 --server-port=0 --initial-heap=1g --max-heap=1g --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false

................
Server in <working-dir>/server-1 on xxx.xxx.x.x[54330] as server-1 is currently online.
Process ID: 8560
Uptime: 14 seconds
Geode Version: 1.13.1-build.0
Java Version: 1.8.0_151
Log File: <working-dir>/server-1/cacheserver.log
JVM Arguments: <jvm-arguments>
Class-Path: <classpath>

5. Executing - start server --name=server-2 --server-port=0 --initial-heap=1g --max-heap=1g --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false

.....................
Server in <working-dir>/server-2 on xxx.xxx.x.x[54366] as server-2 is currently online.
Process ID: 8563
Uptime: 14 seconds
Geode Version: 1.13.1-build.0
Java Version: 1.8.0_151
Log File: <working-dir>/server-2/cacheserver.log
JVM Arguments: <jvm-arguments>
Class-Path: <classpath>

6. Executing - start server --name=server-3 --server-port=0 --initial-heap=1g --max-heap=1g --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false

...............
Server in <working-dir>/server-3 on xxx.xxx.x.x[54397] as server-3 is currently online.
Process ID: 8566
Uptime: 10 seconds
Geode Version: 1.13.1-build.0
Java Version: 1.8.0_151
Log File: <working-dir>/server-3/cacheserver.log
JVM Arguments: <jvm-arguments>
Class-Path: <classpath>

7. Executing - list members

Member Count : 4

  Name   | Id
-------- | -------------------------------------------------------------
locator  | xxx.xxx.x.x(locator:8557:locator)<ec><v0>:41000 [Coordinator]
server-1 | xxx.xxx.x.x(server-1:8560)<v1>:41001
server-2 | xxx.xxx.x.x(server-2:8563)<v2>:41002
server-3 | xxx.xxx.x.x(server-3:8566)<v3>:41003

8. Executing - create region --name=Trade --type=PARTITION_REDUNDANT

 Member  | Status | Message
-------- | ------ | -------------------------------------
server-1 | OK     | Region "/Trade" created on "server-1"
server-2 | OK     | Region "/Trade" created on "server-2"
server-3 | OK     | Region "/Trade" created on "server-3"

Cluster configuration for group 'cluster' is updated.

9. Executing - list regions

List of regions
---------------
Trade

10. Executing - deploy --jar=server/build/libs/server-0.0.1-SNAPSHOT.jar

 Member  |       Deployed JAR        | Deployed JAR Location
-------- | ------------------------- | ---------------------------------------------------
server-1 | server-0.0.1-SNAPSHOT.jar | <working-dir>/server-1/server-0.0.1-SNAPSHOT.v1.jar
server-2 | server-0.0.1-SNAPSHOT.jar | <working-dir>/server-2/server-0.0.1-SNAPSHOT.v1.jar
server-3 | server-0.0.1-SNAPSHOT.jar | <working-dir>/server-3/server-0.0.1-SNAPSHOT.v1.jar

11. Executing - list functions

 Member  | Function
-------- | -----------------------------
server-1 | GetStatisticValueFunction
server-1 | LogAllStatisticValuesFunction
server-2 | GetStatisticValueFunction
server-2 | LogAllStatisticValuesFunction
server-3 | GetStatisticValueFunction
server-3 | LogAllStatisticValuesFunction

************************* Execution Summary ***********************
Script file: startandconfigure.gfsh

Command-1 : start locator --name=locator
Status    : PASSED

Command-2 : set variable --name=APP_RESULT_VIEWER --value=any
Status    : PASSED

Command-3 : configure pdx --read-serialized=true --auto-serializable-classes=.*
Status    : PASSED

Command-4 : start server --name=server-1 --server-port=0 --initial-heap=1g --max-heap=1g --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false
Status    : PASSED

Command-5 : start server --name=server-2 --server-port=0 --initial-heap=1g --max-heap=1g --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false
Status    : PASSED

Command-6 : start server --name=server-3 --server-port=0 --initial-heap=1g --max-heap=1g --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false
Status    : PASSED

Command-7 : list members
Status    : PASSED

Command-8 : create region --name=Trade --type=PARTITION_REDUNDANT
Status    : PASSED

Command-9 : list regions
Status    : PASSED

Command-10 : deploy --jar=server/build/libs/server-0.0.1-SNAPSHOT.jar
Status     : PASSED

Command-11 : list functions
Status     : PASSED
```
### Create Entries
Sample output from the *runclient.sh* script is:

```
./runclient.sh load-region 10000

 :: Spring Boot ::                (v2.4.3)

2021-08-07 13:10:57.595  INFO 13069 --- [           main] example.client.Client                    : Starting Client using Java 1.8.0_151 on barretts-macbook-pro with PID 13069 (/Users/boglesby/Dev/geode-examples/log-statistics/client/build/classes/java/main started by boglesby in /Users/boglesby/Dev/geode-examples/log-statistics/client)

2021-08-07 13:11:02.175  INFO 13069 --- [           main] example.client.Client                    : Started Client in 5.278 seconds (JVM running for 5.864)
2021-08-07 13:11:02.179  INFO 13069 --- [           main] example.client.service.TradeService      : Putting 10000 trades of size 1024 bytes
2021-08-07 13:11:02.459  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=0, cusip=NVS, shares=86, price=581.19)
2021-08-07 13:11:02.521  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=1, cusip=IBM, shares=47, price=321.18)
2021-08-07 13:11:02.561  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=2, cusip=MCD, shares=97, price=496.57)
2021-08-07 13:11:02.594  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=3, cusip=HD, shares=88, price=199.96)
2021-08-07 13:11:02.621  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=4, cusip=MCD, shares=52, price=293.05)
...
2021-08-07 13:11:17.184  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=9995, cusip=NKE, shares=27, price=59.54)
2021-08-07 13:11:17.185  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=9996, cusip=PFE, shares=53, price=582.72)
2021-08-07 13:11:17.186  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=9997, cusip=QCOM, shares=5, price=871.55)
2021-08-07 13:11:17.187  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=9998, cusip=WMT, shares=62, price=80.05)
2021-08-07 13:11:17.188  INFO 13069 --- [           main] example.client.service.TradeService      : Saved Trade(id=9999, cusip=MCD, shares=5, price=151.70)
2021-08-07 13:11:17.188  INFO 13069 --- [           main] example.client.service.TradeService      : Put 10000 trades of size 1024 bytes
```
### Get Specific Statistic Value
Sample output from the *runclient.sh* script is:

```
./runclient.sh get-statistic VMMemoryPoolStats 'CMS Old Gen-Heap memory' currentUsedMemory

2021-06-12 19:23:03.762  INFO 6100 --- [  main] example.client.Client : Starting Client ...
...  
2021-06-12 19:23:09.623  INFO 6100 --- [  main] example.client.Client : Started Client in 6.573 seconds (JVM running for 7.227)
2021-06-12 19:23:09.676  INFO 6100 --- [  main] example.client.Client : 
Statistic value for category=VMMemoryPoolStats; type=CMS Old Gen-Heap memory;statistic=currentUsedMemory
	member=192.168.1.8(server-1:5909)<v1>:41001; value=35,393,776
	member=192.168.1.8(server-2:5910)<v2>:41002; value=35,444,256
	member=192.168.1.8(server-3:5911)<v3>:41003; value=35,266,648
```

```
./runclient.sh get-statistic PartitionedRegionStats /Trade dataStoreBytesInUse

2021-06-12 19:25:11.157  INFO 6139 --- [  main] example.client.Client : Starting Client ...
... 
2021-06-12 19:25:17.032  INFO 6139 --- [  main] example.client.Client : Started Client in 6.562 seconds (JVM running for 7.303)
2021-06-12 19:25:17.098  INFO 6139 --- [  main] example.client.Client : 
Statistic value for category=PartitionedRegionStats; type=/Trade;statistic=dataStoreBytesInUse
	member=192.168.1.8(server-1:5909)<v1>:41001; value=7,258,774
	member=192.168.1.8(server-2:5910)<v2>:41002; value=7,340,782
	member=192.168.1.8(server-3:5911)<v3>:41003; value=7,257,758
```

```
./runclient.sh get-statistic PartitionedRegionStats /Trade dataStoreEntryCount

2021-06-12 19:26:35.074  INFO 6156 --- [  main] example.client.Client : Starting Client ...
...
2021-06-12 19:26:40.653  INFO 6156 --- [  main] example.client.Client : Started Client in 6.278 seconds (JVM running for 6.901)
2021-06-12 19:26:40.714  INFO 6156 --- [  main] example.client.Client : 
Statistic value for category=PartitionedRegionStats; type=/Trade;statistic=dataStoreEntryCount
	member=192.168.1.8(server-1:5909)<v1>:41001; value=6,642
	member=192.168.1.8(server-2:5910)<v2>:41002; value=6,717
	member=192.168.1.8(server-3:5911)<v3>:41003; value=6,641
```
### Log All Statistics Values
Sample output from the *runclient.sh* script is:

```
./runclient.sh log-all-statistics

2021-08-07 15:36:34.282  INFO 13878 --- [           main] example.client.Client                    : Starting Client ...

2021-08-07 15:36:38.761  INFO 13878 --- [           main] example.client.Client                    : Started Client in 5.087 seconds (JVM running for 5.66)
2021-08-07 15:36:38.768  INFO 13878 --- [           main] example.client.Client                    : Logged all statistics
```
### Shutdown Locator and Servers
Sample output from the *shutdownall.sh* script is:

```
./shutdownall.sh 

(1) Executing - connect

Connecting to Locator at [host=localhost, port=10334] ..
Connecting to Manager at [host=192.168.1.11, port=1099] ..
Successfully connected to: [host=192.168.1.11, port=1099]


(2) Executing - shutdown --include-locators=true

Shutdown is triggered
```
