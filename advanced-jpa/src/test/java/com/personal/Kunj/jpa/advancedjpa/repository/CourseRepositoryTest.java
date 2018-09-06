package com.personal.Kunj.jpa.advancedjpa.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.personal.Kunj.jpa.advancedjpa.AdvancedJpaApplication;
import com.personal.Kunj.jpa.advancedjpa.entity.Course;

// Used to launch SpringContext in unit test
@RunWith(SpringRunner.class)
/*
 * The SpringContext that we would want to launch is a 'SpringBootTest'. We
 * would want to launch entire SpringBootContext which is present
 * in'AdvancedJpaApplication'. (classes=AdvancedJpaApplication.class) will
 * launch the entire context
 */
@SpringBootTest(classes = AdvancedJpaApplication.class)
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	/*
	 * If we do right clock on contextLoads(), it will launch the entire context, ie
	 * it will do the same thing as what is done at the application run.
	 */

	// After the above it will run the code present in test method
	// Then context is killed.
	// Unit test is run between the Context Launch and Destroy.
	@Test
	public void contextLoads() {
		logger.info("Test is running");
	}
	
	// These JUnit tests will run as part of your build

	@Test
	public void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());

	}

}
