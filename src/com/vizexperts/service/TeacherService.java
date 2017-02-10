/**
 * 
 */
package com.vizexperts.service;

import java.util.List;

import com.vizexperts.entity.Teacher;
import com.vizexperts.exception.TeacherServiceException;

/**
 * @author Raji
 *
 */
public interface TeacherService {

	void saveOrUpdateTeacherRecord(String name, String data) throws TeacherServiceException;

	List<Teacher> getListOfTeachers() throws TeacherServiceException;

	Teacher getDetailsOfTeacher(String name) throws TeacherServiceException;

	void deleteTeacherRecord(String name) throws TeacherServiceException;

}
