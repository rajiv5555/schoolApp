/**
 * 
 */
package com.vizexperts.service.impl;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vizexperts.dao.TeacherDao;
import com.vizexperts.entity.Address;
import com.vizexperts.entity.Teacher;
import com.vizexperts.entity.TimeTable;
import com.vizexperts.exception.FetchException;
import com.vizexperts.exception.PersistException;
import com.vizexperts.exception.TeacherServiceException;
import com.vizexperts.exception.deleteException;
import com.vizexperts.service.TeacherService;

/**
 * @author Raji
 *
 */
@Service
public class TeacherServiceImpl implements TeacherService {

   @Autowired
   private TeacherDao teacherDao;
   
	@Override
	public void saveOrUpdateTeacherRecord(String name, String data) throws TeacherServiceException {
		JSONObject jsonObject=null;
		String teacherName=null;
		JSONArray jsonArray=null;
		String teacherSubject=null;
		String teacherAddress=null;
		Teacher teacher=null;
		Address address=null;
		TimeTable timeTable=null;
		HashMap<String,String> schedule=null;
		int count=1;
		
			try {
				if ( null != data ) {
				jsonObject=(JSONObject) new JSONParser().parse(data);
				if(jsonObject.containsKey("name")){
					teacherName=(String) jsonObject.get("name");
				}
				if(jsonObject.containsKey("time_table")){
					jsonArray= (JSONArray) jsonObject.get("time_table");
				}
				if(jsonObject.containsKey("subject")){
					teacherSubject=(String) jsonObject.get("subject");
				}
				
				if(jsonObject.containsKey("address")){
					teacherAddress=(String) jsonObject.get("address");
				}
				address=new Address();
				teacher=new Teacher();
				timeTable=new TimeTable();
				schedule=new HashMap<String, String>();
				for(int i=0;i<jsonArray.size();i++){
				String key=count+"_"+jsonArray.get(i).toString();
				schedule.put(key,teacherSubject);
				count++;
				}
				count=1;
				address.setLocation(teacherAddress);
				teacher.setAddress(address);
				teacher.setName(teacherName);
				teacher.setSubject(teacherSubject);
				timeTable.setSchedule(schedule);
				teacher.setTimeTable(timeTable);
				this.teacherDao.saveOrUpdateTeacherRecord(name,teacher);
				}
			} catch (ParseException e) {
				throw new TeacherServiceException(e.getMessage(),e);
			} catch (PersistException e) {
				throw new TeacherServiceException(e.getMessage(),e);
			}
		
		
	}

	@Override
	public List<Teacher> getListOfTeachers() throws TeacherServiceException {
		List<Teacher> teachers=null;
		try {
			 teachers=this.teacherDao.getListOfTeachers();
		} catch (FetchException e) {
			throw new TeacherServiceException(e.getMessage(),e);
		}
		return teachers;
	}

	@Override
	public Teacher getDetailsOfTeacher(String name) throws TeacherServiceException {
		Teacher teacher=null;
		try {
			teacher=this.teacherDao.fetchTeacherRecord(name);
		} catch (FetchException e) {
			throw new TeacherServiceException(e.getMessage(),e);
		}
		return teacher;
	}

	@Override
	public void deleteTeacherRecord(String name) throws TeacherServiceException {
		try {
			this.teacherDao.deleteTeacherRecord(name);
		}  catch (deleteException e) {
			throw new TeacherServiceException(e.getMessage(),e);
		}
		
	}

}
