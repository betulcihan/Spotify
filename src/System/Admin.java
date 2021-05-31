package System;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Help.Helper;

public class Admin extends Members{
	Artist artist = new Artist();
	Connection con = null;
	Connection connect = null;
	Statement stat = null;
	Statement st = null;
	Connection conn = null;
	Connection connection = null;
	Statement statement = null;
	Connection cn = null;
	Statement state = null;
	ResultSet rSet = null;
	ResultSet rs = null;
	ResultSet result = null;
	ResultSet resultSet = null;
	PreparedStatement prep = null;
	PreparedStatement prepared = null;
	PreparedStatement preparedStatement = null; 
	String albumName, albumType;
	int sign;


	
	public Admin(int memberID, String memberName, String memberEmail, String memberPass, String memberCountry) {
		super(memberID, memberName, memberEmail, memberPass, memberCountry);
	}
	
	public Admin() {}

	public ArrayList<Members> getAdminList() {
		ArrayList<Members> adminList = new ArrayList<>();
		Members objMembers;
		try {
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			rSet = st.executeQuery("select * from admin");
			while (rSet.next()) {
				objMembers = new Members(rSet.getInt("admin_id"),rSet.getString("admin_name"),rSet.getString("admin_email"),rSet.getString("admin_pass"),rSet.getString("admin_country"));
				adminList.add(objMembers);
			}
			con.close();
			st.close();
			rSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminList;
	}
	
	public boolean deleteMusic(int musicID) {
		String queryString = "delete from music where music_id = ?";
		boolean sign = false;
		try {
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			prep = (PreparedStatement) con.prepareStatement(queryString);
			prep.setInt(1, musicID);
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
	

public boolean deleteAlbum(String musicName, String musicArtist, String albumName, String albumType) {
	String deleteAlbum = "delete from album where album.album_name = ? and album.album_type =  ?"
			+ " and album.album_music = ? and album.album_artist = ?";
	boolean sign = false;
	try {
		con = connectionHelper.connectionHelper();
		st = (Statement) con.createStatement();
			
		prep = (PreparedStatement) con.prepareStatement(deleteAlbum);
		prep.setString(1, albumName);
		prep.setString(2, albumType);
		prep.setString(3, albumType);
		prep.setString(4, musicArtist);
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
	
	public boolean addMusic(String artistCountry, int albumID, String musicName, String musicDate, String musicArtist, String musicType, int musicTime, int musicPlay) {
		int p=0, q=0, s=0;

		String queryString = "insert into music" + "(music_name,music_date,music_artist,music_album,music_type,music_time,music_play, music_country) values" + "(?,?,?,?,?,?,?,?)";
		String queryAlbum = "insert into album" + "(album_name, album_artist, album_music, album_date, album_type) values" + "(?,?,?,?,?)";	
		String query = "select * from album,music where music.music_name = '" + musicName + "' and album.album_id = " + albumID + " and " 
				+ "music.music_album = album.album_name and music.music_type = album.album_type and album.album_artist = music.music_artist"
				+ " and music.music_country = '" + artistCountry + "'";
		String queryArtist = "insert into artist" + "(artist_name, artist_country) values" + "(?,?)";	
		String queryAlbumExcist = "select * from album where album.album_id = " + albumID;
		String musicRepeat = "select * from album where album.album_music = '" +  musicName +"' " +
				" and album.album_type = '" + musicType +"' ";
				
		boolean sign = false;
		try {
			cn = connectionHelper.connectionHelper();
			conn = connectionHelper.connectionHelper();
			connect = connectionHelper.connectionHelper();
			stat = (Statement) connect.createStatement();
			state = (Statement) cn.createStatement();
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			prep = (PreparedStatement) con.prepareStatement(queryString);
			prepared = (PreparedStatement) conn.prepareStatement(queryAlbum);
			preparedStatement = (PreparedStatement) con.prepareStatement(queryArtist);
			rSet = st.executeQuery(query);
			result = stat.executeQuery(queryAlbumExcist);
			resultSet = state.executeQuery(musicRepeat);
			while(rSet.next()) {
				q = 1;
			}
			while(result.next()) {
				p = 1;
				albumType = result.getString("album_type");
				albumName = result.getString("album_name");
			}
			while(resultSet.next()) {
				s = 1;
			}
			if(q==1) {
				Helper.showMessage("alreadyAdded");
			}else if (!albumType.equals(musicType)) {
				Helper.showMessage("differentType");
			}else if(p==0){
				Helper.showMessage("albumNotExcist");
			}else if(s==1){
				Helper.showMessage("musicInOneAlbum");
			}else {
			
				System.out.println(musicName);
				System.out.println(musicDate);
				System.out.println(musicArtist);
				System.out.println(albumName);
				System.out.println(musicType);
				System.out.println(musicTime);
				System.out.println(musicPlay);
				System.out.println(artistCountry);

				Helper.showMessage("musicAdded");
				prep.setString(1, musicName);
				prep.setString(2, musicDate);
				prep.setString(3, musicArtist);
				prep.setString(4, albumName);
				prep.setString(5, musicType);
				prep.setInt(6, musicTime);
				prep.setInt(7, musicPlay);
				prep.setString(8, artistCountry);
				prep.executeUpdate();
				
				prepared.setString(1, albumName);
				prepared.setString(2, musicArtist);
				prepared.setString(3, musicName);
				prepared.setString(4, musicDate);
				prepared.setString(5, musicType);
				prepared.executeUpdate();
				
				artist.addArtist(musicArtist, artistCountry);
				sign = true;
				con.close();
				st.close();
				prep.close();
				prepared.close();
			}
			conn.close();
			stat.close();
			connect.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(sign)
			return true;
		else 
			return false;
	}

}
