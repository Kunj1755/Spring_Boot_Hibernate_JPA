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
	
	public Course findById(Long id){
		return em.find(Course.class, id);
	}
	
	//public Course save(Course course) -> insert or update
	
	public void deleteById(Long id){
		Course course = findById(id);
		em.remove(course);
	}

}