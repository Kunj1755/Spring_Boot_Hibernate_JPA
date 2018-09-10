package com.personal.Kunj.jpa.advancedjpa.entity;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Course {

	@Id
	@GeneratedValue 
	private Long id;

	@Column(name="fullname", nullable=false)
	private String name;
	
	@OneToMany(mappedBy="course")
	private List<Review> reviews;
	
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
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	
	// Default constructor will be used by JPA to create bean
	protected Course() {
	}

	
	// WE want others only to provide the name , not id
	public Course(String name) {
		this.name = name;
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