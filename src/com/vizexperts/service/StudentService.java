/**
 * 
 */
package com.vizexperts.service;

import java.util.List;

import com.vizexperts.entity.Student;
import com.vizexperts.exception.StudentServiceException;

/**
 * @author Raji
 *
 */
public interface StudentService {

	void saveOrUpdateStudentRecord(String name, String data) throws StudentServiceException;

	List<Student> getListOfStudents() throws StudentServiceException;

	Student getDetailsOfStudent(String name) throws StudentServiceException;

	void deleteStudentRecord(String name) throws StudentServiceException;

}
