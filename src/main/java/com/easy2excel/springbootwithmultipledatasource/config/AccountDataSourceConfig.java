package com.easy2excel.springbootwithmultipledatasource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "accountEntityManagerFactory",
  transactionManagerRef = "accountTransactionManager",
  basePackages = { "com.easy2excel.springbootwithmultipledatasource.accountdb.repository" }
)
public class AccountDataSourceConfig {
	
	@Bean(name="accountDataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.accountdb.datasource")
	public DataSource accountDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name = "accountEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("accountDataSource") DataSource accountDataSource) {
		return builder
				.dataSource(accountDataSource)
				.packages("com.easy2excel.springbootwithmultipledatasource.accountdb.entity")
				.build();
	}
	
	@Bean(name = "accountTransactionManager")
	public PlatformTransactionManager accountTransactionManager(
			@Qualifier("accountEntityManagerFactory") EntityManagerFactory accountEntityManagerFactory) {
		return new JpaTransactionManager(accountEntityManagerFactory);
	}

}
