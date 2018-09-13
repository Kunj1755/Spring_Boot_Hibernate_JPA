package com.personal.Kunj.jpa.advancedjpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.personal.Kunj.jpa.advancedjpa.AdvancedJpaApplication;
import com.personal.Kunj.jpa.advancedjpa.entity.Course;
import com.personal.Kunj.jpa.advancedjpa.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvancedJpaApplication.class)
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpql_basic() {
		Query query = em.createQuery("Select  c  From Course c");
		List resultList = query.getResultList();
		logger.info("Select  c  From Course c -> {}", resultList);
	}

	@Test
	public void jpql_typed() {

		/*
		 * Here we are hard coding the query. If we need to use the same query again, we
		 * need to write the query again. @NamedQuery can help us here where we can give
		 * name to a query.
		 * 
		 * @NamedQuery is always defined on the entity class to which it is directed.
		 */
		// TypedQuery<Course> query = em.createQuery("Select c From Course c",
		// Course.class);
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);

		List<Course> resultList = query.getResultList();
		logger.info("Select  c  From Course c -> {}", resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_100_step_courses", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Select  c  From Course c where name like '%100 Steps'-> {}", resultList);
		// [Course[Web Services in 100 Steps], Course[Spring Boot in 100 Steps]]
	}

	@Test
	public void jpql_courses_without_students() {
		// Here we are refering to the entities and relation behind them.
		// We are not really worried about the tables.
		// In c.students below, students is the variable defined in the course class
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		// [Course[Spring in 50 Steps]]
	}

	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		// [Course[JPA in 50 Steps]]
	}

	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc",
				Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'",
				Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

}