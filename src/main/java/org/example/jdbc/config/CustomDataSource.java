package org.example.jdbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.metrics.MetricsTrackerFactory;
import com.zaxxer.hikari.metrics.prometheus.PrometheusHistogramMetricsTrackerFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class CustomDataSource {
    private static DataSource DATASOURCE = null;

    static {
        Properties properties = new Properties();
        properties.setProperty("jdbcUrl", "jdbc:postgresql://localhost:5432/testing");
        properties.setProperty("username", "postgres");
        properties.setProperty("password", "admin");
        properties.setProperty("autoCommit", "false");
        HikariConfig config = new HikariConfig(properties);
        config.setMetricsTrackerFactory(new PrometheusHistogramMetricsTrackerFactory());
        DATASOURCE = new HikariDataSource(config);
    }

    private CustomDataSource() {
    }

    public static DataSource getInstance() {
        return DATASOURCE;
    }
}
