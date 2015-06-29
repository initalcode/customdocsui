package com.docgen.tempservice.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Doctor {
	
	@Column(name = "id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private String name;
	private long npi;
	@ManyToMany(mappedBy="doctors", cascade = CascadeType.REMOVE)
	private Collection<Facility> facility;
	public String getName() {
		return name;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	public Collection<Facility> getFacility() {
		return facility;
	}
	public void setFacility(Collection<Facility> facility) {
		this.facility = facility;
	}
	public Doctor(String name, long npi, Collection<Facility> facility) {
		this.name = name;
		this.npi = npi;
		this.facility = facility;
	}
	public Doctor(){
	}
	public String toString(){
		return "" + this.name + "";
	}
	}