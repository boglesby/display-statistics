The logstatistics.sh script logs the most useful statistics.

Run it like:

./logstatistics.sh statistics.gfs

Example output is:

-------
VMStats
-------
fdLimit fds: max=524288
fdsOpen fds: min=418 max=2888 last=1583
processCpuTime nanoseconds/sec: min=0 max=11751004016.06 last=1003550185.29
threads threads: min=43 max=2528 last=1183

-----------------
VMMemoryPoolStats
-----------------
CMS Old Gen-Heap memory
	currentMaxMemory bytes: min=263066746880 max=263066746880 last=263066746880
	currentUsedMemory bytes: min=0 max=262917264456 last=192262082880

---------
VMGCStats
---------
ConcurrentMarkSweep
	collections operations/sec: min=0 max=1 last=0
	collectionTime milliseconds/sec: min=0 max=615.28 last=0
ParNew
	collections operations/sec: min=-1 max=2 last=0
	collectionTime milliseconds/sec: min=0 max=1421 last=0

-----------
StatSampler
-----------
delayDuration milliseconds: min=0 max=5926 last=992
jvmPauses jvmPauses/sec: min=0 max=0.17 last=0.02

--------------------
ResourceManagerStats
--------------------
heapCriticalEvents events: min=0 max=2 last=2
evictionStartEvents events: min=0 max=2 last=2

----------------
LinuxSystemStats
----------------
cachedMemory megabytes: min=169276 max=201563 last=197102
freeMemory megabytes: min=536 max=32922 last=4470
physicalMemory megabytes: max=484558
cpus items: min=16 max=16 last=16
cpuActive %: min=1 max=100 last=7
loadAverage1 threads: min=0.44 max=25.79 last=1.66
loadAverage5 threads: min=0.41 max=11.16 last=2.51
loadAverage15 threads: min=1.04 max=6.02 last=2.64
recvBytes bytes/sec: min=0 max=157383286 last=79554.84
recvDrops packets/sec: min=0 max=0 last=0
xmitBytes bytes/sec: min=0 max=283933613 last=48239.58
xmitDrops packets/sec: min=0 max=0 last=0

-----------------
DistributionStats
-----------------
nodes nodes: min=2 max=3 last=3
functionExecutionThreads threads: min=1 max=48 last=11
functionExecutionQueueSize messages: min=0 max=0 last=0
highPriorityThreads threads: min=2 max=9 last=4
highPriorityQueueSize messages: min=0 max=0 last=0
partitionedRegionThreads threads: min=1 max=1 last=1
partitionedRegionQueueSize messages: min=0 max=0 last=0
processingThreads threads: min=1 max=373 last=3
overflowQueueSize messages: min=0 max=0 last=0
replyTimeouts timeouts/sec: min=0 max=0 last=0
replyWaitsInProgress operations: min=0 max=16 last=2
receiversTO threads: min=1 max=60 last=16
sendersTO sockets: min=0 max=209 last=18
senderTimeouts expirations/sec: min=-1 max=83 last=0.02
suspectsReceived messages/sec: min=0 max=0.02 last=0.02
suspectsSent messages/sec: min=0 max=0 last=0

----------------
CacheServerStats
----------------
currentClients clients: min=0 max=114 last=100
currentClientConnections sockets: min=0 max=1919 last=660
closeConnectionRequests operations/sec: min=-1 max=202 last=0.03
connectionsTimedOut connections/sec: min=0 max=50 last=0.12
threadQueueSize connections: min=0 max=0 last=0

--------------
CachePerfStats
--------------
cacheListenerCallsInProgress operations: min=0 max=0 last=0
cacheWriterCallsInProgress operations: min=0 max=0 last=0
loadsInProgress operations: min=0 max=0 last=0
