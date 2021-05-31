package System;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import GUI.LogInGUI;

public class User extends Members {
	public String premium;
	Statement statement = null;
	ResultSet result = null;
	Connection cn = null;
	PreparedStatement prep = null;
	
	public User(int memberID, String memberName, String memberEmail, String memberPass, String memberCountry, String premium) {
		super(memberID, memberName, memberEmail, memberPass, memberCountry);
		this.premium = premium;
	}
	public User() {}
	
	public boolean isSignUp(String username, String email, String pass, String premium, String country) {
		int sign = 0;
		int count = 0;
		String queryString = "insert into user (user_name, user_email, user_pass, user_premium, user_country) values (?,?,?,?,?)";
		
		try {
			cn  = connectionHelper.connectionHelper();
			statement = (Statement) cn.createStatement();
			result = statement.executeQuery("select * from user where user_name = '" + username + "' and user_email = '" + email +"'" );
			
			while (result.next()) {
				count++;
				break;
			}
			if(count == 0) {
				prep = (PreparedStatement) cn.prepareStatement(queryString);
				prep.setString(1, username);
				prep.setString(2, email);
				prep.setString(3, pass);
				prep.setString(4, premium);
				prep.setString(5, country);
				prep.executeUpdate();
			}
			sign = 1;
			cn.close();
			statement.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(sign == 1)
			return true;
		else 
			return false;
	}

	
	public ArrayList<User> getUserList() {
		ArrayList<User> userList = new ArrayList<>();
		User objUser;
		try {
			cn  = connectionHelper.connectionHelper();
			statement = (Statement) cn.createStatement();
			result = statement.executeQuery("select * from user");
			while (result.next()) {
				objUser = new User(result.getInt("user_id"),result.getString("user_name"),result.getString("user_email"),result.getString("user_pass"),result.getString("user_country"),
						result.getString("user_premium"));
				userList.add(objUser);
			}
			cn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public ArrayList<User> getFollowedList() {
		ArrayList<User> followedList = new ArrayList<>();
		User objFollowed;
		try {
			cn  = connectionHelper.connectionHelper();
			statement = (Statement) cn.createStatement();
			result = statement.executeQuery("select * from user,follow where user.user_id = follow.follow_id and mydb.follow.user_id = " + LogInGUI.user.getMemberID());
			while (result.next()) {
				objFollowed = new User(result.getInt("follow_id"),result.getString("user_name"),result.getString("user_email"),result.getString("user_pass"),result.getString("user_country"),
						result.getString("user_premium"));
				followedList.add(objFollowed);
			}
			cn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return followedList;
	}
	
	public ArrayList<User> getFollowerList() {
		ArrayList<User> followerList = new ArrayList<>();
		User objFollower;
		try {
			cn  = connectionHelper.connectionHelper();
			statement = (Statement) cn.createStatement();
			result = statement.executeQuery("select * from user,follow where user.user_id = follow.user_id and follow.follow_id = " + LogInGUI.user.getMemberID());
			while (result.next()) {
				objFollower = new User(result.getInt("user_id"),result.getString("user_name"),result.getString("user_email"),result.getString("user_pass"),result.getString("user_country"),
						result.getString("user_premium"));
				followerList.add(objFollower);
			}
			cn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return followerList;
	}
	
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
} 