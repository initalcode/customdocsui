package com.docgen.tempservice.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Facility {
	
	@Column(name = "id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private String facilityName;
	private String street;
	private String town;
	private String state;
	private String zip;
	private String phone;
	private String fax;
	private long npi;
	private String taxId;
	@ManyToMany (cascade = CascadeType.REMOVE)
	@JoinTable(name="facility_doctor", joinColumns = 
				{@JoinColumn(name="taxId")},
				inverseJoinColumns= 
				{@JoinColumn(name="npi")})
	private Collection<Doctor> doctors;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Collection<Doctor> getDoctor() {
		return doctors;
	}
	public void setDoctor(Collection<Doctor> doctor) {
		this.doctors = doctor;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public long getNpi() {
		return npi;
	}
	public void setNpi(long npi) {
		this.npi = npi;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	
	public Facility(String facilityName, String street, String town,
			String state, String zip, String phone, String fax, long npi,
			String taxId, Collection<Doctor> doctors) {
		super();
		this.facilityName = facilityName;
		this.street = street;
		this.town = town;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.fax = fax;
		this.npi = npi;
		this.taxId = taxId;
		this.doctors = doctors;
	}
	public Facility() {
		super();
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

}
