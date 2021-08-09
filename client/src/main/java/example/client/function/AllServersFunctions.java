package example.client.function;

import org.apache.geode.distributed.DistributedMember;
import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnServers;

import java.util.Map;

@OnServers(resultCollector = "allServersResultCollector")
public interface AllServersFunctions {

  @FunctionId("GetStatisticValueFunction")
  Map<DistributedMember,Number> getStatisticValue(String type, String instance, String statistic);

  @FunctionId("LogAllStatisticValuesFunction")
  Object logAllStatisticsValues();
}
