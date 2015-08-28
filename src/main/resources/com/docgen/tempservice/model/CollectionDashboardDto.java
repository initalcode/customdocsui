package com.docgen.tempservice.model;

import java.util.Date;


public class CollectionDashboardDto {
	
	private long collectionId;
	private String patientName;
	private Date dateCreated;
	private String facilityName;
	private String payment;
	private String letterType;
	
	public CollectionDashboardDto(){};
	public CollectionDashboardDto(String patientName, Date dateCreated,
			String facilityName, String payment, long collectionId, String letterType) {
		super();
		this.letterType = letterType;
		this.collectionId = collectionId;
		this.patientName = patientName;
		this.dateCreated = dateCreated;
		this.facilityName = facilityName;
		this.payment = payment;
	}
	
	public String getLetterType() {
		return letterType;
	}
	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}
	public long getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(long collectionId) {
		this.collectionId = collectionId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	
	
	
	
}
