package com.docgen.tempservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.docgen.tempservice.model.Doctor;
import com.docgen.tempservice.model.DoctorDto;
import com.docgen.tempservice.model.Facility;

@Component
public class ProviderDao extends JdbcDaoSupport {
	
	public void deleteDoctor(long id){
		String sql = "delete from doctor where id=?";
		this.getJdbcTemplate().update(sql, new Object[]{id});
	}
	public void updateDoctor(DoctorDto doctor){
		String sql = "update doctor set name=?, npi=? where id=?";
		this.getJdbcTemplate().update(sql, new Object[]{doctor.getName(), doctor.getNpi(), doctor.getId()});
		String ssql = "select * from facility where taxId = ?";
		List<Facility> facility = this.getJdbcTemplate().query(ssql, new Object[]{doctor.getFacility()}, new FacilityMapper());
		String tsql = "update facility_doctor set taxid = ? where npi = ?";
		this.getJdbcTemplate().update(tsql, new Object[]{facility.get(0).getId(), doctor.getId()});
		
	}
	public void insertFacility(Facility facility) {
		String sql = "insert into facility (facilityname, street, town, state, zip, phone, npi, taxid, fax) values (?,?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[]{facility.getFacilityName(), facility.getStreet(), facility.getTown(),
				facility.getState(), facility.getZip(), facility.getPhone(), facility.getNpi(), facility.getTaxId(), facility.getFax()});
	}
	public List<Facility> getAllFacilties(){
		String sql = "select * from facility";
		return this.getJdbcTemplate().query(sql, new FacilityMapper());
	}
	public void deleteFacility(long id){
		String sql = "delete from facility where id = ?";
		this.getJdbcTemplate().update(sql, new Object[]{id});
	}
	public void updateFacility(Facility fac){
		String sql = "update facility set facilityname=?, street=?, town=?, state=?, zip=?, phone=?, npi=?, taxid=?, fax=? where id=?";
		this.getJdbcTemplate().update(sql, new Object[]{fac.getFacilityName(), fac.getStreet(), fac.getTown(), fac.getState(),
							fac.getZip(),fac.getPhone(), fac.getNpi(), fac.getTaxId(), fac.getFax(), fac.getId()});
	}
	public void insertDoctor(DoctorDto doctor){
		String sql = "insert into doctor (name, npi) values (?,?)";
		this.getJdbcTemplate().update(sql, new Object[]{doctor.getName(), doctor.getNpi()});
		String dsql = "select * from doctor where npi = ?";
		List<Doctor> newDoctor = this.getJdbcTemplate().query(dsql, new Object[]{doctor.getNpi()}, new DoctorMapper());
		String facsql = "select * from facility where taxId = ?";
		List<Facility> facilities = this.getJdbcTemplate().query(facsql, new Object[]{doctor.getFacility()}, new FacilityMapper());
		String ssql = "insert into facility_doctor (taxId, npi) values (?,?)";
		this.getJdbcTemplate().update(ssql, new Object[]{facilities.get(0).getId(), newDoctor.get(0).getId()});
	}
	public List<DoctorDto> getAllDoctors(){
		String sql = "select d.id, d.name, d.npi, f.facilityname, f.id as facilityId from doctor d, facility f, facility_doctor fd where "
					+ "fd.taxId = f.id and d.id = fd.npi";
		return this.getJdbcTemplate().query(sql, new DoctorDtoMapper());
	}
	public DoctorDto getDoctorForId(Long id){
		String sql = "select d.id, d.name, d.npi, f.facilityname, f.id as facilityId from doctor d, facility f, facility_doctor fd where "
						+ "fd.taxId = f.id and d.id = fd.npi and d.id = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new DoctorDtoMapper());
	}
	public Facility getFacilityForId(long id){
		String sql = "select * from facility where id = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new FacilityMapper());
	}
	private static final class FacilityMapper implements RowMapper<Facility> {

	    public Facility mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Facility facility = new Facility();
	        facility.setFacilityName(rs.getString("facilityName"));
	        facility.setStreet(rs.getString("street"));
	        facility.setTown(rs.getString("town"));
	        facility.setState(rs.getString("state"));
	        facility.setZip(rs.getString("zip"));
	        facility.setNpi(rs.getLong("npi"));
	        facility.setPhone(rs.getString("phone"));
	        facility.setTaxId(rs.getString("taxId"));
	        facility.setFax(rs.getString("fax"));
	        facility.setId(rs.getLong("id"));
	        return facility;
	    }
	}
	private static final class DoctorMapper implements RowMapper<Doctor> {
		
		public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException{
			Doctor doctor = new Doctor();
			doctor.setName(rs.getString("name"));
			doctor.setNpi(rs.getLong("npi"));
			doctor.setId(rs.getLong("id"));
			return doctor;
		}
	}
	private static final class DoctorDtoMapper implements RowMapper<DoctorDto> {
		
		public DoctorDto mapRow(ResultSet rs, int rowNum) throws SQLException{
			DoctorDto doctor = new DoctorDto();
			doctor.setName(rs.getString("name"));
			doctor.setNpi(rs.getLong("npi"));
			doctor.setId(rs.getLong("id"));
			doctor.setFacility(rs.getString("facilityName"));
			doctor.setFacilityId(rs.getLong("facilityId"));
			return doctor;
		}
	}
}
