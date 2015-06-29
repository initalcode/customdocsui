package com.docgen.tempservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.docgen.tempservice.model.AppealsDashboardDto;

@Component
public class AppealsDashboardDao extends JdbcDaoSupport{

	public List<AppealsDashboardDto> getAppealsDashboard() {
		String sql = "select  p.name, d.name, f.facilityName, appdto.claimId, appdto.dateOfService, appdto.dateCreated "
				+ "from appealsdto appdto, doctor d, patient p, facility f where p.id = appdto.patientid and "
				+ "d.id = appdto.doctorid and appdto.facilityid = f.id group by p.name, d.name, f.facilityName,appdto.claimId, appdto.dateOfService, "
				+ "appdto.dateCreated order by appdto.dateCreated asc";
		return this.getJdbcTemplate().query(sql, new AppealsDashboardDtoMapper());
	}
	private final static class AppealsDashboardDtoMapper implements RowMapper<AppealsDashboardDto>{

		public AppealsDashboardDto mapRow(ResultSet rs, int rowNum)	throws SQLException {
			AppealsDashboardDto appealsDto = new AppealsDashboardDto();
			appealsDto.setPatientName(rs.getString("name"));
			appealsDto.setDoctorName(rs.getString("name"));
			appealsDto.setFacilityName(rs.getString("facilityName"));
			appealsDto.setClaimId(rs.getString("claimId"));
			appealsDto.setDateOfService(rs.getDate("dateOfService"));
			appealsDto.setDateCreated(rs.getDate("dateCreated"));
			return appealsDto;
		}
		
	}
}
