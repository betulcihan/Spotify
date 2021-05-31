package System;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Help.ConnectionHelper;
import Help.Helper;

public class Album {
int albumID;
String albumName, albumArtist, albumMusic, albumDate, albumType, albumCountry;
ConnectionHelper connectionHelper = new ConnectionHelper();
Connection con = null;
Statement st = null;
ResultSet rSet = null;
PreparedStatement prep = null;

public Album(int albumID, String albumName, String albumArtist, String albumMusic, String albumDate, String albumType, String albumCountry) {
	this.albumID = albumID;
	this.albumName = albumName;
	this.albumArtist = albumArtist;
	this.albumMusic = albumMusic;
	this.albumType = albumType;
	this.albumDate = albumDate;
	this.albumType = albumType;
	this.albumCountry = albumCountry;

}



public Album() {}


public ArrayList<Album> getAlbumList(){
	ArrayList<Album> albumList = new ArrayList<>();
	Album objAlbum;
	try {
		con = connectionHelper.connectionHelper();
		st = (Statement) con.createStatement();
		rSet = st.executeQuery("select * from album,music where album.album_music = music.music_name and album.album_type = music.music_type and"
				+ " album.album_date = music.music_date and album.album_artist = music.music_artist and album.album_name = music.music_album");
		while (rSet.next()) {
			objAlbum = new Album(rSet.getInt("album_id"), rSet.getString("album_name"), rSet.getString("album_artist"), rSet.getString("album_music"),
					rSet.getString("album_date"), rSet.getString("album_type"), rSet.getString("music_country"));
			albumList.add(objAlbum);
		}
		con.close();
		st.close();
		rSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return albumList;
}

public boolean deleteAlbum(int albumID) {
	String queryString = "delete from album where album_id = ?";
	boolean sign = false;
	try {
		con = connectionHelper.connectionHelper();
		st = (Statement) con.createStatement();
		prep = (PreparedStatement) con.prepareStatement(queryString);
		prep.setInt(1, albumID);
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

public boolean deleteAllAlbum(String albumName, String albumType) {
	String deleteAlbum = "delete from album where album_name = ? and album_type = ?";
	boolean sign = false;
	try {
		con = connectionHelper.connectionHelper();
		st = (Statement) con.createStatement();
			
		prep = (PreparedStatement) con.prepareStatement(deleteAlbum);
		prep.setString(1, albumName);
		prep.setString(2, albumType);
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


public boolean deleteMusic(String albumName, String albumType) {
	String deleteMusic = "delete from music where music.music_album = ? and music.music_type =  ?";
	boolean sign = false;
	try {
		con = connectionHelper.connectionHelper();
		st = (Statement) con.createStatement();
			
		prep = (PreparedStatement) con.prepareStatement(deleteMusic);
		prep.setString(1, albumName);
		prep.setString(2, albumType);
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

public int getAlbumID() {
	return albumID;
}
public void setAlbumID(int albumID) {
	this.albumID = albumID;
}
public String getAlbumName() {
	return albumName;
}
public void setAlbumName(String albumName) {
	this.albumName = albumName;
}
public String getAlbumArtist() {
	return albumArtist;
}
public void setAlbumArtist(String albumArtist) {
	this.albumArtist = albumArtist;
}
public String getAlbumDate() {
	return albumDate;
}
public void setAlbumDate(String albumDate) {
	this.albumDate = albumDate;
}
public String getAlbumType() {
	return albumType;
}
public void setAlbumType(String albumType) {
	this.albumType = albumType;
}
public String getAlbumMusic() {
	return albumMusic;
}
public void setAlbumMusic(String albumMusic) {
	this.albumMusic = albumMusic;
}
public String getAlbumCountry() {
	return albumCountry;
}
public void setAlbumCountry(String albumCountry) {
	this.albumCountry = albumCountry;
}

}
