package com.docgen.tempservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.docgen.tempservice.model.Insurance;

@Component
public class InsuranceDao extends JdbcDaoSupport {
	
	public void insertInsurance(Insurance insurance){
		String sql = "insert into insurance (name, dept, street, town, state, zip, phone) values (?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[]{insurance.getName(), insurance.getDept(), insurance.getStreet(),
								insurance.getTown(), insurance.getState(), insurance.getZip(), insurance.getPhone()});
	}
	public List<Insurance> getAllInsurances(){
		String sql = "select * from insurance";
		return this.getJdbcTemplate().query(sql, new InsuranceMapper());
	}
	public Insurance getInsuranceForId(long id){
		String sql = "select * from insurance where id = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new InsuranceMapper());
	}
	public void deleteInsurance(long id){
		String sql = "delete from insurance where id = ? ";
		this.getJdbcTemplate().update(sql, new Object[]{id});
	}
	public void updateInsurance(Insurance ins){
		String sql = "update insurance set name=?, dept=?, street=?, town=?, state=?, zip=? phone=? where id=?";
		this.getJdbcTemplate().update(sql, new Object[]{ins.getName(), ins.getDept(), ins.getStreet(), ins.getTown(),
				ins.getState(), ins.getPhone(), ins.getId()});
	}
	private final static class InsuranceMapper implements RowMapper<Insurance> {

		public Insurance mapRow(ResultSet rs, int rowNum) throws SQLException {
			Insurance insurance = new Insurance();
			insurance.setId(rs.getLong("id"));
			insurance.setName(rs.getString("name"));
			insurance.setDept(rs.getString("dept"));
			insurance.setStreet(rs.getString("street"));
			insurance.setTown(rs.getString("town"));
			insurance.setState(rs.getString("state"));
			insurance.setZip(rs.getString("zip"));
			insurance.setPhone(rs.getString("phone"));
			return insurance;
		}
		
	}
}
