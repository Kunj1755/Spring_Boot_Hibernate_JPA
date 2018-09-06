package com.personal.Kunj.jpa.advancedjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personal.Kunj.jpa.advancedjpa.entity.Course;
import com.personal.Kunj.jpa.advancedjpa.repository.CourseRepository;

@SpringBootApplication
public class AdvancedJpaApplication implements CommandLineRunner{
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(AdvancedJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Course course = repo.findById(10001L);
		logger.info("Course 10001 -> {}",course);
		repo.deleteById(10001L);
	}
}
