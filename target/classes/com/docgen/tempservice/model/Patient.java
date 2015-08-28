package com.docgen.tempservice.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.xml.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

@Entity
public class Patient {
	
	@Column(name = "id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private long accountId;
	private String insId;
	private String name;
	private Date dateOfBirth;
	private String ptStreet;
	private String ptTown;
	private String ptState;
	private String ptZip;
	
	
	public String getPtStreet() {
		return ptStreet;
	}
	public void setPtStreet(String ptStreet) {
		this.ptStreet = ptStreet;
	}
	public String getPtTown() {
		return ptTown;
	}
	public void setPtTown(String ptTown) {
		this.ptTown = ptTown;
	}
	public String getPtState() {
		return ptState;
	}
	public void setPtState(String ptState) {
		this.ptState = ptState;
	}
	public String getPtZip() {
		return ptZip;
	}
	public void setPtZip(String ptZip) {
		this.ptZip = ptZip;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInsId() {
		return insId;
	}
	public void setInsId(String insId) {
		this.insId = insId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accoundId) {
		this.accountId = accoundId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Patient(long id, long accountId, String insId, String name,
			Date dateOfBirth, String ptStreet, String ptTown, String ptState, String ptZip) {
		super();
		this.ptStreet = ptStreet;
		this.ptTown = ptTown;
		this.ptState = ptState;
		this.ptZip = ptZip;
		this.id = id;
		this.accountId = accountId;
		this.insId = insId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	public Patient(){}
	
}
