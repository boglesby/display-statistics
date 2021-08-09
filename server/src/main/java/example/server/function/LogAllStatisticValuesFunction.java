package example.server.function;

import org.apache.geode.Statistics;
import org.apache.geode.StatisticDescriptor;
import org.apache.geode.StatisticsType;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.Declarable;

import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;

import org.apache.geode.distributed.internal.InternalDistributedSystem;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LogAllStatisticValuesFunction implements Function, Declarable {

  public void execute(FunctionContext context) {
    // Log all statistics
    logAllStatistics(context.getCache());

    // Return the result
    context.getResultSender().lastResult(true);
  }

  private void logAllStatistics(Cache cache) {
    // Get the statistics
    InternalDistributedSystem ids = (InternalDistributedSystem) cache.getDistributedSystem();
    StringBuilder builder = new StringBuilder();
    List<Statistics> statsList = ids.getStatisticsManager().getStatsList();

    // Add the header
    addHeader(ids, builder, statsList);

    // Add the statistics
    statsList.stream()
      .sorted(Comparator.comparing(statistics -> statistics.getType().getName()))
      .forEach(statistics -> addStatistics(builder, statistics));

    // Log the results
    cache.getLogger().info(builder.toString());
  }

  private void addHeader(InternalDistributedSystem ids, StringBuilder builder, List<Statistics> statsList) {
    builder
      .append("Member ")
      .append(ids.getDistributedMember())
      .append(" defines the following ")
      .append(statsList.size())
      .append(" statistics types:\n");
  }

  private void addStatistics(StringBuilder builder, Statistics statistics) {
    // Add statistics header
    StatisticsType type = statistics.getType();
    addStatisticsHeader(builder, statistics, type);

    // Add statistics
    Arrays.stream(type.getStatistics())
      .sorted(Comparator.comparing(StatisticDescriptor::getName))
      .forEach(statisticDescriptor -> addStatistic(builder, statistics, statisticDescriptor));
    builder.append("\n");
  }

  private void addStatisticsHeader(StringBuilder builder, Statistics statistics, StatisticsType type) {
    builder
      .append("\n")
      .append(type.getName())
      .append(".")
      .append(statistics.getTextId())
      .append(" statistics:");
  }

  private void addStatistic(StringBuilder builder, Statistics statistics, StatisticDescriptor descriptor) {
    builder
      .append("\n\t")
      .append(descriptor.getName())
      .append("=")
      .append(NumberFormat.getInstance().format(statistics.get(descriptor.getName())))
      .append(" ")
      .append(descriptor.getUnit());
  }

  public String getId() {
    return getClass().getSimpleName();
  }
}