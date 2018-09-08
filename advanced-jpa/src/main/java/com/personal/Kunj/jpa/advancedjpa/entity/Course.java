package com.personal.Kunj.jpa.advancedjpa.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
// To define the name of the table
@Table(name="CourseDetails")
// we can either use multiple @NamedQuery or @NamedQueries

@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "Select  c  From Course c"),
		@NamedQuery(name = "query_get_100_step_courses", query = "Select  c  From Course c where name like '%100 Steps'") })

/*@NamedQuery(name="query_get_all_courses", query="Select  c  From Course c")
@NamedQuery(name="query_get_100_step_courses", query="Select  c  From Course c where name like '%100 Steps'")*/
public class Course {

	// To define primary key
	@Id
	@GeneratedValue // want JPA to generate for us
	private Long id;

	// Set all the constraints here that you have in your data in dB to prevent bad data from entering into DB
	@Column(name="fullname", nullable=false)
	private String name;
	
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