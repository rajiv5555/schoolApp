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

import com.vizexperts.entity.Teacher;
import com.vizexperts.exception.TeacherServiceException;
import com.vizexperts.service.TeacherService;

/**
 * @author Raji
 *
 */
@RestController
public class TeacherController {
	
	private static final Logger logger=Logger.getLogger(TeacherController.class);
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/teachers",method=RequestMethod.GET)
	public String getListOfTeachers(){
		List<Teacher> teachers=null;
		JSONArray teacherName=null;
		try {
			teachers = this.teacherService.getListOfTeachers();
			teacherName=new JSONArray();
			for(Teacher t:teachers){
			teacherName.add(t.getName());
			}
			return teacherName.toString();
		} catch (TeacherServiceException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
		
	}
	
	@RequestMapping(value="/teacher/{name}",method=RequestMethod.GET)
	public String getDetailsOfTeacher(@PathVariable("name") String name){
		Teacher teacher=null;
		try {
			teacher = this.teacherService.getDetailsOfTeacher(name);
			if(null!=teacher)
			return teacher.toString();
			else
				return "Data not found !!!";
		} catch (TeacherServiceException e) {
			logger.error(e.getMessage(),e);
		}
		return "";
		
	}
	

	
	@RequestMapping(value="/teacher/{name}",method={RequestMethod.POST,RequestMethod.PUT})
	public String saveOrUpdateTeacherRecord(@PathVariable(value="name") String name,@RequestBody String data){
		
		try {
			this.teacherService.saveOrUpdateTeacherRecord(name,data);
			return "Data Successfully saved !!! ";
		} catch (TeacherServiceException e) {
			logger.error(e.getMessage(),e);
		}
		return "";
		
		
	}
	
	
	
	@RequestMapping(value="/teacher/{name}",method=RequestMethod.DELETE)
	public String deleteTeacherRecord(@PathVariable(value="name") String name){
		try {
			this.teacherService.deleteTeacherRecord(name);
			return "Data Successfully deleted ";
		} catch (TeacherServiceException e) {
			logger.error(e.getMessage(),e);
		}
		return "";
		
	}
	
	

}
