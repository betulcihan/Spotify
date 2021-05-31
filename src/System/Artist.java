package System;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import GUI.LogInGUI;
import Help.ConnectionHelper;
import Help.Helper;

public class Artist {
	public String  artistName, artistCountry;
	public int artistID,artistMusic, s=0;

	ConnectionHelper connectionHelper = new ConnectionHelper();
	Connection con = null;
	Statement st = null;
	ResultSet rSet = null;
	PreparedStatement prep = null;
	int sign;

	public Artist(int artistID, String artistName, String artistCountry, int artistMusic) {
		this.artistID = artistID; 
		this.artistName = artistName;
		this.artistCountry = artistCountry;
		this.artistMusic = artistMusic; 
	}
	
	public Artist(){}
	Artist objArtist;

	public ArrayList<Artist> getArtistList() {
		ArrayList<Artist> artistList = new ArrayList<>();
		try {
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			rSet = st.executeQuery("select * from artist");
			while (rSet.next()) {
				objArtist = new Artist(rSet.getInt("artist_id"),rSet.getString("artist_name"),rSet.getString("artist_country"), rSet.getInt("music_id"));
				artistList.add(objArtist);
			}
			con.close();
			st.close();
			rSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artistList;
	}
	
	public ArrayList<Artist> getFollowedArtistList() {
		ArrayList<Artist> followedArtistList = new ArrayList<>();
		Artist objFollowedArtist;
		try {
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			rSet = st.executeQuery("select * from artist_follow,artist where artist_follow.artist_id = artist.artist_id "
					+ " and artist_follow.user_id = " + LogInGUI.user.getMemberID());
			while (rSet.next()) {
				objFollowedArtist = new Artist(rSet.getInt("artist_id"),rSet.getString("artist_name"),rSet.getString("artist_country"), rSet.getInt("music_id"));
				followedArtistList.add(objFollowedArtist);
			}
			con.close();
			st.close();
			rSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return followedArtistList;
	}
	
	public boolean addArtist(String artistName, String artistCountry) {
		s=0;
		String queryString = "insert into artist" + "(artist_name, artist_country) values" + "(?,?)";
		String isExcist = "select * from artist where artist_name = '" + artistName + "' and artist_country = '" + artistCountry +"'";
		boolean sign = false;
		try {
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			rSet = st.executeQuery(isExcist);
			while(rSet.next()) {
				s = 1;
			}
			if(s==0) {
				Helper.showMessage("artistAdded");
				prep = (PreparedStatement) con.prepareStatement(queryString);
				prep.setString(1, artistName);
				prep.setString(2, artistCountry);
				prep.executeUpdate();
				sign = true;
				prep.close();
			}else {
				Helper.showMessage("artistExcist");
			}
			con.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(sign)
			return true;
		else 
			return false;
	}
	
	public boolean updateArtist(int artistID, String artistName, String artistCountry){
    	sign = 0;
    	String updateMusic = "update artist set artist_name = ?, artist_country = ? where artist_id = " + artistID;
    	try {
    		con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			prep = (PreparedStatement) con.prepareStatement(updateMusic);
			prep.setString(1, artistName);
			prep.setString(2, artistCountry);
			prep.executeUpdate();
			
			sign = 1;
			prep.close();
	    	con.close();
	    	st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	if(sign == 1)
    		return true;
    	else 
			return false;    	
    }

	
	public boolean deleteArtist(int artistID) {
		String queryString = "delete from artist where artist_id = ?";
		boolean sign = false;
		try {
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			prep = (PreparedStatement) con.prepareStatement(queryString);
			prep.setInt(1, artistID);
			prep.executeUpdate();
			sign = true;
			con.close();
			st.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(sign)
			return true;
		else 
			return false;
	}

	public int getArtistMusic() {
		return artistMusic;
	}

	public void setArtistMusic(int artistMusic) {
		this.artistMusic = artistMusic;
	}
	public int getArtistID() {
		return artistID;
	}

	public void setArtistID(int artistID) {
		this.artistID = artistID;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getArtistCountry() {
		return artistCountry;
	}

	public void setArtistCountry(String artistCountry) {
		this.artistCountry = artistCountry;
	}
}
