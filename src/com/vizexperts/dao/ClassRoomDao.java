/**
 * 
 */
package com.vizexperts.dao;

import java.util.List;

import com.vizexperts.entity.ClassRoom;
import com.vizexperts.exception.FetchException;
import com.vizexperts.exception.PersistException;
import com.vizexperts.exception.deleteException;

/**
 * @author Raji
 *
 */

public interface ClassRoomDao {

	List<ClassRoom> getListOfDivisionsOfClassroom() throws FetchException;

	ClassRoom fetchClassRoomDetail(String name) throws FetchException;

	void saveOrUpdateClassroomRecord(String name, ClassRoom classRoom) throws PersistException;

	void deleteClassroomRecord(String name) throws deleteException;

}
