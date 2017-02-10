/**
 * 
 */
package com.vizexperts.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;

/**
 * @author Raji
 *
 */
@Entity
public class TimeTable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="timetable_id")
	private int id;
	
	@MapKey
	private HashMap<String,String> schedule=new HashMap<String,String>();


	
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
	 * @return the schedule
	 */
	public Map<String, String> getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(HashMap<String, String> schedule) {
		this.schedule = schedule;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TimeTable [id=" + id + ", schedule=" + schedule + "]";
	}

	

	

}
