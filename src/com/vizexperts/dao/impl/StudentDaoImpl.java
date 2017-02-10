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

import com.vizexperts.dao.StudentDao;
import com.vizexperts.entity.Student;
import com.vizexperts.exception.FetchException;
import com.vizexperts.exception.PersistException;
import com.vizexperts.exception.deleteException;

/**
 * @author Raji
 *
 */
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdateStudentRecord(String name, Student student)throws PersistException {
		Session session=null;
		try {
			Student students=this.fetchStudentRecord(name);
			session=this.sessionFactory.openSession();
			if(null!=students){
				student.setId(students.getId());
				if(null != students.getAddress()){
				student.getAddress().setAddress_id(students.getAddress().getAddress_id());
				}
				session.update(student);
			}else{
				session.save(student);
			}
			
			
		} catch (FetchException e) {
			throw new PersistException(e.getMessage(),e);
		}finally{
			session.flush();
			session.close();
		}
		
	}

	@Override
	public Student fetchStudentRecord(String name) throws FetchException {
		Session session=null;
		try{
			String Query="from Student s where s.name=:name ";
			session =this.sessionFactory.openSession();
			Query query=session.createQuery(Query);
			query.setString("name",name);
			List result=query.list();
			if(result.size()>0){
				return (Student) query.list().get(0);
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
	public List<Student> getListOfStudents() throws FetchException {
		Session session=null;
		try{
		String Query="from Student ";
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
	public void deleteStudentRecord(String name) throws deleteException {
		Session session=null;
		try{
			session=this.sessionFactory.openSession();
			Student student=fetchStudentRecord(name);
			session.delete(student);
			
		}catch(Exception e){
			throw new deleteException(e.getMessage(),e);
		}finally{
			session.flush();
			session.close();
		}
		
	}
	
	
	
	
	
	
}
