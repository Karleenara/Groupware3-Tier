package com.ds.gw.pt.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

// config인지를 지정하기 위한 어노테이션
// 파일명과 클래스명은 내마음
@Configuration
public class MyBatisConfig {
	// 객체 생성
	@Bean
	public SqlSessionFactory makesqlsessionFactory(DataSource dataSource) throws Exception {
		
		System.out.println("*****************");
		// SqlSessionFactory - Factory 공장객체를 먼저 만들어서 던지기
		final SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		
		// 저 객체와 application.properties 파일에 있는 datasource 와 연결
		factory.setDataSource(dataSource);
		
		// 설정파일과 연동하기 (mybatis-config.xml)파일과 연동
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		Resource configLocation = resolver.getResource("classpath:mybatis-config.xml");
		
		factory.setConfigLocation(configLocation);
		return factory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate makesqlSession(SqlSessionFactory factory) {
		return new SqlSessionTemplate(factory);
	}
	
}