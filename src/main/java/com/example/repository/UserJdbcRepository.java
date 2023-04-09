package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Role;
import com.example.domain.User;

@Repository
public class UserJdbcRepository {
	
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public UserJdbcRepository(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public User getUser(Long userId) {
		String sql = "Select * from User where userId = :userId";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		
		return jdbcTemplate.query(sql, paramMap, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				User u = new User();
				System.out.println(rs.getString("userMobile"));
//				User u = new User(rs.getLong("userId"), rs.getString("username"), rs.getString("userPassword"), rs.getString("userEmail"), rs.getString("userMobile"),  rs.getString("userRoles"));   
				return u;
			}
			
		});
	}
}
