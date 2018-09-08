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
		
		Course course1 = new Course("Web Services in 100 Steps");
		em.persist(course1);
		Course course2 = new Course("Angular JS in 100 Steps");
		em.persist(course2);	
		
		em.flush();
		
		course1.setName("Web Services in 100 Steps - Updated");
		course2.setName("Angular Js in 100 Steps - Updated");
		
		/*
		 * Refresh course1 with the content that is there in the database. Take
		 * course1 details as it is in the database. I do not want the updated data of
		 * course1 to go through.
		 */
		em.refresh(course1);;
		
		// Only course2 changes will go the database
		em.flush();
				
	}
	
}