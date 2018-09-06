package com.personal.kunj.database.databasedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.personal.kunj.database.databasedemo.entity.Person;
import com.personal.kunj.database.databasedemo.jpa.PersonJpaRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	PersonJpaRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("User id 10001 -> {}", repo.findById(10001));
		logger.info("\n All users -> {}", repo.findAll());
		
		 repo.deleteById(10002);
		 
		// Let hibernate decide the id
		logger.info("Inserting  -> {}", 
				repo.insert(new Person("Tara", "Berlin", new Date())));
		
		logger.info("Update 10003 -> {}", 
				repo.update(new Person(10003, "Pieter", "Utrecht", new Date())));
	}
}
