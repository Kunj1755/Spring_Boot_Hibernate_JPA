package com.personal.Kunj.jpa.advancedjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personal.Kunj.jpa.advancedjpa.repository.CourseRepository;
import com.personal.Kunj.jpa.advancedjpa.repository.EmployeeRepository;
import com.personal.Kunj.jpa.advancedjpa.repository.StudentRepository;

@SpringBootApplication
public class AdvancedJpaApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repo;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdvancedJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		studentRepo.insertHardcodedStudentAndCourse();

		// repo.addHardcodedReviewsForCourse();
		/*
		 * List<Review> reviews = new ArrayList<>();
		 * 
		 * reviews.add(new Review("5", "Great Hands-on Stuff.")); reviews.add(new
		 * Review("5", "Hatsoff."));
		 * 
		 * repo.addReviewsForCourse(10003L, reviews );
		 * studentRepo.insertStudentAndCourse(new Student("Jack"), new
		 * Course("Microservices in 100 Steps"));
		 */

		/*
		 * employeeRepository.insert(new PartTimeEmployee("Jill", new
		 * BigDecimal("50"))); employeeRepository.insert(new FullTimeEmployee("Jack",
		 * new BigDecimal("10000")));
		 * 
		 * logger.info("All Employees -> {}",
		 * employeeRepository.retrieveAllFullTimeEmployees());
		 * logger.info("All Employees -> {}",
		 * employeeRepository.retrieveAllPartTimeEmployees());
		 */

	}
}
