package com.personal.Kunj.jpa.advancedjpa.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// To define the name of the table
@Table(name="CourseDetails")
public class Course {

	// To define primary key
	@Id
	@GeneratedValue // want JPA to generate for us
	private Long id;

	// Set all the constraints here that you have in your data in dB to prevent bad data from entering into DB
	@Column(name="fullname", nullable=false)
	private String name;
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