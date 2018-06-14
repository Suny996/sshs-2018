package com.sshs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * 系统启动总入口
 * @author Suny
 * @date 2018-01-20
 */
@ComponentScan(basePackages = { "com.sshs" })
@MapperScan("com.sshs.**.mapper")
@SpringBootApplication
public class SshsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SshsApplication.class, args);
	}


	/*@Bean
	public static MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.sshs.core.customise.mapper");
		// 配置通用mappers
		Properties properties = new Properties();
		properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
		properties.setProperty("notEmpty", "false");
		mapperScannerConfigurer.setProperties(properties);

		return mapperScannerConfigurer;
	}*/

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		c.setIgnoreUnresolvablePlaceholders(true);
		return c;
	}
}
