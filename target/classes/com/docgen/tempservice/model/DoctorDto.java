package com.docgen.tempservice.model;

public class DoctorDto {

	private String name;
	private long npi;
	private String facility;
	private long id;
	private long facilityId;
	
	
	
	public long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNpi() {
		return npi;
	}
	public void setNpi(long npi) {
		this.npi = npi;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public DoctorDto(){}
	public DoctorDto(String name, long npi, String facility) {
		super();
		this.name = name;
		this.npi = npi;
		this.facility = facility;
	}
	
}
