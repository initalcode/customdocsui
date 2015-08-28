package com.docgen.tempservice.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.docgen.tempservice.model.CollectionDashboardDto;
import com.docgen.tempservice.model.CollectionDto;


@Component
public class CollectionDao extends JdbcDaoSupport{

	public long addCollectionLetter(final CollectionDto dto){
		final String sql = "insert into collectiondto (patientid, facilityid, dateCreated, payment, letterType) values (?, ?, ?,?, ?)";
		//return this.getJdbcTemplate().update(sql, new Object[]{dto.getPatientId(), dto.getFacilityId(), dto.getDateCreated(), dto.getPayment()});
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
		  new PreparedStatementCreator() {
			@Override
			public java.sql.PreparedStatement createPreparedStatement(
					Connection con) throws SQLException {
			    PreparedStatement pst = con.prepareStatement(sql, new String[] {"collectionid"});
			    pst.setLong(1, dto.getPatientId());
			    pst.setLong(2, dto.getFacilityId());
			    pst.setDate(3, new java.sql.Date(dto.getDateCreated().getTime()));
			    pst.setString(4, dto.getPayment());
			    pst.setString(5, dto.getLetterType());
			    return pst;
			}
		  }, keyHolder);
		return (long)keyHolder.getKey() ;
	}
	public void updateCollectionLetter(CollectionDto dto){
		String sql = "update collectiondto set payment = ? where collectionid = ?";
		this.getJdbcTemplate().update(sql, new Object[]{dto.getPayment(), dto.getCollectionId()});
	}
	public void updateLetterType(CollectionDashboardDto dto){
		String sql = "update collectiondto set letterType = ?, dateCreated = ? where collectionid = ?";
		this.getJdbcTemplate().update(sql, new Object[]{dto.getLetterType(), dto.getDateCreated(),dto.getCollectionId()});
	}
	public Boolean letterExists(long collectionId){
		String sql = "select count(*) from collectiondto where collectionId = ?";
		Integer x = this.getJdbcTemplate().queryForObject(sql, Integer.class, collectionId);
		return x != null && x > 0; 
	}
	public List<CollectionDashboardDto> getCollectionLetters(){
		String sql = "select  p.name, f.facilityName, dto.dateCreated, dto.payment, dto.collectionid, dto.letterType from "
				  +	"collectiondto dto, patient p, facility f where p.id = dto.patientid and f.id = dto.facilityid"
				  +	" group by p.name, f.facilityName, dto.payment, dto.dateCreated, dto.collectionid order by dto.dateCreated asc";
		return this.getJdbcTemplate().query(sql, new CollectionMapper());
	}
	public CollectionDto getCollectionLetter(long id){
		String sql = "select * from collectiondto where collectionId = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new CollectionDtoMapper());
	}
	public void removeCollectionLetter(long id){
		String sql = "delete from collectiondto where collectionid = ?";
		this.getJdbcTemplate().update(sql, new Object[]{id});
	}
	public static final class CollectionDtoMapper implements RowMapper<CollectionDto>{

		@Override
		public CollectionDto mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			CollectionDto dto = new CollectionDto();
			dto.setCollectionId(rs.getLong("collectionId"));
			dto.setDateCreated(rs.getDate("dateCreated"));
			dto.setFacilityId(rs.getLong("facilityId"));
			dto.setPatientId(rs.getLong("patientId"));
			dto.setPayment(rs.getString("payment"));
			dto.setLetterType(rs.getString("letterType"));
			return dto;
		}
		
	}
	public static final class CollectionMapper implements RowMapper<CollectionDashboardDto>{

		@Override
		public CollectionDashboardDto mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			CollectionDashboardDto dto = new CollectionDashboardDto();
			dto.setDateCreated(rs.getDate("dateCreated"));
			dto.setFacilityName(rs.getString("facilityName"));
			dto.setPatientName(rs.getString("name"));
			dto.setPayment(rs.getString("payment"));
			dto.setCollectionId(rs.getLong("collectionId"));
			dto.setLetterType(rs.getString("letterType"));
			return dto;
		}
		
		
	}
}
