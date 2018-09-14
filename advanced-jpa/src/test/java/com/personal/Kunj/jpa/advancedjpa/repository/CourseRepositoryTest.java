package com.personal.Kunj.jpa.advancedjpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.personal.Kunj.jpa.advancedjpa.AdvancedJpaApplication;
import com.personal.Kunj.jpa.advancedjpa.entity.Course;
import com.personal.Kunj.jpa.advancedjpa.entity.Review;

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

	@Autowired
	EntityManager em;

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

	@Test
	@Transactional
	public void findById_firstLevelCacheDemo() {

		Course course = repository.findById(10001L);
		logger.info("First Course Retrieved {}", course);

		/*
		 * It will not fire a separate DB query if the method is annotataed with
		 * 
		 * @Transactional.
		 */
		Course course1 = repository.findById(10001L);
		logger.info("First Course Retrieved again {}", course1);

		assertEquals("JPA in 50 Steps", course.getName());

		assertEquals("JPA in 50 Steps", course1.getName());
	}

	@Test
	// To reset the database status
	@DirtiesContext
	public void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}

	@Test
	@DirtiesContext
	public void save_basic() {

		// get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());

		// update details
		course.setName("JPA in 50 Steps - Updated");

		repository.save(course);

		// check the value
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50 Steps - Updated", course1.getName());
	}

	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}

	// Test for @OneToMany side o the relationship
	@Test
	@Transactional // In the below method by default Lazy fetch will take place
	// by default on @OneToMany side of the relationship, fetch strategy is Lazy
	// In every @OneToMany side of the relationship, you have to decide which type
	// of fetching you want to go for

	public void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L);
		// The below statement will throw "exception" if we do not have @Transactional
		logger.info("{}", course.getReviews());
	}

	// Test for @ManyToOne side o the relationship
	@Test
	@Transactional

	public void retrieveCourseForReview() {
		// On the @ManyToOne side of the relationship The fetching is always EAGER
		Review review = em.find(Review.class, 50001L);
		logger.info("{}", review.getCourse());
	}

}
