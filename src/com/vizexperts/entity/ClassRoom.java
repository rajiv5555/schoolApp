/**
 * 
 */
package com.vizexperts.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author Raji
 *
 */
@Entity
public class ClassRoom {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="classroom_id")
	private int id;
	
	@Column(name="division_name")
	private String divisionName;
	
	@OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
	private Teacher teacher;
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="class_room_id")
	@MapKey(name="id")
	private Map<Integer,Student> studentRoll;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private TimeTable timeTable;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * @return the timeTable
	 */
	public TimeTable getTimeTable() {
		return timeTable;
	}

	/**
	 * @param timeTable the timeTable to set
	 */
	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}

	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}

	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	/**
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the studentRoll
	 */
	public Map<Integer, Student> getStudentRoll() {
		return studentRoll;
	}

	/**
	 * @param studentRoll the studentRoll to set
	 */
	public void setStudentRoll(HashMap<Integer, Student> studentRoll) {
		this.studentRoll = studentRoll;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassRoom [id=" + id + ", divisionName=" + divisionName
				+ ", teacher=" + teacher + ", studentRoll=" + studentRoll
				+ ", timeTable=" + timeTable + "]";
	}



	
}
