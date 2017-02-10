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

import com.vizexperts.entity.Student;
import com.vizexperts.exception.StudentServiceException;
import com.vizexperts.service.StudentService;

/**
 * @author Raji
 *
 */
@RestController
public class StudentController {
	
	private static final Logger logger=Logger.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/students",method=RequestMethod.GET)
	public String getListOfStudents(){
		List<Student> students=null;
		JSONArray studentName=null;
		try {
			students=this.studentService.getListOfStudents();
			studentName=new JSONArray();
			for(Student s:students)
				studentName.add(s.getName());
			return studentName.toString();
		} catch (StudentServiceException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
		
	}
	
	@RequestMapping(value="/student/{name}",method=RequestMethod.GET)
	public String getDetailsOfStudent(@PathVariable("name") String name){
		Student student=null;
		try {
			student=this.studentService.getDetailsOfStudent(name);
			if(null != student)
			return student.toString();
			else 
				return "data not found !!!";
		} catch (StudentServiceException e) {
			logger.error(e.getMessage(),e);
		}
		return name;
		
	}
	
	@RequestMapping(value="/student/{name}",method={RequestMethod.POST,RequestMethod.PUT})
	public String saveOrUpdateStudentRecord(@PathVariable("name") String name,@RequestBody String data){
		
		try {
			this.studentService.saveOrUpdateStudentRecord(name,data);
			return "Data Successfully saved !!!";
		} catch (StudentServiceException e) {
			logger.error(e.getMessage(),e);
		}
		return "";
		
		
	}
	
	
	
	@RequestMapping(value="/student/{name}",method=RequestMethod.DELETE)
	public String deleteStudentRecord(@PathVariable("name") String name){
		try {
			this.studentService.deleteStudentRecord(name);
			return "Data Successfully Deleted !!!";
		} catch (StudentServiceException e) {
			logger.error(e.getMessage(),e);
		}
		return "";
		
	}
	

}
