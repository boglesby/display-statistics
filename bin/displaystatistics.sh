#!/bin/bash
. ./setenv.sh
IFS=$'\n'

function get_stat {
  local stat_line=`./systemadmin.sh stats $2 -archive=$1`
  echo "$stat_line"
}

function log_stats {
	RESULT=`get_stat $1 $2`
	for i in $RESULT;
	do
		INFO_INDEX=`echo $i | awk '{print index($1, "info")}'`
		if [ "$INFO_INDEX" -eq 0 ]; then
			STAT_INDEX=`echo $i $2 | awk '{print index($1, "$2")}'`
			if [ "$STAT_INDEX" -eq 0 ]; then
				STAT_NAME=`echo $i | awk '{print $1}'`
				if [[ "$STAT_NAME" =~ $3 ]]; then
					UNIT=`echo $i | awk '{print $2}'`
					MIN=`echo $i | awk '{print $4}'`
					MAX=`echo $i | awk '{print $5}'`
					LAST=`echo $i | awk '{print $8}'`
					echo -e '\t'$STAT_NAME $UNIT $MIN $MAX $LAST
				fi
			fi
		else
			TYPE=`echo $2 | awk -F: '{print $1}'`
			echo $TYPE
		fi
	done
}

function log_stat {
	RESULT=`get_stat $1 $2`
	STAT=`echo $RESULT | awk '{print $14}'`
	UNIT=`echo $RESULT | awk '{print $15}'`
	MIN=`echo $RESULT | awk '{print $17}'`
	MAX=`echo $RESULT | awk '{print $18}'`
	LAST=`echo $RESULT | awk '{print $21}'`
	echo $STAT $UNIT $MIN $MAX $LAST
}

function dump_max_stat {
	RESULT=`get_stat $1 $2`
	STAT=`echo $RESULT | awk '{print $14}'`
	UNIT=`echo $RESULT | awk '{print $15}'`
	MAX=`echo $RESULT | awk '{print $18}'`
	echo $STAT $UNIT $MAX
}

echo '-------'
echo 'VMStats'
echo '-------'
dump_max_stat $1 :VMStats.fdLimit
log_stat $1 :VMStats.fdsOpen
log_stat $1 :VMStats.processCpuTime
log_stat $1 :VMStats.threads

echo
echo '-----------------'
echo 'VMMemoryPoolStats'
echo '-----------------'
log_stats $1 'CMS Old Gen-Heap memory:VMMemoryPoolStats' '^(currentMaxMemory|currentUsedMemory)$'

echo
echo '---------'
echo 'VMGCStats'
echo '---------'
log_stats $1 'ConcurrentMarkSweep:VMGCStats' '^(collections|collectionTime)$'
log_stats $1 'ParNew:VMGCStats' '^(collections|collectionTime)$'

echo
echo '-----------'
echo 'StatSampler'
echo '-----------'
log_stat $1 :StatSampler.delayDuration
log_stat $1 :StatSampler.jvmPauses

echo
echo '--------------------'
echo 'ResourceManagerStats'
echo '--------------------'
log_stat $1 :ResourceManagerStats.heapCriticalEvents
log_stat $1 :ResourceManagerStats.evictionStartEvents

echo
echo '----------------'
echo 'LinuxSystemStats'
echo '----------------'
log_stat $1 :LinuxSystemStats.cachedMemory
log_stat $1 :LinuxSystemStats.freeMemory
dump_max_stat $1 :LinuxSystemStats.physicalMemory
log_stat $1 :LinuxSystemStats.cpus
log_stat $1 :LinuxSystemStats.cpuActive
log_stat $1 :LinuxSystemStats.loadAverage1
log_stat $1 :LinuxSystemStats.loadAverage5
log_stat $1 :LinuxSystemStats.loadAverage15
log_stat $1 :LinuxSystemStats.recvBytes
log_stat $1 :LinuxSystemStats.recvDrops
log_stat $1 :LinuxSystemStats.xmitBytes
log_stat $1 :LinuxSystemStats.xmitDrops

echo
echo '-----------------'
echo 'DistributionStats'
echo '-----------------'
log_stat $1 :DistributionStats.nodes
log_stat $1 :DistributionStats.functionExecutionThreads
log_stat $1 :DistributionStats.functionExecutionQueueSize
log_stat $1 :DistributionStats.highPriorityThreads
log_stat $1 :DistributionStats.highPriorityQueueSize
log_stat $1 :DistributionStats.partitionedRegionThreads
log_stat $1 :DistributionStats.partitionedRegionQueueSize
log_stat $1 :DistributionStats.processingThreads
log_stat $1 :DistributionStats.overflowQueueSize
log_stat $1 :DistributionStats.replyTimeouts
log_stat $1 :DistributionStats.replyWaitsInProgress
log_stat $1 :DistributionStats.receiversTO
log_stat $1 :DistributionStats.sendersTO
log_stat $1 :DistributionStats.senderTimeouts
log_stat $1 :DistributionStats.suspectsReceived
log_stat $1 :DistributionStats.suspectsSent

echo
echo '----------------'
echo 'CacheServerStats'
echo '----------------'
log_stat $1 :CacheServerStats.currentClients
log_stat $1 :CacheServerStats.currentClientConnections
log_stat $1 :CacheServerStats.closeConnectionRequests
log_stat $1 :CacheServerStats.connectionsTimedOut
log_stat $1 :CacheServerStats.threadQueueSize

echo
echo '--------------'
echo 'CachePerfStats'
echo '--------------'
log_stat $1 :CachePerfStats.cacheListenerCallsInProgress
log_stat $1 :CachePerfStats.cacheWriterCallsInProgress
log_stat $1 :CachePerfStats.loadsInProgress

unset IFS
