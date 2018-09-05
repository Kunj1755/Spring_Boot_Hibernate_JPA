package com.personal.kunj.database.databasedemo.jdbc;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.personal.kunj.database.databasedemo.entity.Person;


@Repository
public class PersonJbdcDao {
	
	// JdbcTemplate is spring's way to provide database connection
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Person> findAll() {
		// This will get the resultset and map individual rows to the Person class
		return jdbcTemplate.query("select * from person", 
				new BeanPropertyRowMapper<Person>(Person.class));
	}
}