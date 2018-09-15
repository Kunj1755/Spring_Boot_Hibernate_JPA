package com.personal.Kunj.jpa.advancedjpa.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Cacheable
// On the delete of a row I want this SQL to be called
@SQLDelete(sql = "update course set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
public class Course {

	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();

	private boolean isDeleted;

	@PreRemove
	private void preRemove() {
		LOGGER.info("Setting isDeleted to True");
		this.isDeleted = true;
	}

	// Default constructor will be used by JPA to create bean
	protected Course() {
	}

	// We want others only to provide the name , not id
	public Course(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	/*
	 * I do not want others to set reviews. I want others to add one review at a
	 * time.I do not want somebody to take the list of the revies and manipulate it
	 * and then give me the complete list.
	 */

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	// Override toString() to get rid of hashcode being printed
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
}