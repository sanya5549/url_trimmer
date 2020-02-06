package com.dsp.cassandra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@ComponentScan("com")
@PropertySource("classpath:application.properties")
public class CassandraConfiguration {
    @Configuration
    public static class CassandraConfig extends AbstractCassandraConfiguration {
        @Value("${host_name}")
        private String hostname;

        @Value("${key_space}")
        private String keyspace;

        @Override
        protected boolean getMetricsEnabled() { return false; }

        @Override
        public String getContactPoints(){
            return (hostname != null) ? hostname : CassandraConnectionConstants.HOST_NAME;
        }

        @Override
        public String getKeyspaceName() {
            return (keyspace != null) ? keyspace : CassandraConnectionConstants.KEYSPACE;
        }

        @Override
        public SchemaAction getSchemaAction() {
            return SchemaAction.CREATE_IF_NOT_EXISTS;
        }
    }}
