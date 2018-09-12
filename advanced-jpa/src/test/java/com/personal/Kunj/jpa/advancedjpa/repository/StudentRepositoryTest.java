package com.personal.Kunj.jpa.advancedjpa.repository;

import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.personal.Kunj.jpa.advancedjpa.AdvancedJpaApplication;
import com.personal.Kunj.jpa.advancedjpa.entity.Passport;
import com.personal.Kunj.jpa.advancedjpa.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvancedJpaApplication.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
		//Session & Session Factory
		//EntityManager & Persistence Context
		//Transaction
		
		@Test
		// Let us remove @Transactional and move the method body to a new method in StudentRepository.java
	/*
	 * After removing @Transactional, the transaction support will be provided by
	 * StudentRepository.java and all the queries in
	 * someOperationToUnderstandPersistenceContext() will run fine.
	 */
		//@Transactional
		public void someTest() {
			repository.someOperationToUnderstandPersistenceContext();
		}

	@Test
	/*
	 * If @Transactional is not here, student.getPassport() will throw below
	 * exception in case of lazy fetch: 
	 * org.hibernate.LazyInitializationException: could not initialize proxy
	 * [com.personal.Kunj.jpa.advancedjpa.entity.Passport#40001] - no Session
	 */
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("passport -> {}",student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("student -> {}", passport.getStudent());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		// Executes only the student query (Lazy fetch)
		// ManyToMany by default uses lazy fetch
		Student student = em.find(Student.class, 20001L);
		
		logger.info("student -> {}", student);
		// It will run join query on student_course and course table
		logger.info("courses -> {}", student.getCourses());
	}
	
}