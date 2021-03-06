package com.amrTm.backLMS.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataSourcesConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
		data.setDriverClassName("com.mysql.cj.jdbc.Driver");
		data.setUrl("jdbc:mysql://localhost:3306/lms");
		data.setUsername("root");
		data.setPassword("AmarM124890#");
		return data;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory e) {
		return new JpaTransactionManager(e);
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
		vendor.setDatabase(Database.MYSQL);
		vendor.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
		vendor.setShowSql(true);
		vendor.setGenerateDdl(true);
		return vendor;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean container = new LocalContainerEntityManagerFactoryBean();
		container.setDataSource(dataSource());
		container.setJpaVendorAdapter(jpaVendorAdapter());
		Properties jpaProperties = new Properties();
	    jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
	    jpaProperties.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
		container.setJpaProperties(jpaProperties);
		container.setPackagesToScan("com.amrTm.backLMS.entity");
		return container;
	}
}
