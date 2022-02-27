package com.fedex.eccp;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class MicroserviceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceDemoApplication.class, args);
		String sql = "select * from employees where employee_id=100";
		JdbcTemplate jdbcTemplate = null;
		try {
			jdbcTemplate = new JdbcTemplate(getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Employees> emps = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Employees.class));
		emps.forEach(emp -> System.out.println("fetch "+emp.getFIRST_NAME() + "  " + emp.getLAST_NAME()));

	}

	@Bean
	public static DataSource getDataSource() {
		return DataSourceBuilder.create().url("jdbc:oracle:thin:@localhost:1521/orcl").username("hr").password("oracle")
				.build();
	}

}
