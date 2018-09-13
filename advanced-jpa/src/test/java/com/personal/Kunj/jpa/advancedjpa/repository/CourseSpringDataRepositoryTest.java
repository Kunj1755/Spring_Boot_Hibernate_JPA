package com.personal.Kunj.jpa.advancedjpa.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.personal.Kunj.jpa.advancedjpa.AdvancedJpaApplication;
import com.personal.Kunj.jpa.advancedjpa.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvancedJpaApplication.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository repository;

	@Test
	public void findById_CoursePresent() {
		/*
		 * Spring data JPA returns Optional back. repository.findById(10001L) will not
		 * return a Course object, rather it will return an Optional.
		 * 
		 * Optional provides a way to check if course exists or not.
		 * 
		 * Optional eliminates the need for a null value. Suppose we pass a course id
		 * which is not present then courseOptional will be a proper object but it would
		 * not contain a course so isPresent() will return false.
		 */

		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
	}

	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
	}

	@Test
	public void playingAroundWithSpringDataRepository() {

		// Same method repository.save(course) is doing save as well as update.

		// Course course = new Course("Microservices in 100 Steps");
		// repository.save(course);

		// course.setName("Microservices in 100 Steps - Updated");
		// repository.save(course);
		logger.info("Courses -> {} ", repository.findAll());
		logger.info("Count -> {} ", repository.count());
	}

	@Test
	public void sort() {
		// Sort criteria can be added by doing .sort().
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		logger.info("Sorted Courses -> {} ", repository.findAll(sort));

	}

	@Test
	public void pagination() {
		// Want to divide the result in the pages of 3 result
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page -> {} ", firstPage);

		// To get the second page data
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {} ", secondPage.getContent());

	}

	@Test
	public void findUsingName() {
		logger.info("FindByName -> {} ", repository.findByName("JPA in 50 Steps"));
	}

	@Test
	public void findUsingStudentsName() {
		logger.info("findUsingStudentsName -> {} ", repository.findByName("Ranga"));
	}

}