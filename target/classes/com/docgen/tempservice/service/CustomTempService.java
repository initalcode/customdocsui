package com.docgen.tempservice.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;

import com.docgen.tempservice.model.AppealsDashboardDto;
import com.docgen.tempservice.model.AppealsDto;
import com.docgen.tempservice.model.Doctor;
import com.docgen.tempservice.model.DoctorDto;
import com.docgen.tempservice.model.Facility;
import com.docgen.tempservice.model.Insurance;
import com.docgen.tempservice.model.Patient;

import freemarker.template.TemplateException;

public class CustomTempService {
	@Autowired
	com.docgen.tempservice.freemarker.FreemarkerImpl fi;
	@Autowired
	com.docgen.tempservice.dao.PatientDao patientDao;
	@Autowired
	com.docgen.tempservice.dao.ProviderDao providerDao;
	@Autowired
	com.docgen.tempservice.dao.InsuranceDao insuranceDao;
	@Autowired
	com.docgen.tempservice.dao.AppealsDao appealsDao;
	@Autowired
	com.docgen.tempservice.dao.AppealsDashboardDao appealsDashboardDao;
	
	@POST
	@Path("/editpatient")
	public void editPatient(Patient patient){
		patientDao.updatePatient(patient);
	}
	@DELETE
	@Path("/editpatient")
	public void removePatient(@QueryParam("id") long id){
		patientDao.deletePatient(id);
	}
	@DELETE
	@Path("appealsletter")
	public void deleteAppealsLetter(@QueryParam("claimId") String claimId){
		appealsDao.deleteAppealsDtoForId(claimId);
	}
	@GET
	@Produces("application/json")
	@Path("/openappealsletters")
	public List<AppealsDashboardDto> getOpenAppealsLetters(){
		return appealsDashboardDao.getAppealsDashboard();
	}
	@POST
	@Path("/appealsletter")
	public void insertAppealsLetter(AppealsDto appealsDto) {
		appealsDao.insertAppealsDetails(appealsDto);
	}
	@GET
	@Path("/appealsletter")
	@Produces("application/octet-stream")
	public Response getAppealsLetter(@QueryParam("claimId") String claimId) throws TemplateException, IOException {
		AppealsDto appealsDto = appealsDao.getAppealsDtoforClaimId(claimId);
		
		Patient currentPatient = patientDao.getPatientForId(appealsDto.getPatientId());
		Facility currentFacility = providerDao.getFacilityForId(appealsDto.getFacilityId());
		DoctorDto currentDoctor = providerDao.getDoctorForId(appealsDto.getDoctorId());
		Insurance currentInsurance = insuranceDao.getInsuranceForId(appealsDto.getInsuranceId());
		
		Map<String, Object> data = new HashMap();
		data.put("patient", currentPatient);
		data.put("facility", currentFacility);
		data.put("doctor", currentDoctor);
		data.put("insurance", currentInsurance);
		data.put("dateOfService", appealsDto.getDateOfService());
		data.put("claimId", appealsDto.getClaimId());
		data.put("dateCreated", appealsDto.getDateCreated());
		data.put("parOne", appealsDto.getParOne());
		data.put("parTwo", appealsDto.getParTwo());
	
		File returnFile = fi.process("AppealLetterTemplate.html", data);
		ResponseBuilder resp = Response.ok((Object) returnFile);
		resp.header("Content-Type", "application/octet-stream");
		resp.header("Content-Disposition", "attachment; filename=\"" + "appealsLetter" + "\"");
		return resp.build();
	}
	@POST
	@Path("/insurance")
	public void addInsurance(Insurance insurance){
		insuranceDao.insertInsurance(insurance);
	}
	@GET
	@Path("/insurance")
	@Produces("application/json")
	public List<Insurance> allInsurances(){
		return insuranceDao.getAllInsurances();
	}
	@DELETE
	@Path("/editinsurance")
	public void removeInsurance(@QueryParam("id") long id){
		insuranceDao.deleteInsurance(id);
	}
	@POST
	@Path("/editinsurance")
	public void editInsurance(Insurance ins){
		insuranceDao.updateInsurance(ins);
	}
	@POST
	@Path("/doctor")
	public void addDoctor(DoctorDto doctor){
		providerDao.insertDoctor(doctor);
	}
	@GET
	@Path("/doctor")
	@Produces("application/json")
	public List<DoctorDto> allDoctors(){
		return providerDao.getAllDoctors();
	}
	@GET
	@Path("getdoctor")
	public DoctorDto getMDforId(@QueryParam("id") long id){
		return providerDao.getDoctorForId(id);
	}
	@POST
	@Path("getdoctor")
	public void updateMD(DoctorDto doctor){
		providerDao.updateDoctor(doctor);
	}
	@DELETE
	@Path("/doctor")
	public void removeDoctor(@QueryParam("id") long id){
		providerDao.deleteDoctor(id);
	}
	@POST
	@Path("/facility")
	public void addFacility(Facility facility){
		providerDao.insertFacility(facility);
	}
	@GET
	@Path("/getfacility")
	public Facility getOneFacility(@QueryParam("id") long id){
		return providerDao.getFacilityForId(id);
	}
	@DELETE
	@Path("/editfacility")
	public void removeFacility(@QueryParam("id") long id){
		providerDao.deleteFacility(id);
	}
	@POST
	@Path("/editfacility")
	public void editFacility(Facility fac){
		providerDao.updateFacility(fac);
	}
	@GET
	@Path("/facility")
	@Produces("application/json")
	public List<Facility> allFacilities(){
		return providerDao.getAllFacilties();
	}
	
	@GET
	@Path("/patient")
	@Produces("application/json")
	public 	List<Patient> allPatients() {
		return patientDao.getAllPatients();
	}
	
	@POST
	@Path("/patient")
	public void addPatient(Patient patient) {
		patientDao.insertPatient(patient);
	}
	@GET
	@Path("/getpatient")
	public Patient getPatient(@QueryParam("id")long id) {
		return patientDao.getPatientForId(id);
	}
	
}
