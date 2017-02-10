/**
 * 
 */
package com.vizexperts.dao;

import java.util.List;

import com.vizexperts.entity.Teacher;
import com.vizexperts.exception.FetchException;
import com.vizexperts.exception.PersistException;
import com.vizexperts.exception.deleteException;

/**
 * @author Raji
 *
 */
public interface TeacherDao {

	void saveOrUpdateTeacherRecord(String name, Teacher teacher) throws PersistException;
	
	Teacher fetchTeacherRecord(String name) throws FetchException;

	List<Teacher> getListOfTeachers() throws FetchException;

	void deleteTeacherRecord(String name) throws  deleteException;

}
