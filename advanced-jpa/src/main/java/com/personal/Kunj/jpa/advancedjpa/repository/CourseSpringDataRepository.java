package com.personal.Kunj.jpa.advancedjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.personal.Kunj.jpa.advancedjpa.entity.Course;

// Not a eecommended way for the production
@RepositoryRestResource(path = "course")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
	// Defining custom methods
	// Methods can start with find/retrieve/query for select statements

	List<Course> findByNameAndId(String name, Long id);

	List<Course> findByName(String name);

	List<Course> countByName(String name);

	/*
	 * List<Course> findByNameOrderByIdDesc(String name);
	 * 
	 * List<Course> deleteByName(String name);
	 * 
	 * @Query("Select  c  From Course c where name like '%100 Steps'") List<Course>
	 * courseWith100StepsInName();
	 * 
	 * @Query(value = "Select  *  From Course c where name like '%100 Steps'",
	 * nativeQuery = true) List<Course> courseWith100StepsInNameUsingNativeQuery();
	 * 
	 * @Query(name = "query_get_100_Step_courses") List<Course>
	 * courseWith100StepsInNameUsingNamedQuery();
	 */
}