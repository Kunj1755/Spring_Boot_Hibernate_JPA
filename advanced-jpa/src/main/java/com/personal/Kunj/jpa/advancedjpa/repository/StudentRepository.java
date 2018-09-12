package com.personal.Kunj.jpa.advancedjpa.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personal.Kunj.jpa.advancedjpa.entity.Course;
import com.personal.Kunj.jpa.advancedjpa.entity.Passport;
import com.personal.Kunj.jpa.advancedjpa.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {

		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}

		return student;
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		/*
		 * passport has to be there in the database before you want to create a
		 * relationship between student and passport.
		 */

		/*
		 * Caused by: org.springframework.dao.InvalidDataAccessApiUsageException:
		 * org.hibernate.TransientPropertyValueException: object references an unsaved
		 * transient instance - save the transient instance before flushing :
		 * com.personal.Kunj.jpa.advancedjpa.entity.Student.passport ->
		 * com.personal.Kunj.jpa.advancedjpa.entity.Passport; nested exception is
		 * java.lang.IllegalStateException:
		 * org.hibernate.TransientPropertyValueException: object references an unsaved
		 * transient instance - save the transient instance before flushing :
		 * com.personal.Kunj.jpa.advancedjpa.entity.Student.passport ->
		 * com.personal.Kunj.jpa.advancedjpa.entity.Passport
		 */

		/*
		 * Before we are creating a student we have to create a passport (create an id
		 * for it) as student is at the owning side of the relationship
		 */

		/*
		 * Note: Hibernate is Lazy! it will wait as long as it can before inserting the
		 * passport in.
		 */

		// Here hibernate will just generate the next sequence. Query will not go the DB
		em.persist(passport);

		Student student = new Student("Mike");

		student.setPassport(passport);
		em.persist(student);
		// At the end of the transaction, hibernate will send the changes down to the database
	}

	public void someOperationToUnderstandPersistenceContext() {
		// Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		// Persistence Context (student)

		// Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		// Persistence Context (student, passport)

		// Database Operation 3 - update passport
		passport.setNumber("E123457");
		// Persistence Context (student, passport++)

		// Database Operation 4 - update student
		student.setName("Ranga - updated");
		// Persistence Context (student++ , passport++)
	}
	
	public void insertHardcodedStudentAndCourse(){
		Student student = new Student("Jack");
		Course course = new Course("Microservices in 100 Steps");
		em.persist(student);
		em.persist(course);
		
		// Persisting the relationship between student and course
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student); // Persisting the owning side
	}
	
	public void insertStudentAndCourse(Student student, Course course){
		
		student.addCourse(course);
		course.addStudent(student);

		em.persist(student);
		em.persist(course);
	}

}