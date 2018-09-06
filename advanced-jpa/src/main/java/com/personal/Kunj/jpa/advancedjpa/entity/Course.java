package com.personal.Kunj.jpa.advancedjpa.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {

	// To define primary key
	@Id
	@GeneratedValue // want JPA to generate for us
	private Long id;

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