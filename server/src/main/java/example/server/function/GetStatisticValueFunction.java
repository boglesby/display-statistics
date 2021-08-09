package example.server.function;

import org.apache.geode.Statistics;
import org.apache.geode.StatisticsFactory;
import org.apache.geode.StatisticsType;

import org.apache.geode.cache.Declarable;

import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;

import java.util.Arrays;

public class GetStatisticValueFunction implements Function, Declarable {

  public void execute(FunctionContext context) {
    // Get the arguments
    Object[] arguments = (Object[]) context.getArguments();
    String typeName = (String) arguments[0];
    String textId = (String) arguments[1];
    String statistic = (String) arguments[2];
    context.getCache().getLogger()
      .info("typeName=" + typeName + ";textId=" + textId + "; statistic=" + statistic);

    // Get the statistic value
    Number statisticValue = getStatisticValue(context.getCache().getDistributedSystem(),
      typeName, textId, statistic);

    // Return the result
    context.getResultSender().lastResult(statisticValue);
  }

  private Number getStatisticValue(StatisticsFactory statisticsFactory, String typeName,
    String textId, String statistic) {
    StatisticsType type = statisticsFactory.findType(typeName);
    Statistics[] statisticsByType = statisticsFactory.findStatisticsByType(type);
    return Arrays.stream(statisticsByType)
      .filter(statistics -> statistics.getTextId().equals(textId))
      .map(statistics -> statistics.get(statistic))
      .findFirst()
      .orElse(-1);
  }

  public String getId() {
    return getClass().getSimpleName();
  }
}