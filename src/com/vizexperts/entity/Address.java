/**
 * 
 */
package com.vizexperts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Raji
 *
 */
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="addr_id")
	private int address_id;
	
	@Column(name="location",length=512)
	private String location;
	

	/**
	 * @return the address_id
	 */
	public int getAddress_id() {
		return address_id;
	}

	/**
	 * @param address_id the address_id to set
	 */
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", location=" + location
				+ "]";
	}



	
}
