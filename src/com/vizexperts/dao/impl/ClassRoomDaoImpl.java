/**
 * 
 */
package com.vizexperts.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vizexperts.dao.ClassRoomDao;
import com.vizexperts.entity.ClassRoom;
import com.vizexperts.exception.FetchException;
import com.vizexperts.exception.PersistException;
import com.vizexperts.exception.deleteException;

/**
 * @author Raji
 *
 */
@Repository
@Transactional
public class ClassRoomDaoImpl implements ClassRoomDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ClassRoom> getListOfDivisionsOfClassroom() throws FetchException {
		Session session=null;
		try{
			session=this.sessionFactory.openSession();
			String Query="from ClassRoom ";
			Query query=session.createQuery(Query);
			return query.list();
		}catch(Exception e){
			throw new FetchException(e.getMessage(),e);
		}finally{
			session.flush();
			session.close();
		}
		
	}

	@Override
	public ClassRoom fetchClassRoomDetail(String name) throws FetchException {
		
		Session session=null;
		try{
		String Query="from ClassRoom r where r.divisionName=:name";
		session=this.sessionFactory.openSession();
		Query query=session.createQuery(Query);
		query.setString("name",name);
		List result=query.list();
		if(result.size()>0){
			return (ClassRoom) query.list().get(0);
		}
		else return null;
		}catch(Exception e){
			throw new FetchException(e.getMessage(),e);
		}finally{
			session.flush();
			session.close();
		}
	}

	

	@Override
	public void deleteClassroomRecord(String name) throws deleteException {
		Session session=null;
		try{
			session=this.sessionFactory.openSession();
			ClassRoom classRoom=fetchClassRoomDetail(name);
			session.delete(classRoom);
			
		}catch(Exception e){
			throw new deleteException(e.getMessage(),e);
		}finally{
			session.flush();
			session.close();
		}
		
	}

	@Override
	public void saveOrUpdateClassroomRecord(String name, ClassRoom classRoom) throws PersistException {
		Session session=null;
		try{
			session=this.sessionFactory.openSession();
			ClassRoom classRooms=this.fetchClassRoomDetail(name);
			if(null != classRooms){
				classRoom.setId(classRooms.getId());
				if(null != classRooms.getTeacher()){
				classRoom.getTeacher().setId(classRooms.getTeacher().getId());
				}
				if(null !=classRooms.getTimeTable()){
				classRoom.getTimeTable().setId(classRooms.getTimeTable().getId());
				}
				session.update(classRoom);
			}else{
				session.save(classRoom);
			}
			
		}catch(Exception e){
			throw new PersistException(e.getMessage(),e);
		}finally{
			session.flush();
			session.close();
		}
	}

}
