package com.docgen.tempservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollectionDto {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long collectionId;
	private long patientId;
	private Date dateCreated;
	private long facilityId;
	private String payment;
	private String letterType;
	
	
	public String getLetterType() {
		return letterType;
	}
	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public long getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(long collectionId) {
		this.collectionId = collectionId;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}
	
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public CollectionDto(long collectionId, long patientId, long facilityId, Date dateCreated, String payment, String letterType) {
		super();
		this.payment = payment;
		this.dateCreated = dateCreated;
		this.collectionId = collectionId;
		this.patientId = patientId;
		this.facilityId = facilityId;
		this.letterType = letterType;
	}
	
	public CollectionDto(){};
}
