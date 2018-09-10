package com.personal.Kunj.jpa.advancedjpa.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;
	
	/*
	 * Even though there is no studnt_id column associated with passport, this gives
	 * us a way to navigate from passport to student. This is Bidirectional
	 * relationship
	 */ 
	@OneToOne(fetch=FetchType.LAZY, mappedBy="passport") 
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	protected Passport() {
	}

	public Passport(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Passport[%s]", number);
	}
}