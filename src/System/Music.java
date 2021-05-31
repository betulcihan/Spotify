package System;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import GUI.SignInGUI;
import Help.*;

public class Music implements Comparable{
	public int musicID, musicTime, musicPlay;
	public String  musicName, musicDate, musicArtist, musicAlbum, musicType, musicCountry;

	ConnectionHelper connectionHelper = new ConnectionHelper();
	Connection con = null;
	Statement st = null;
	ResultSet rSet = null;
	PreparedStatement prep = null;
	int sign = 0;

	public Music(int musicID, String musicName, String musicDate, String musicArtist, String musicAlbum, String musicType, int musicTime, int musicPlay, String musicCountry) {
		this.musicID = musicID; 
		this.musicName = musicName;
		this.musicDate = musicDate;
		this.musicArtist = musicArtist;
		this.musicAlbum = musicAlbum;
		this.musicType = musicType;
		this.musicTime = musicTime;
		this.musicPlay = musicPlay;
		this.musicCountry = musicCountry;
	}
	
	public Music(){}

	public ArrayList<Music> getMusicList(String type) {
		Music objMusic;
		ArrayList<Music> musicList = new ArrayList<>();
		try {
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			switch (type) {
			case "pop": 
				rSet = st.executeQuery("select * from music where music_type = 'pop'");
				break;
			case "klasik": 
				rSet = st.executeQuery("select * from music where music_type = 'klasik'");
				break;
			case "jazz": 
				rSet = st.executeQuery("select * from music where music_type = 'jazz'");
				break;
			case "music": 
				rSet = st.executeQuery("select * from music");
				break;
			default: 
				rSet = st.executeQuery("select * from music");
				break;		
			}
			while (rSet.next()) {
				objMusic = new Music(rSet.getInt("music_id"),rSet.getString("music_name"),rSet.getString("music_date"),rSet.getString("music_artist"),rSet.getString("music_album"),
						rSet.getString("music_type"),rSet.getInt("music_time"),rSet.getInt("music_play"), rSet.getString("music_country"));
				musicList.add(objMusic);
			}
			con.close();
			st.close();
			rSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return musicList;
	}
	
	public ArrayList<Music> getMusicCountryList(String country) {
		Music objMusicCountry;
		ArrayList<Music> musicCountryList = new ArrayList<>();
		try {
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			rSet = st.executeQuery("select * from music where music_country = '" + country + "'");
			while (rSet.next()) {
				objMusicCountry = new Music(rSet.getInt("music_id"),rSet.getString("music_name"),rSet.getString("music_date"),rSet.getString("music_artist"),rSet.getString("music_album"),
						rSet.getString("music_type"),rSet.getInt("music_time"),rSet.getInt("music_play"), rSet.getString("music_country"));
				musicCountryList.add(objMusicCountry);
				System.out.println(musicCountryList.get(0).musicCountry);
			}
			con.close();
			st.close();
			rSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return musicCountryList;
	}
	
	public boolean updateMusic(int musicID, String musicName, String musicDate, String musicArtist, String musicAlbum, String musicType, int musicTime, int musicPlay) {
    	sign = 0;
    	String updateMusic = "update music set music_name = ?, music_date = ?, music_artist = ?, music_album = ?,"
    			+ "music_type = ?, music_time = ?, music_play = ? where music_id = " + musicID;
    	try {
    		con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			prep = (PreparedStatement) con.prepareStatement(updateMusic);
			prep.setString(1, musicName);
			prep.setString(2, musicDate);
			prep.setString(3, musicArtist);
			prep.setString(4, musicAlbum);
			prep.setString(5, musicType);
			prep.setInt(6, musicTime);
			prep.setInt(7, musicPlay);
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
	
    public ArrayList<String> removeDuplicates(ArrayList<Music> musicList){
        ArrayList<String> stringCountry = new ArrayList<>();
        for(int i=0; i<musicList.size(); i++) {
        	stringCountry.add(musicList.get(i).getMusicCountry());
        }
        Set<String> set = new HashSet<>(stringCountry);
        stringCountry.clear();
        stringCountry.addAll(set);
        return stringCountry;
   }
        
	public String getMusicArtist() {
		return musicArtist;
	}
	public void setMusicArtist(String musicArtist) {
		this.musicArtist = musicArtist;
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
	public String getMusicCountry() {
		return musicCountry;
	}
	public void setMusicCountry(String musicCountry) {
		this.musicCountry = musicCountry;
	}
	
	@Override
	public int compareTo(Object comp) {
        int compareage=((Music)comp).getMusicPlay();
        return compareage-this.musicPlay;
	}
}
