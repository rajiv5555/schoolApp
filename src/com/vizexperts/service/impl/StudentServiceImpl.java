/**
 * 
 */
package com.vizexperts.service.impl;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vizexperts.dao.StudentDao;
import com.vizexperts.entity.Address;
import com.vizexperts.entity.Student;
import com.vizexperts.exception.FetchException;
import com.vizexperts.exception.PersistException;
import com.vizexperts.exception.StudentServiceException;
import com.vizexperts.exception.deleteException;
import com.vizexperts.service.StudentService;

/**
 * @author Raji
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public void saveOrUpdateStudentRecord(String name, String data) throws StudentServiceException {
		
		JSONObject jsonObject=null;
		String studentName=null;
		String studentAddres=null;
		String studentDivisionName=null;
		Student student=null;
		Address address=null;
		
			try {
				if(null!=data){
				jsonObject=(JSONObject) new JSONParser().parse(data);
				if(jsonObject.containsKey("name")){
					studentName=(String) jsonObject.get("name");
				}
				if(jsonObject.containsKey("address")){
					studentAddres=(String) jsonObject.get("address");
				}
				if(jsonObject.containsKey("division_name")){
					studentDivisionName=(String) jsonObject.get("division_name");
				}
				student=new Student();
				address=new Address();
				address.setLocation(studentAddres);
				student.setAddress(address);
				student.setDivisionName(studentDivisionName);
				student.setName(studentName);
				this.studentDao.saveOrUpdateStudentRecord(name,student);
				
				}
			} catch (ParseException e) {
				throw new StudentServiceException(e.getMessage(),e);
			} catch (PersistException e) {
				throw new StudentServiceException(e.getMessage(),e);
			}
		
	}

	@Override
	public List<Student> getListOfStudents() throws StudentServiceException {
		List<Student> students=null;
		try {
			students=this.studentDao.getListOfStudents();
		} catch (FetchException e) {
			throw new StudentServiceException(e.getMessage(),e);
		}
		return students;
	}

	@Override
	public Student getDetailsOfStudent(String name) throws StudentServiceException {
		Student student=null;
		try {
			student=this.studentDao.fetchStudentRecord(name);
		} catch (FetchException e) {
			throw new StudentServiceException(e.getMessage(),e);
		}
		return student;
	}

	@Override
	public void deleteStudentRecord(String name) throws StudentServiceException {
		try {
			this.studentDao.deleteStudentRecord(name);
		} catch (deleteException e) {
			throw new StudentServiceException(e.getMessage(),e);
		}
		
	}

}
