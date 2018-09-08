package com.personal.Kunj.jpa.advancedjpa.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.personal.Kunj.jpa.advancedjpa.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {

		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}

		return course;
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public void playWithEntityManager() {
		Course course = new Course("Web Services in 100 Steps");
		// persist() is used to create a new entity
		em.persist(course);
		/*
		 * An update query is fired bcz of the below statement without even asking for a
		 * save. HOW?
		 * 
		 * BCZ OF @Transactional annotation, this entire method is in a
		 * single transaction. And while we are within the scope of a transaction,
		 * EntityManager keeps track of all the things that were updated/modified
		 * through it. In this example Course is updated/inserted through the
		 * EntityManager. So changes made to the course are tracked by the
		 * EntityManager.
		 */
		course.setName("Web Services in 100 Steps - Updated");
	}
}