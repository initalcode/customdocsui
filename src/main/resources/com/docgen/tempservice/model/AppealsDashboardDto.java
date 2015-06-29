package com.docgen.tempservice.model;

import java.util.Date;

public class AppealsDashboardDto {

	private String patientName;
	private String doctorName;
	private String facilityName;
	private String claimId;
	private Date dateOfService;
	private Date dateCreated;
	
	
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	public Date getDateOfService() {
		return dateOfService;
	}
	public void setDateOfService(Date dateOfService) {
		this.dateOfService = dateOfService;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public AppealsDashboardDto(String patientName, String doctorName,
			String facilityName, String claimId, Date dateOfService,
			Date dateCreated) {
		super();
		this.patientName = patientName;
		this.doctorName = doctorName;
		this.facilityName = facilityName;
		this.claimId = claimId;
		this.dateOfService = dateOfService;
		this.dateCreated = dateCreated;
	}
	public AppealsDashboardDto(){}
	
	
}
