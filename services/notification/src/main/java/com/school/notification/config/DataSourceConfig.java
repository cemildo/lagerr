package com.school.notification.config;

import io.micrometer.observation.ObservationRegistry;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import net.ttddyy.observation.tracing.DataSourceObservationListener;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

  @Bean
  public DataSource dataSource(DataSourceProperties properties, ObservationRegistry registry) {
    DataSource realDataSource = properties.initializeDataSourceBuilder().build();

    return ProxyDataSourceBuilder.create(realDataSource)
        .name("notification-ds")
        .listener(new DataSourceObservationListener(registry))
        .build();
  }
}
