/**
 * 
 */
package com.vizexperts.dao;

import java.util.List;

import com.vizexperts.entity.Student;
import com.vizexperts.exception.FetchException;
import com.vizexperts.exception.PersistException;
import com.vizexperts.exception.deleteException;

/**
 * @author Raji
 *
 */
public interface StudentDao {

	void saveOrUpdateStudentRecord(String name, Student student) throws  PersistException;
	
	Student fetchStudentRecord(String name) throws  FetchException;
     
	List<Student> getListOfStudents() throws FetchException;
	
	 void deleteStudentRecord(String name) throws deleteException;
}
