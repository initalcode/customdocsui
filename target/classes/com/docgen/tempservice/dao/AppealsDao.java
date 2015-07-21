package com.docgen.tempservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.docgen.tempservice.model.AppealsDto;

@Component
public class AppealsDao extends JdbcDaoSupport{

	public void insertAppealsDetails(AppealsDto dto) {
		String sql = "insert into appealsdto (patientId, doctorId, facilityId, insuranceId, dateOfService, claimId, "
				+ "dateCreated, parOne, parTwo) values (?,?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[]{dto.getPatientId(), dto.getDoctorId(), dto.getFacilityId(), dto.getInsuranceId(),
					dto.getDateOfService(), dto.getClaimId(), dto.getDateCreated(), dto.getParOne(), dto.getParTwo()});
	}
	public void updateAppealsDetails(AppealsDto dto){
		String sql = "update appealsdto set patientId=?, doctorId=?, facilityId=?, insuranceId=?, dateOfService=?, "
				+ "dateCreated=?, parOne=?, parTwo=? where claimId=?";
		this.getJdbcTemplate().update(sql, new Object[]{dto.getPatientId(), dto.getDoctorId(), dto.getFacilityId(), dto.getInsuranceId(),
					dto.getDateOfService(), dto.getDateCreated(), dto.getParOne(), dto.getParTwo(), dto.getClaimId()});
	}
	public AppealsDto getAppealsDtoforClaimId(String claimId){
		String sql = "select * from appealsdto where claimId = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[]{claimId},new AppealsMapper());
	}
	public Boolean letterExists(String claimId){
		String sql = "select count(*) from appealsdto where claimId = ?";
		Integer x = this.getJdbcTemplate().queryForObject(sql, Integer.class, claimId);
		return x != null && x > 0; 
	}
	public List<AppealsDto> getAllAppealsDtos(){
		String sql = "select * from appealsdto";
		System.out.println("te st");
		return this.getJdbcTemplate().query(sql, new AppealsMapper());
		
	}
	public void deleteAppealsDtoForId(String claimId){
		String sql = "delete from appealsdto where claimId = ?";
		this.getJdbcTemplate().update(sql, new Object[]{claimId});
	}
	private final static class AppealsMapper implements RowMapper<AppealsDto> {

		public AppealsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			AppealsDto appealsDto = new AppealsDto();
			appealsDto.setPatientId(rs.getLong("patientId"));
			appealsDto.setDoctorId(rs.getLong("doctorId"));
			appealsDto.setFacilityId(rs.getLong("facilityId"));
			appealsDto.setInsuranceId(rs.getInt("insuranceId"));
			appealsDto.setDateOfService(rs.getDate("dateOfService"));
			appealsDto.setClaimId(rs.getString("claimId"));
			appealsDto.setDateCreated(rs.getDate("dateCreated"));
			appealsDto.setParOne(rs.getString("parOne"));
			appealsDto.setParTwo(rs.getString("parTwo"));
			return appealsDto;
		}}
		

	
}
