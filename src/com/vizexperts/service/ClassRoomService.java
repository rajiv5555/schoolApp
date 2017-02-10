/**
 * 
 */
package com.vizexperts.service;

import java.util.List;

import com.vizexperts.entity.ClassRoom;
import com.vizexperts.exception.ClassRoomException;

/**
 * @author Raji
 *
 */
public interface ClassRoomService {

	List<ClassRoom> getListOfDivisionsOfClassroom() throws ClassRoomException;

	ClassRoom getDetailsOfClassroom(String name) throws ClassRoomException;

	void saveOrUpdateClassroomRecord(String name, String data) throws ClassRoomException;

	void deleteClassroomRecord(String name) throws ClassRoomException;

}
