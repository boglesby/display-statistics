package example.client;

import example.client.domain.Trade;
import example.client.function.AllServersFunctions;
import example.client.service.TradeService;
import org.apache.geode.distributed.DistributedMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.geode.boot.autoconfigure.ContinuousQueryAutoConfiguration;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

@SpringBootApplication(exclude = ContinuousQueryAutoConfiguration.class) // disable subscriptions
@EnableEntityDefinedRegions(basePackageClasses = Trade.class)
public class Client {

  @Autowired
  private TradeService service;

  @Autowired
  protected AllServersFunctions allServersFunctions;

  protected static final Logger logger = LoggerFactory.getLogger(Client.class);

  public static void main(String[] args) {
    new SpringApplicationBuilder(Client.class)
      .build()
      .run(args);
  }

  @Bean
  ApplicationRunner runner() {
    return args -> {
      List<String> operations = args.getOptionValues("operation");
      String operation = operations.get(0);
      String parameter1 = (args.containsOption("parameter1")) ? args.getOptionValues("parameter1").get(0) : null;
      String parameter2 = (args.containsOption("parameter2")) ? args.getOptionValues("parameter2").get(0) : null;
      String parameter3 = (args.containsOption("parameter3")) ? args.getOptionValues("parameter3").get(0) : null;
      switch (operation) {
        case "load-region":
          this.service.put(Integer.parseInt(parameter1), 1024);
          break;
        case "get-statistic":
          Map<DistributedMember,Number> statisticValues = this.allServersFunctions.getStatisticValue(parameter1, parameter2, parameter3);
          logStatisticValues(statisticValues, parameter1, parameter2, parameter3);
          break;
        case "log-all-statistics":
          this.allServersFunctions.logAllStatisticsValues();
          logger.info("Logged all statistics");
          break;
    }};
  }

  private void logStatisticValues(Map<DistributedMember,Number> statisticValues, String type, String instance, String statistic) {
    StringBuilder builder = new StringBuilder();
    builder
      .append("\nStatistic value for type=")
      .append(type)
      .append("; instance=")
      .append(instance)
      .append(";statistic=")
      .append(statistic);
    statisticValues.forEach((key, value) -> {
      builder
        .append("\n\tmember=")
        .append(key)
        .append("; value=")
        .append(NumberFormat.getInstance().format(value));
    });
    logger.info(builder.toString());
  }
}
