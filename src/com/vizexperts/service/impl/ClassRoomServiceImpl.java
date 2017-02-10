/**
 * 
 */
package com.vizexperts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vizexperts.dao.ClassRoomDao;
import com.vizexperts.dao.StudentDao;
import com.vizexperts.dao.TeacherDao;
import com.vizexperts.entity.ClassRoom;
import com.vizexperts.entity.Student;
import com.vizexperts.entity.Teacher;
import com.vizexperts.entity.TimeTable;
import com.vizexperts.exception.ClassRoomException;
import com.vizexperts.exception.FetchException;
import com.vizexperts.exception.PersistException;
import com.vizexperts.exception.deleteException;
import com.vizexperts.service.ClassRoomService;

/**
 * @author Raji
 *
 */
@Service
public class ClassRoomServiceImpl implements ClassRoomService{
	
	@Autowired
	private ClassRoomDao classRoomDao;
	
	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired
	private StudentDao studentDao;

	@Override
	public List<ClassRoom> getListOfDivisionsOfClassroom() throws ClassRoomException {
		List<ClassRoom> classRooms=null;
		try {
			classRooms=this.classRoomDao.getListOfDivisionsOfClassroom();
		} catch (FetchException e) {
			throw new ClassRoomException(e.getMessage(),e);
		}
		return classRooms;
	}

	@Override
	public ClassRoom getDetailsOfClassroom(String name) throws ClassRoomException {
		ClassRoom classRoom=null;
		try {
			classRoom=this.classRoomDao.fetchClassRoomDetail(name);
		} catch (FetchException e) {
			throw new ClassRoomException(e.getMessage(),e);
		}
		return classRoom;
	}

	@Override
	public void saveOrUpdateClassroomRecord(String name, String data) throws ClassRoomException {
		JSONObject jsonObject=null;
		String divName=null;
		JSONArray jsonArray=null;
		String classTeacher=null;
		JSONArray student_Roll=null;
		ClassRoom classRoom=null;
		Teacher teacher=null;
		TimeTable timeTable=null;
		HashMap<String,String> schedules=null;
		int count=1;
		HashMap<Integer,Student> studentRoll=null;
		Student student=null;
			try {
				if(null!=data){
				jsonObject=(JSONObject) new JSONParser().parse(data);
				if(jsonObject.containsKey("division_name")){
					divName=(String) jsonObject.get("division_name");
				}
				if(jsonObject.containsKey("time_table")){
					jsonArray=(JSONArray) jsonObject.get("time_table");
				}
				if(jsonObject.containsKey("class_teacher")){
					classTeacher=(String) jsonObject.get("class_teacher");
				}
				if(jsonObject.containsKey("student_roll")){
					student_Roll=(JSONArray) jsonObject.get("student_roll");
				}
				
				classRoom=new ClassRoom();
				classRoom.setDivisionName(divName);
				
					teacher=new Teacher();
					teacher.setName(classTeacher);
					classRoom.setTeacher(teacher);
				
				schedules=new HashMap<String, String>();
				for(int i=0;i<jsonArray.size();i++){
					String key=count+"_"+divName;
					String value=jsonArray.get(i).toString();
					schedules.put(key,value);
					count++;
				}
				count=1;
				timeTable=new TimeTable();
				timeTable.setSchedule(schedules);
				classRoom.setTimeTable(timeTable);
				studentRoll=new HashMap<Integer, Student>();
				for(int i=0;i<student_Roll.size();i++){
					JSONObject stud=(JSONObject) student_Roll.get(i);
					 Set<String> keys = stud.keySet();
					 for(String k:keys){
						 student=new Student();
						 student.setDivisionName(divName);
						 student.setName(stud.get(k).toString());
						 studentRoll.put(Integer.parseInt(k),student);
					 }
				}
				classRoom.setStudentRoll(studentRoll);
				
				this.classRoomDao.saveOrUpdateClassroomRecord(name,classRoom);
				}
			} catch (ParseException e) {
				throw new ClassRoomException(e.getMessage(),e);
			} catch (PersistException e) {
				throw new ClassRoomException(e.getMessage(),e);
			}
		
		
		
	}

	@Override
	public void deleteClassroomRecord(String name) throws ClassRoomException {
		try {
			this.classRoomDao.deleteClassroomRecord(name);
		} catch (deleteException e) {
			throw new ClassRoomException(e.getMessage(),e);
		}
		
	}
	
	
	

}
