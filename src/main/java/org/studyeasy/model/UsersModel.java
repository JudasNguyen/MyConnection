package org.studyeasy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.studyeasy.config.DatabaseConfig;
import org.studyeasy.entity.User;

public class UsersModel {
	public List<User> listuser() {
		
		List<User> listusers = new ArrayList<User>();
		
		// Step 1: connection object initialization
		Connection connect = DatabaseConfig.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		System.out.println(connect);
		//Step 2: Create the DB query
		String query ="SELECT * FROM studyeasy.users";
		try {
			stmt = connect.createStatement();
			

			// Step 3: Execution of statement
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				System.out.println(connect);
				listusers.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return listusers;
	}
	
	public void addUser (User newUser) {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DatabaseConfig.getConnection();
			String username = newUser.getUsername();
			String email = newUser.getEmail();
			String query = "insert into users (username, email) values (?,?)";
			statement = con.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.execute();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser (User updateUser) {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DatabaseConfig.getConnection();
			int UserId = updateUser.getUser_id();
			String username = updateUser.getUsername();
			String email = updateUser.getEmail();
			String query = "update users set username = ? , email = ? , where user_id = ?";
			statement = con.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setInt(3, UserId);
			statement.execute();
					
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser (int userID) {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DatabaseConfig.getConnection();
			String query = "delete from users where user_id = ?";
			statement = con.prepareStatement(query);
			statement.setInt(3, userID);
			statement.execute();
					
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
