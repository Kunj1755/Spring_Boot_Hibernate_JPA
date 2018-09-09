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
		@Transactional
		public void someTest() {
			//Database Operation 1 - Retrieve student
			Student student = em.find(Student.class, 20001L);
						
			
			//Database Operation 2 - Retrieve passport
			// As we already have student object, we are not using EntityManager to get data.
			
			Passport passport = student.getPassport();

			//Database Operation 3 - update passport
			passport.setNumber("E123457");
			
			//Database Operation 4 - update student
			student.setName("Ranga - updated");
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
}