package com.personal.Kunj.jpa.advancedjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.Kunj.jpa.advancedjpa.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

}