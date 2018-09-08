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
		logger.info("Select  c  From Course c -> {}",resultList);
	}

	@Test
	public void jpql_typed() {
		
		/*
		 * Here we are hard coding the query. If we need to use the same query again, 
		 * we need to write the query again. @NamedQuery can help us here where we can give
		 * name to a query.
		 * 
		 * @NamedQuery is always defined on the entity class to which it is directed.
		 */
		//TypedQuery<Course> query = em.createQuery("Select  c  From Course c", Course.class);
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
		
		List<Course> resultList = query.getResultList();	
		logger.info("Select  c  From Course c -> {}",resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> query = 
					em.createNamedQuery("query_get_100_step_courses", Course.class);
		
		List<Course> resultList = query.getResultList();
		
		logger.info("Select  c  From Course c where name like '%100 Steps'-> {}",resultList);
		//[Course[Web Services in 100 Steps], Course[Spring Boot in 100 Steps]]
	}

}