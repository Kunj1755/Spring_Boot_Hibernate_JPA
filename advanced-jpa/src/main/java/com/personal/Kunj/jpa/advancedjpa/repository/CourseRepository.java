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
		// The changes done until that point is sent out to the database
		em.flush();

		course1.setName("Web Services in 100 Steps - Updated");
		em.flush();
		
		Course course2 = new Course("Angular JS in 100 Steps");
		em.persist(course2);
		em.flush();
		
		// Let's say I do not want course2 changes to be going to the database after this step.
		// The changes to course2 will no longer be tracked after this stage
		//em.detach(course2);
		
		// The other way of detaching the entity is by clearing everything up. This will clear everything that is there in the EntityManager.
		em.clear();
		
		course2.setName("Angular Js in 100 Steps - Updated");
		em.flush();
	}
}