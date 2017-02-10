/**
 * 
 */
package com.vizexperts.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Raji
 *
 */
@Entity
public class Teacher {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="teacher_id")
	private int id;
	
	@Column(name="teacher_name")
	private String name;
	
	@Column(name="teacher_subject")
	private String subject;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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
	
	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", subject=" + subject
				+ ", address=" + address + ", timeTable=" + timeTable + "]";
	}


	
}
