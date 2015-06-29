package com.docgen.tempservice.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.docgen.tempservice.model.Patient;

@Component
public class PatientDao extends JdbcDaoSupport{
	
	public void updatePatient(Patient patient){
		String sql = "update patient set name = ?, insid = ?, dateofbirth = ?, accountid = ? where id=?";
		this.getJdbcTemplate().update(sql, new Object[]{patient.getName(), patient.getInsId(), patient.getDateOfBirth(), patient.getAccountId(), patient.getId()});
	}
	public void deletePatient(long id){
		String sql = "delete from patient where id = ?";
		this.getJdbcTemplate().update(sql, new Object[]{id});
	}
	public void insertPatient(Patient patient){
		String sql = "insert into patient (accountid, name, insid, dateofbirth) values (?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, new Object[]{patient.getAccountId(), patient.getName(), patient.getInsId(), patient.getDateOfBirth()});
	}
	public List<Patient> getAllPatients(){
		String sql = "select * from patient";
		return this.getJdbcTemplate().query(sql, new PatientMapper());
	}
	public Patient getPatientForId(long id){
		String sql = "select * from patient where id = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new PatientMapper());
	}
	private static final class PatientMapper implements RowMapper<Patient> {

	    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Patient patient = new Patient();
	        patient.setId(rs.getLong("id"));
	        patient.setAccountId((rs.getLong("accountid")));
	        patient.setInsId(rs.getString("insid"));
	        patient.setName(rs.getString("name"));
	        patient.setDateOfBirth(rs.getDate("dateofbirth"));
	        return patient;
	    }
	}
}
