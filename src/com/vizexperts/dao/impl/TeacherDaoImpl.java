/**
 * 
 */
package com.vizexperts.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vizexperts.dao.TeacherDao;
import com.vizexperts.entity.Teacher;
import com.vizexperts.exception.FetchException;
import com.vizexperts.exception.PersistException;
import com.vizexperts.exception.deleteException;

/**
 * @author Raji
 *
 */
@Repository
@Transactional
public class TeacherDaoImpl  implements TeacherDao  {
	
	@Autowired
	private SessionFactory sessionFactory;

	
	
	
	@Override
	public void saveOrUpdateTeacherRecord(String name, Teacher teacher) throws PersistException  {
		Session session=null;
		try{
		session=this.sessionFactory.openSession();
		Teacher teachers=fetchTeacherRecord(name);
		if(null !=teachers){
			teacher.setId(teachers.getId());
			if(null != teachers.getAddress()){
			teacher.getAddress().setAddress_id(teachers.getAddress().getAddress_id());
			}
			if(null !=teachers.getTimeTable()){
			teacher.getTimeTable().setId(teachers.getTimeTable().getId());
			}
		
			session.update(teacher);
			
		}else{
			session.save(teacher);
		}
		
		}catch(Exception e){
			throw new PersistException(e.getMessage(),e);
		}finally{
			session.flush();
			session.close();
		}
	}

	@Override
	public Teacher fetchTeacherRecord(String name) throws FetchException {
		Session session=null;
		try{
		String Query="from Teacher t where t.name=:name ";
		session=this.sessionFactory.openSession();
		Query query=session.createQuery(Query);
		query.setString("name",name);
		List result=query.list();
		if(result.size()>0){
			return (Teacher) query.list().get(0);
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
	public List<Teacher> getListOfTeachers() throws FetchException {
		Session session=null;
		try{
			String Query="from Teacher ";
			session=this.sessionFactory.openSession();
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
	public void deleteTeacherRecord(String name) throws deleteException   {
		Session session=null;
		try{
			session=this.sessionFactory.openSession();
			Teacher teacher=fetchTeacherRecord(name);
			session.delete(teacher);
			
		}catch(Exception e){
			throw new deleteException(e.getMessage(),e);
		}finally{
			session.flush();
			session.close();
		}
		
	}

	
	}
	
	


