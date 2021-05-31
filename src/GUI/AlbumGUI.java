package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Help.ConnectionHelper;
import Help.Helper;
import System.Music;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AlbumGUI extends JFrame {

	private JPanel contentPane;
	private JTable musicTable;
	private JTextField albumNameField;
	private JTextField albumTypeField;
	private DefaultTableModel musicModel = null;
	private Object[] musicObjects = null;
	Music music = new Music();
	ConnectionHelper connectionHelper = new ConnectionHelper();
	Connection con = null;
	Connection connect = null;
	Statement stat = null;
	Statement st = null;
	Connection conn = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet rSet = null;
	ResultSet rs = null;
	ResultSet result = null;
	PreparedStatement prep = null;
	PreparedStatement prepared = null;
	PreparedStatement preparedStatement = null; 
	PreparedStatement preparedStat = null; 
	String musicAlbum, musicType, musicArtist, musicDate, musicName;
	int sign, musicId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlbumGUI frame = new AlbumGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlbumGUI() {
		setTitle("Alb\u00FCm Olu\u015Ftur");
		createMusicPage();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 594);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 733, 279);
		contentPane.add(scrollPane);
		
		musicTable = new JTable(musicModel);
		scrollPane.setViewportView(musicTable);
		
		JLabel lblNewLabel = new JLabel("Alb\u00FCm Ad\u0131:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(178, 374, 125, 38);
		contentPane.add(lblNewLabel);
		
		albumNameField = new JTextField();
		albumNameField.setBounds(348, 376, 213, 38);
		contentPane.add(albumNameField);
		albumNameField.setColumns(10);
		
		JLabel lblAlbmTr = new JLabel("Alb\u00FCm T\u00FCr\u00FC:");
		lblAlbmTr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAlbmTr.setBounds(178, 441, 125, 38);
		contentPane.add(lblAlbmTr);
		
		
		musicTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					musicId = (int) musicTable.getValueAt(musicTable.getSelectedRow(), 0);
					musicAlbum = musicTable.getValueAt(musicTable.getSelectedRow(), 4).toString();
					musicType = musicTable.getValueAt(musicTable.getSelectedRow(), 5).toString();
					musicArtist = musicTable.getValueAt(musicTable.getSelectedRow(), 3).toString();
					musicDate = musicTable.getValueAt(musicTable.getSelectedRow(), 2).toString();
					musicName = musicTable.getValueAt(musicTable.getSelectedRow(), 1).toString();
				} catch (Exception e2) {
				}
			}
		});
		
		albumTypeField = new JTextField();
		albumTypeField.setColumns(10);
		albumTypeField.setBounds(348, 443, 213, 38);
		contentPane.add(albumTypeField);
		
		JButton btnAddAlbum = new JButton("Güncelle / Ekle");
		btnAddAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean control = addAlbum(musicId, musicName, musicAlbum, musicType, musicArtist, musicDate, albumNameField.getText(), albumTypeField.getText());
				if(control)
					updateMusicPage();
			}
		});
		btnAddAlbum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddAlbum.setBounds(437, 509, 265, 38);
		contentPane.add(btnAddAlbum);
		
		JButton btnGoBack = new JButton("Geri Dön");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminGUI adminGUI = new AdminGUI(LogInGUI.admin);
				adminGUI.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGoBack.setBounds(92, 509, 131, 38);
		contentPane.add(btnGoBack);
		
		JLabel lblNewLabel_1 = new JLabel("G\u00FCncellemek / Alb\u00FCme Eklemek \u0130stedi\u011Finiz \u015Eark\u0131y\u0131 Se\u00E7in");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 10, 623, 45);
		contentPane.add(lblNewLabel_1);
	}
	
	
	public boolean addAlbum(int musicID, String musicName, String musicAlbum, String musicType, String musicArtist, String musicDate, String albumName, String albumType) {
		int p=0, q=0;
		String albumname = albumName;
		String albumtype = albumType;
		String updateMusic = "update music set music_album = ? where music_id = " + musicID;
		String updateAlbum = "update album set album_name = ? where album_music = '" + musicName + "'";
		String queryAlbum = "insert into album" + "(album_name, album_artist, album_music, album_date, album_type) values" + "(?,?,?,?,?)";	
		String deleteAlbum = "delete from album where album_name = ? and album_type = ? and album_music = ?";
		String queryAlbumExcist = "select * from album where album_name = '" + albumname + "' and album_type = '" + albumtype + "'";
		String queryAlreadyAdded = "select * from album,music where album_name = '" + albumname + "' and album_type = '" 
		+ albumtype + "' and music_id = " + musicID + " and music_album = album_name";
		System.out.println(musicID);
		System.out.println(albumname);
		System.out.println(albumtype);

		boolean sign = false;
		try {
			connection = connectionHelper.connectionHelper();
			conn = connectionHelper.connectionHelper();
			connect = connectionHelper.connectionHelper();
			stat = (Statement) connect.createStatement();
			con = connectionHelper.connectionHelper();
			st = (Statement) con.createStatement();
			prep = (PreparedStatement) con.prepareStatement(queryAlbum);
			prepared = (PreparedStatement) conn.prepareStatement(updateMusic);
			preparedStatement = (PreparedStatement) con.prepareStatement(deleteAlbum);
			preparedStat = (PreparedStatement) connection.prepareStatement(updateAlbum);
			rSet = st.executeQuery(queryAlreadyAdded);
			result = stat.executeQuery(queryAlbumExcist);
			while(rSet.next()) {
				q = 1;
			}
			while(result.next()) {
				p = 1;
			}
			if(q==1) {
				Helper.showMessage("alreadyAdded");
			}else if (!albumType.equals(musicType)) {
				Helper.showMessage("differentType");
			}else if(p==1){
				Helper.showMessage("albumUpdate");
				prepared.setString(1, albumName);
				prepared.executeUpdate();
				
				preparedStat.setString(1, albumName);
				preparedStat.executeUpdate();
				prepared.close();
				preparedStat.close();
				connection.close();
				updateMusicPage();
			}else {
				Helper.showMessage("addToAlbum");
				preparedStatement.setString(1, musicAlbum);
				preparedStatement.setString(2, musicType);
				preparedStatement.setString(3, musicName);
				preparedStatement.executeUpdate();
				
				prep.setString(1, albumName);
				prep.setString(2, musicArtist);
				prep.setString(3, musicName);
				prep.setString(4, musicDate);
				prep.setString(5, albumType);
				prep.executeUpdate();
				
				prepared.setString(1, albumName);
				prepared.executeUpdate();
				
				sign = true;
				con.close();
				st.close();
				prep.close();
				prepared.close();
				preparedStatement.close();
				updateMusicPage();
			}
			conn.close();
			stat.close();
			connect.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		if(sign)
			return true;
		else 
			return false;
	}
	
	public void createMusicPage() {
		musicModel = new DefaultTableModel();
		Object[] adminColName = new Object[9];
		adminColName[0] = "ID";
		adminColName[1] = "Ýsim";
		adminColName[2] = "Çýkýþ Tarihi";
		adminColName[3] = "Sanatçý";
		adminColName[4] = "Albüm";
		adminColName[5] = "Tür";
		adminColName[6] = "Süre";
		adminColName[7] = "Oynatma Sayýsý";
		adminColName[8] = "Ülke";
		musicModel.setColumnIdentifiers(adminColName);
		musicObjects = new Object[9];

		for (int i = 0; i < music.getMusicList("music").size(); i++) {
			musicObjects[0] = music.getMusicList("music").get(i).getMusicID();
			musicObjects[1] = music.getMusicList("music").get(i).getMusicName();
			musicObjects[2] = music.getMusicList("music").get(i).getMusicDate();
			musicObjects[3] = music.getMusicList("music").get(i).getMusicArtist();
			musicObjects[4] = music.getMusicList("music").get(i).getMusicAlbum();
			musicObjects[5] = music.getMusicList("music").get(i).getMusicType();
			musicObjects[6] = music.getMusicList("music").get(i).getMusicTime();
			musicObjects[7] = music.getMusicList("music").get(i).getMusicPlay();
			musicObjects[8] = music.getMusicList("music").get(i).getMusicCountry();
			musicModel.addRow(musicObjects);
		}
	}
	
	public void updateMusicPage() {
		DefaultTableModel updateTable = (DefaultTableModel) musicTable.getModel();
		updateTable.setRowCount(0);
		for (int i = 0; i < music.getMusicList("music").size(); i++) {
			musicObjects[0] = music.getMusicList("music").get(i).getMusicID();
			musicObjects[1] = music.getMusicList("music").get(i).getMusicName();
			musicObjects[2] = music.getMusicList("music").get(i).getMusicDate();
			musicObjects[3] = music.getMusicList("music").get(i).getMusicArtist();
			musicObjects[4] = music.getMusicList("music").get(i).getMusicAlbum();
			musicObjects[5] = music.getMusicList("music").get(i).getMusicType();
			musicObjects[6] = music.getMusicList("music").get(i).getMusicTime();
			musicObjects[7] = music.getMusicList("music").get(i).getMusicPlay();
			musicObjects[8] = music.getMusicList("music").get(i).getMusicCountry();
			musicModel.addRow(musicObjects);
		}
	}
}
