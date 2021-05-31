package System;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import GUI.LogInGUI;
import Help.ConnectionHelper;

public class Playlist {
int playlistID, userID, musicID, musicTime, musicPlay;
String playlistType, musicName, musicDate, musicArtist, musicAlbum, musicType, musicCountry;

ConnectionHelper connectionHelper = new ConnectionHelper();
Connection con = null;
Statement st = null;
ResultSet rSet = null;
PreparedStatement prep = null;

public Playlist(int playlistID, int userID, int musicID, String playlistType, String musicName, String musicDate, String musicArtist,
		String musicAlbum, String musicType, int musicTime, int musicPlay, String musicCountry) {
	this.playlistID = playlistID;
	this.userID = userID;
	this.musicID = musicID;
	this.playlistType = playlistType;
	this.musicTime = musicTime;
	this.musicPlay = musicPlay;
	this.musicName = musicName;
	this.musicDate = musicDate;
	this.musicArtist = musicArtist;
	this.musicAlbum = musicAlbum;
	this.musicType = musicType;
	this.musicCountry = musicCountry;
}

public Playlist() {}

public ArrayList<Playlist> getPopList(int listenUserID){
	ArrayList<Playlist> popList = new ArrayList<>();
	Playlist objPop;
	try {
		con = connectionHelper.connectionHelper();
		st = (Statement) con.createStatement();
		rSet = st.executeQuery("select * from playlist,music where playlist.playlist_type = 'pop' and music.music_id = playlist.music_id and playlist.user_id = " + listenUserID);
		while (rSet.next()) {
			objPop = new Playlist(rSet.getInt("playlist_id"), rSet.getInt("user_id"), rSet.getInt("music_id"), rSet.getString("playlist_type"), rSet.getString("music_name"), rSet.getString("music_date"), rSet.getString("music_artist"),rSet.getString("music_album"),rSet.getString("music_type"),
					rSet.getInt("music_time"),rSet.getInt("music_play"), rSet.getString("music_country"));
			popList.add(objPop);
		}
		con.close();
		st.close();
		rSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return popList;
}

public ArrayList<Playlist> getClassicalList(int listenUserID){
	ArrayList<Playlist> classicalList = new ArrayList<>();
	Playlist objClassical;
	try {
		con = connectionHelper.connectionHelper();
		st = (Statement) con.createStatement();
		rSet = st.executeQuery("select * from playlist,music where playlist.playlist_type = 'klasik' and music.music_id = playlist.music_id and playlist.user_id = " + listenUserID);
		while (rSet.next()) {
			objClassical = new Playlist(rSet.getInt("playlist_id"), rSet.getInt("user_id"), rSet.getInt("music_id"), rSet.getString("playlist_type"), rSet.getString("music_name"), rSet.getString("music_date"), rSet.getString("music_artist"),rSet.getString("music_album"),rSet.getString("music_type"),
					rSet.getInt("music_time"),rSet.getInt("music_play"), rSet.getString("music_country"));
			classicalList.add(objClassical);
		}
		con.close();
		st.close();
		rSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return classicalList;
}


public ArrayList<Playlist> getJazzList(int listenUserID){
	ArrayList<Playlist> jazzList = new ArrayList<>();
	Playlist objJazz;
	try {
		con = connectionHelper.connectionHelper();
		st = (Statement) con.createStatement();
		rSet = st.executeQuery("select * from playlist,music where playlist.playlist_type = 'jazz' and music.music_id = playlist.music_id and playlist.user_id = " + listenUserID);
		while (rSet.next()) {
			objJazz = new Playlist(rSet.getInt("playlist_id"), rSet.getInt("user_id"), rSet.getInt("music_id"), rSet.getString("playlist_type"), rSet.getString("music_name"), rSet.getString("music_date"), rSet.getString("music_artist"),rSet.getString("music_album"),rSet.getString("music_type"),
					rSet.getInt("music_time"),rSet.getInt("music_play"), rSet.getString("music_country"));
			jazzList.add(objJazz);
		}
		con.close();
		st.close();
		rSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return jazzList;
}

public boolean isAddPlaylist(int selectedID, String selectedType) {
	int count = 0;
	String queryString = "insert into playlist (user_id, music_id, playlist_type) values (?,?,?)";
	System.out.println(selectedID);
	try {
		con = connectionHelper.connectionHelper();
		st = (Statement) con.createStatement();
		rSet = st.executeQuery(
				"select * from playlist,music where music.music_id = playlist.music_id and playlist.user_id = "
						+ LogInGUI.user.getMemberID() + " and playlist.music_id = " + selectedID);

		while (rSet.next()) {
			count++;
			break;
		}
		if (count == 0) {
			prep = (PreparedStatement) con.prepareStatement(queryString);
			prep.setInt(1, LogInGUI.user.getMemberID());
			prep.setInt(2, selectedID);
			prep.setString(3, selectedType);
			prep.executeUpdate();
			prep.close();
		}
		con.close();
		st.close();
		rSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	if (count == 0)
		return true;
	else
		return false;
}

public int getPlaylistID() {
	return playlistID;
}
public void setPlaylistID(int playlistID) {
	this.playlistID = playlistID;
}
public int getMusicID() {
	return musicID;
}
public void setMusicID(int musicID) {
	this.musicID = musicID;
}
public int getMusicTime() {
	return musicTime;
}
public void setMusicTime(int musicTime) {
	this.musicTime = musicTime;
}
public int getMusicPlay() {
	return musicPlay;
}
public void setMusicPlay(int musicPlay) {
	this.musicPlay = musicPlay;
}
public String getPlaylistType() {
	return playlistType;
}
public void setPlaylistType(String playlistType) {
	this.playlistType = playlistType;
}
public String getMusicName() {
	return musicName;
}
public void setMusicName(String musicName) {
	this.musicName = musicName;
}
public String getMusicDate() {
	return musicDate;
}
public void setMusicDate(String musicDate) {
	this.musicDate = musicDate;
}
public String getMusicArtist() {
	return musicArtist;
}
public void setMusicArtist(String musicArtist) {
	this.musicArtist = musicArtist;
}
public String getMusicAlbum() {
	return musicAlbum;
}
public void setMusicAlbum(String musicAlbum) {
	this.musicAlbum = musicAlbum;
}
public String getMusicType() {
	return musicType;
}
public void setMusicType(String musicType) {
	this.musicType = musicType;
}

public int getUserID() {
	return userID;
}

public void setUserID(int userID) {
	this.userID = userID;
}
public String getMusicCountry() {
	return musicCountry;
}

public void setMusicCountry(String musicCountry) {
	this.musicCountry = musicCountry;
}

}
