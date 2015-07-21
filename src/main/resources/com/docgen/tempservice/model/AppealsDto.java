package com.docgen.tempservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppealsDto {

	private long patientId;
	private long doctorId;
	private long facilityId;
	private long insuranceId;
	private Date dateOfService;
	@Id
	private String claimId;
	private Date dateCreated;
	@Column(length = 2000, columnDefinition = "TEXT")
	private String parOne;
	@Column(length = 2000, columnDefinition = "TEXT")
	private String parTwo;
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}
	public long getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(long insuranceId) {
		this.insuranceId = insuranceId;
	}
	public Date getDateOfService() {
		return dateOfService;
	}
	public void setDateOfService(Date dateOfService) {
		this.dateOfService = dateOfService;
	}
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getParOne() {
		return parOne;
	}
	public void setParOne(String parOne) {
		this.parOne = parOne;
	}
	public String getParTwo() {
		return parTwo;
	}
	public void setParTwo(String parTwo) {
		this.parTwo = parTwo;
	}
	public AppealsDto(long patientId, long doctorId, long facilityId,
			int insuranceId, Date dateOfService, String claimId,
			Date dateCreated, String parOne, String parTwo) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.facilityId = facilityId;
		this.insuranceId = insuranceId;
		this.dateOfService = dateOfService;
		this.claimId = claimId;
		this.dateCreated = dateCreated;
		this.parOne = parOne;
		this.parTwo = parTwo;
	}
	public AppealsDto(){}
	
}
