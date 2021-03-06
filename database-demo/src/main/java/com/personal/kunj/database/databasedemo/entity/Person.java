package com.personal.kunj.database.databasedemo.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
// NamedQuery is defined on the entity for which it is used
// JPQL does not use database table to fetch data. It uses entities.
@NamedQuery(name="find_all_persons", query="select p from Person p")
public class Person {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String location;
	private Date birthDate;
	
	// The bean on which BeanPropertyRowMapper is defined should have a default constructor.
	
	public Person() {
		
	}

	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	public Person(String name, String location, Date birthDate) {
		super();
		
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}

	
}