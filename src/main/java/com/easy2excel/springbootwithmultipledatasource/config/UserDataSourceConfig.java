package com.easy2excel.springbootwithmultipledatasource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "userEntityManagerFactory",
  transactionManagerRef = "userTransactionManager",
  basePackages = { "com.easy2excel.springbootwithmultipledatasource.userdb.repository" }
)
public class UserDataSourceConfig {
	@Bean(name="userDataSource")
	@ConfigurationProperties(prefix="spring.userdb.datasource")
	public DataSource userDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "userEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("userDataSource") DataSource userDataSource) {
		return builder
				.dataSource(userDataSource)
				.packages("com.easy2excel.springbootwithmultipledatasource.userdb.entity")
				.build();
	}
	
	@Bean(name = "userTransactionManager")
	public PlatformTransactionManager userTransactionManager(
			@Qualifier("userEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {
		return new JpaTransactionManager(userEntityManagerFactory);
	}

}
