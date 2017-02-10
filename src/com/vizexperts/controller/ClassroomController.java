/**
 * 
 */
package com.vizexperts.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vizexperts.entity.ClassRoom;
import com.vizexperts.exception.ClassRoomException;
import com.vizexperts.service.ClassRoomService;

/**
 * @author Raji
 *
 */
@RestController
public class ClassroomController {
	
	private static final Logger logger=Logger.getLogger(StudentController.class);
	
	@Autowired
	private ClassRoomService classRoomService;
	
	@RequestMapping(value="/classrooms",method=RequestMethod.GET)
	public String getListOfDivisionsOfClassroom(){
		List<ClassRoom> classRooms=null;
		JSONArray divName=null;
		try {
			classRooms=this.classRoomService.getListOfDivisionsOfClassroom();
			divName=new JSONArray();
			for(ClassRoom cr:classRooms)
				divName.add(cr.getDivisionName());
				
			return divName.toString();
		} catch (ClassRoomException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
		
	}
	
	@RequestMapping(value="/classroom/{name}",method=RequestMethod.GET)
	public String getDetailsOfClassroom(@PathVariable("name") String name){
		ClassRoom classRoom=null;
		try {
			classRoom=this.classRoomService.getDetailsOfClassroom(name);
			if(null != classRoom)
			return classRoom.toString();
			else 
				return "data not found !!!";
		} catch (ClassRoomException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
		
	}
	
	@RequestMapping(value="/classroom/{name}",method={RequestMethod.POST,RequestMethod.PUT})
	public String saveOrUpdateClassroomRecord(@PathVariable("name") String name,@RequestBody String data){
		try {
			this.classRoomService.saveOrUpdateClassroomRecord(name,data);
			return "Data Successfully saved !!!";
		} catch (ClassRoomException e) {
			logger.error(e.getMessage(),e);
		}
		return "";
		
	}
	
	
	@RequestMapping(value="/classroom/{name}",method=RequestMethod.DELETE)
	public String deleteClassroomRecord(@PathVariable("name") String name){
		try {
			this.classRoomService.deleteClassroomRecord(name);
			return "Data Successfully Deleted !!!";
		} catch (ClassRoomException e) {
			logger.error(e.getMessage(),e);
		}
		return "";
		
	}
	

}
