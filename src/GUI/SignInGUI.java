package GUI;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import Help.*;
import System.Album;
import System.Artist;
import System.Music;
import System.Playlist;
import System.User;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SignInGUI extends JFrame {
	int d = 0, idMusic, playMusic, a = 0, b = 0, listSize, unfollowID, followArtistID, unfollowArtistID;
	static int followID;
	String inf, musicType, country;
	private Music music = new Music();
	private Artist artist = new Artist();
	private Album album = new Album();
	Playlist playlist = new Playlist();
	AdminGUI adminGUI = new AdminGUI(LogInGUI.admin);
	private DefaultTableModel musicModel = null;
	private DefaultTableModel artistModel = null;
	private DefaultTableModel albumModel = null;
	private DefaultTableModel followedModel = null;
	private DefaultTableModel followerModel = null;
	private DefaultTableModel popModel = null;
	private DefaultTableModel classicalModel = null;
	private DefaultTableModel jazzModel = null;
	private DefaultTableModel userModel = null;
	private DefaultTableModel top10PopModel = null;
	private DefaultTableModel top10ClassicalModel = null;
	private DefaultTableModel top10JazzModel = null;
	private DefaultTableModel top10GeneralModel = null;
	private DefaultTableModel top10CountryModel = null;
	private DefaultTableModel followArtistModel = null;
	private Object[] followArtistObjects = null;
	private Object[] top10PopObjects = null;
	private Object[] top10GeneralObjects = null;
	private Object[] top10CountryObjects = null;
	private Object[] top10ClassicalObjects = null;
	private Object[] top10JazzObjects = null;
	private Object[] popObjects = null;
	private Object[] classicalObjects = null;
	private Object[] jazzObjects = null;
	private Object[] followerObjects = null;
	private Object[] followedObjects = null;
	private Object[] musicObjects = null;
	private Object[] albumObjects = null;
	private Object[] artistObjects = null;
	private Object[] userObjects = null;
	private JPanel signInContentPane;
	private JTable signInMusicTable;
	private JTable signInArtistTable;
	private JTable signInUsersTable;
	ConnectionHelper connectionHelper = new ConnectionHelper();
	Statement statement = null;
	ResultSet result = null;
	Connection con;
	PreparedStatement prep = null;
	private JTable followedTable;
	private JTable followerTable;
	private JTable popTable;
	private JTable classicalTable;
	private JTable jazzTable;
	private JTable signInAlbumTable;
	private JTable popTop10Table;
	private JTable classicalTop10Table;
	private JTable jazzTop10Table;
	private JTable generalTop10Table;
	private JTable countryTop10Table;
	private JTable followArtistTable;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInGUI frame = new SignInGUI(LogInGUI.user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignInGUI(User user) {
		setBackground(new Color(176, 196, 222));
		createUserPage();
		createMusicPage();
		createAlbumPage();
		createArtistPage();
		createFollowedPage();
		createFollowerPage();
		createPopPage();
		createClassicalPage();
		createJazzPage();
		top10PopPage();
		top10ClassicalPage();
		top10JazzPage();
		top10GeneralPage();
		top10CountryPage();
		createFollowArtistPage();

		setTitle("MyDB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 629);
		signInContentPane = new JPanel();
		signInContentPane.setBackground(new Color(176, 196, 222));
		signInContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(signInContentPane);
		signInContentPane.setLayout(null);

		JLabel lblSignInWelcome = new JLabel("Hoþgeldiniz " + LogInGUI.user.getMemberName());
		lblSignInWelcome.setBackground(new Color(176, 196, 222));
		lblSignInWelcome.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblSignInWelcome.setBounds(49, 23, 315, 38);
		signInContentPane.add(lblSignInWelcome);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 71, 902, 511);
		signInContentPane.add(tabbedPane);

		JPanel panelMusic = new JPanel();
		tabbedPane.addTab("Þarkýlar", null, panelMusic, null);

		JScrollPane signInScrollPane = new JScrollPane();
		signInScrollPane.setBounds(10, 10, 878, 388);
		panelMusic.add(signInScrollPane);

		signInMusicTable = new JTable(musicModel);
		signInScrollPane.setViewportView(signInMusicTable);
		panelMusic.setLayout(null);

		signInMusicTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					idMusic = (int) signInMusicTable.getValueAt(signInMusicTable.getSelectedRow(), 0);
					musicType = (String) signInMusicTable.getValueAt(signInMusicTable.getSelectedRow(), 5);
					playMusic = (int) signInMusicTable.getValueAt(signInMusicTable.getSelectedRow(), 7);
				} catch (Exception e2) {
				}
			}
		});

		

		JButton btnListenMusic = new JButton("Dinle");
		btnListenMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String queryString = "update music set music_play = ? where music_id = " + idMusic;
				try {
					con = connectionHelper.connectionHelper();
					statement = (Statement) con.createStatement();
					prep = (PreparedStatement) con.prepareStatement(queryString);
					prep.setInt(1, (playMusic + 1));
					prep.executeUpdate();
					updateMusicPage();
					con.close();
					statement.close();
					prep.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListenMusic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListenMusic.setBounds(225, 419, 123, 55);
		panelMusic.add(btnListenMusic);

		JButton btnAddToPlaylist = new JButton("Çalma Listesine Ekle");
		btnAddToPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(idMusic);
				boolean isAdded = playlist.isAddPlaylist(idMusic, musicType);
				if (isAdded) {
					Helper.showMessage("selectedAddPlaylist");
					updatePopPage();
					updateClassicalPage();
					updateJazzPage();
				} else {
					Helper.showMessage("alreadyAddToPlaylist");
				}
			}
		});
		btnAddToPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddToPlaylist.setBounds(468, 420, 204, 55);
		panelMusic.add(btnAddToPlaylist);

		JPanel panelArtist = new JPanel();
		tabbedPane.addTab("Sanatçýlar", null, panelArtist, null);
		panelArtist.setLayout(null);

		JScrollPane signInScrollPane_1 = new JScrollPane();
		signInScrollPane_1.setBounds(10, 10, 877, 424);
		panelArtist.add(signInScrollPane_1);

		signInArtistTable = new JTable(artistModel);
		signInScrollPane_1.setViewportView(signInArtistTable);
		
		JButton btnFollowArtist = new JButton("Takip Et");
		btnFollowArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean follow = isFollowArtist(followArtistID, user.getMemberID());
				if (follow) {
					Helper.showMessage("followArtist");
					updateFollowedArtistPage();
				}
			}
		});
		btnFollowArtist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFollowArtist.setBounds(753, 444, 134, 30);
		panelArtist.add(btnFollowArtist);

		JPanel panelAlbum = new JPanel();
		tabbedPane.addTab("Albümler", null, panelAlbum, null);
		panelAlbum.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 10, 877, 464);
		panelAlbum.add(scrollPane_2);

		signInAlbumTable = new JTable(albumModel);
		scrollPane_2.setViewportView(signInAlbumTable);

		JPanel panelUsers = new JPanel();
		tabbedPane.addTab("Kullanýcýlar", null, panelUsers, null);
		panelUsers.setLayout(null);

		JScrollPane signInScrollPane_3 = new JScrollPane();
		signInScrollPane_3.setBounds(10, 10, 877, 413);
		panelUsers.add(signInScrollPane_3);

		signInUsersTable = new JTable(userModel);
		signInScrollPane_3.setViewportView(signInUsersTable);
		signInUsersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					followID = (int) signInUsersTable.getValueAt(signInUsersTable.getSelectedRow(), 0);
				} catch (Exception e2) {
				}
			}
		});

		JButton btnFollow = new JButton("Takip Et");
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean follow = isFollow(followID, user.getMemberID());
				if (follow) {
					Helper.showMessage("followSuccess");
					updateFollowedPage();
				}
			}
		});
		btnFollow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFollow.setBounds(736, 433, 131, 41);
		panelUsers.add(btnFollow);

		JButton btnViewPlaylist = new JButton("Çalma Listelerini Görüntüle");
		btnViewPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				followControl(followID);
			}
		});
		btnViewPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewPlaylist.setBounds(20, 433, 312, 41);
		panelUsers.add(btnViewPlaylist);

		JPanel panelFollow = new JPanel();
		tabbedPane.addTab("Takip", null, panelFollow, null);
		panelFollow.setLayout(null);

		JScrollPane followedScrollPane = new JScrollPane();
		followedScrollPane.setBounds(10, 72, 425, 372);
		panelFollow.add(followedScrollPane);

		followedTable = new JTable(followedModel);
		followedScrollPane.setViewportView(followedTable);
		followedTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					unfollowID = (int) followedTable.getValueAt(followedTable.getSelectedRow(), 0);
				} catch (Exception e2) {
				}
			}
		});

		JScrollPane followerScrollPane = new JScrollPane();
		followerScrollPane.setBounds(462, 72, 425, 372);
		panelFollow.add(followerScrollPane);

		followerTable = new JTable(followerModel);
		followerScrollPane.setViewportView(followerTable);

		JLabel lblFollowed = new JLabel("Takip Ettikleriniz");
		lblFollowed.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblFollowed.setBounds(135, 20, 164, 42);
		panelFollow.add(lblFollowed);

		JLabel lblFollower = new JLabel("Takipçileriniz");
		lblFollower.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblFollower.setBounds(594, 20, 125, 42);
		panelFollow.add(lblFollower);
		
		JButton btnUnfollow = new JButton("Takibi Býrak");
		btnUnfollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unfollow(unfollowID, LogInGUI.user.getMemberID());
				updateFollowedPage();
				Helper.showMessage("unfollowed");
			}
		});
		btnUnfollow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUnfollow.setBounds(10, 450, 104, 25);
		panelFollow.add(btnUnfollow);
		
		JPanel panelPlaylist = new JPanel();
		tabbedPane.addTab("Çalma Listelerin", null, panelPlaylist, null);
		panelPlaylist.setLayout(null);

		JTabbedPane playlistTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		playlistTabbedPane.setBounds(10, 10, 877, 464);
		panelPlaylist.add(playlistTabbedPane);

		JPanel panelPop = new JPanel();
		playlistTabbedPane.addTab("POP", null, panelPop, null);
		panelPop.setLayout(null);

		JScrollPane popScrollPane = new JScrollPane();
		popScrollPane.setBounds(10, 10, 852, 417);
		panelPop.add(popScrollPane);

		popTable = new JTable(popModel);
		popScrollPane.setViewportView(popTable);

		JPanel panelClassical = new JPanel();
		playlistTabbedPane.addTab("KLASÝK", null, panelClassical, null);
		panelClassical.setLayout(null);

		JScrollPane ClassicalScrollPane = new JScrollPane();
		ClassicalScrollPane.setBounds(10, 10, 852, 417);
		panelClassical.add(ClassicalScrollPane);

		classicalTable = new JTable(classicalModel);
		ClassicalScrollPane.setViewportView(classicalTable);

		JPanel panelJazz = new JPanel();
		playlistTabbedPane.addTab("JAZZ", null, panelJazz, null);
		panelJazz.setLayout(null);

		JScrollPane JazzScrollPane = new JScrollPane();
		JazzScrollPane.setBounds(10, 10, 852, 417);
		panelJazz.add(JazzScrollPane);

		jazzTable = new JTable(jazzModel);
		JazzScrollPane.setViewportView(jazzTable);

		JPanel panelTop10 = new JPanel();
		tabbedPane.addTab("Top 10", null, panelTop10, null);
		panelTop10.setLayout(null);

		JTabbedPane tabbedPaneTop10 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneTop10.setBounds(10, 10, 877, 464);
		panelTop10.add(tabbedPaneTop10);

		JPanel panelPopTop10 = new JPanel();
		tabbedPaneTop10.addTab("Pop Top 10", null, panelPopTop10, null);
		panelPopTop10.setLayout(null);

		JScrollPane scrollPanePopTop10 = new JScrollPane();
		scrollPanePopTop10.setBounds(10, 10, 852, 417);
		panelPopTop10.add(scrollPanePopTop10);

		popTop10Table = new JTable(top10PopModel);
		scrollPanePopTop10.setViewportView(popTop10Table);

		JPanel panelClassicalTop10 = new JPanel();
		tabbedPaneTop10.addTab("Klasik Top 10", null, panelClassicalTop10, null);
		panelClassicalTop10.setLayout(null);

		JScrollPane scrollPaneClassicalTop10 = new JScrollPane();
		scrollPaneClassicalTop10.setBounds(10, 10, 852, 399);
		panelClassicalTop10.add(scrollPaneClassicalTop10);

		classicalTop10Table = new JTable(top10ClassicalModel);
		scrollPaneClassicalTop10.setViewportView(classicalTop10Table);

		JPanel panelJazzTop10 = new JPanel();
		tabbedPaneTop10.addTab("Jazz Top 10", null, panelJazzTop10, null);
		panelJazzTop10.setLayout(null);

		JScrollPane scrollPaneJazzTop10 = new JScrollPane();
		scrollPaneJazzTop10.setBounds(10, 10, 852, 399);
		panelJazzTop10.add(scrollPaneJazzTop10);

		jazzTop10Table = new JTable(top10JazzModel);
		scrollPaneJazzTop10.setViewportView(jazzTop10Table);

		JPanel panelGeneralTop10 = new JPanel();
		tabbedPaneTop10.addTab("Top 10", null, panelGeneralTop10, null);
		panelGeneralTop10.setLayout(null);

		JScrollPane scrollPaneGeneralTop10 = new JScrollPane();
		scrollPaneGeneralTop10.setBounds(10, 10, 852, 399);
		panelGeneralTop10.add(scrollPaneGeneralTop10);

		generalTop10Table = new JTable(top10GeneralModel);
		scrollPaneGeneralTop10.setViewportView(generalTop10Table);

		JPanel panelCountryTop10 = new JPanel();
		tabbedPaneTop10.addTab("Ülkeler Top 10", null, panelCountryTop10, null);
		panelCountryTop10.setLayout(null);

		JScrollPane scrollPaneCountryTop10 = new JScrollPane();
		scrollPaneCountryTop10.setBounds(10, 10, 852, 351);
		panelCountryTop10.add(scrollPaneCountryTop10);

		
		countryTop10Table = new JTable(top10CountryModel);
		scrollPaneCountryTop10.setViewportView(countryTop10Table);
		countryTop10Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					country = (String) countryTop10Table.getValueAt(countryTop10Table.getSelectedRow(), 0);
				} catch (Exception e2) {
				}
			}
		});

		JButton btnViewCountryTop10 = new JButton("Ülkenin Top 10 Listesini Görüntüle");
		btnViewCountryTop10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CountryGUI countryGUI = new CountryGUI(country);
				countryGUI.setVisible(true);
				dispose();
			}
		});
		btnViewCountryTop10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewCountryTop10.setBounds(287, 371, 276, 56);
		panelCountryTop10.add(btnViewCountryTop10);
		
		JPanel panelFollowArtist = new JPanel();
		tabbedPane.addTab("Takip Edilen Sanatçýlar", null, panelFollowArtist, null);
		panelFollowArtist.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 877, 373);
		panelFollowArtist.add(scrollPane);
		
		followArtistTable = new JTable(followArtistModel);
		scrollPane.setViewportView(followArtistTable);
		
		followArtistTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					unfollowArtistID = (int) followArtistTable.getValueAt(followArtistTable.getSelectedRow(), 0);
				} catch (Exception e2) {
				}
			}
		});
		
		signInArtistTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					followArtistID = (int) signInArtistTable.getValueAt(signInArtistTable.getSelectedRow(), 0);
				} catch (Exception e2) {
				}
			}
		});
		JLabel lblNewLabel = new JLabel("Takip Edilen Sanatçýlar");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 396, 41);
		panelFollowArtist.add(lblNewLabel);
		
		JButton btnUnfollowArtist = new JButton("Takibi Býrak");
		btnUnfollowArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unfollowArtist(unfollowArtistID, LogInGUI.user.getMemberID());
				updateFollowedArtistPage();
				Helper.showMessage("unfollowArtist");
			}
		});
		btnUnfollowArtist.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUnfollowArtist.setBounds(10, 444, 129, 30);
		panelFollowArtist.add(btnUnfollowArtist);

		JButton btnSignOutLogIn = new JButton("Çýkýþ Yap");
		btnSignOutLogIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSignOutLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInGUI logInGUI = new LogInGUI();
				logInGUI.setVisible(true);
				dispose();
			}
		});
		btnSignOutLogIn.setBounds(814, 10, 98, 30);
		signInContentPane.add(btnSignOutLogIn);

	}

	public void followControl(int followID) {
		a = 0;
		String quString = "select * from follow where follow.follow_id = " + followID + " and user_id = "
				+ LogInGUI.user.getMemberID();
		try {
			con = connectionHelper.connectionHelper();
			statement = (Statement) con.createStatement();
			ResultSet rSet = statement.executeQuery(quString);
			while (rSet.next()) {
				a = 1;
			}
			if (a == 0) {
				Helper.showMessage("needFollow");
			} else {
				PremiumPlaylistGUI premiumPlaylist = new PremiumPlaylistGUI(followID);
				premiumPlaylist.setVisible(true);
				dispose();
			}
			con.close();
			statement.close();
			rSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createPopPage() {
		for (int i = 0; i < playlist.getPopList(LogInGUI.user.getMemberID()).size(); i++) {
			popObjects[0] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicID();
			popObjects[1] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicName();
			popObjects[2] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicDate();
			popObjects[3] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicArtist();
			popObjects[4] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicAlbum();
			popObjects[5] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicType();
			popObjects[6] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicTime();
			popObjects[7] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicPlay();
			popObjects[8] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicCountry();
			popModel.addRow(popObjects);
		}
	}

	public void createClassicalPage() {
		for (int i = 0; i < playlist.getClassicalList(LogInGUI.user.getMemberID()).size(); i++) {
			classicalObjects[0] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicID();
			classicalObjects[1] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicName();
			classicalObjects[2] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicDate();
			classicalObjects[3] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicArtist();
			classicalObjects[4] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicAlbum();
			classicalObjects[5] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicType();
			classicalObjects[6] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicTime();
			classicalObjects[7] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicPlay();
			classicalObjects[8] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicCountry();
			classicalModel.addRow(classicalObjects);
		}
	}

	public void createJazzPage() {
		for (int i = 0; i < playlist.getJazzList(LogInGUI.user.getMemberID()).size(); i++) {
			jazzObjects[0] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicID();
			jazzObjects[1] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicName();
			jazzObjects[2] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicDate();
			jazzObjects[3] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicArtist();
			jazzObjects[4] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicAlbum();
			jazzObjects[5] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicType();
			jazzObjects[6] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicTime();
			jazzObjects[7] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicPlay();
			jazzObjects[8] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicCountry();
			jazzModel.addRow(jazzObjects);
		}
	}
	
	public boolean isFollowArtist(int artistFollowedID, int userID) {
		int sign = 0;
		b = 0;
		String queryString = "insert into artist_follow (artist_id, user_id) values (?,?)";
		try {
			con = connectionHelper.connectionHelper();
			statement = (Statement) con.createStatement();
			result = statement.executeQuery("select * from artist_follow where artist_follow.artist_id = " + artistFollowedID
					+ " and artist_follow.user_id = " + LogInGUI.user.getMemberID());
			while (result.next()) {
				b = 1;
			}
			if (b == 0) {
				prep = (PreparedStatement) con.prepareStatement(queryString);
				prep.setInt(1, artistFollowedID);
				prep.setInt(2, userID);
				prep.executeUpdate();
				sign = 1;
			} else if(b != 0) {
				Helper.showMessage("alreadyFollowedArtist");
			}
			con.close();
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (sign == 1)
			return true;
		else
			return false;
	}

	public boolean isFollow(int followedID, int userID) {
		int sign = 0;
		b = 0;
		String queryString = "insert into follow (follow_id, user_id) values (?,?)";
		try {
			con = connectionHelper.connectionHelper();
			statement = (Statement) con.createStatement();
			result = statement.executeQuery("select * from follow where follow.follow_id = " + followedID
					+ " and follow.user_id = " + LogInGUI.user.getMemberID());
			while (result.next()) {
				b = 1;
			}
			if (b == 0 && (followedID != userID)) {
				prep = (PreparedStatement) con.prepareStatement(queryString);
				result = statement.executeQuery("select * from user where user_id = " + followedID);
				while (result.next()) {
					inf = result.getString("user_premium");
				}
				if (inf.equals("y")) {
					prep.setInt(1, followedID);
					prep.setInt(2, userID);
					prep.executeUpdate();
					sign = 1;
					prep.close();
				} else {
					Helper.showMessage("premium");
				}
			} else if(b != 0 && (followedID != userID)) {
				Helper.showMessage("alreadyFollowed");
			}
			con.close();
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(followedID == userID) {
			sign = 0;
			Helper.showMessage("followYourself");
		}
		if (sign == 1)
			return true;
		else
			return false;
	}
	
	public void unfollow(int unfollowedID, int userID) {
		try {
			con = connectionHelper.connectionHelper();
	        prep = (PreparedStatement) con.prepareStatement("delete from follow where follow_id = " + unfollowedID + " and user_id = " + userID);
	        prep.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void unfollowArtist(int unfollowedArtistID, int userID) {
		try {
			con = connectionHelper.connectionHelper();
	        prep = (PreparedStatement) con.prepareStatement("delete from artist_follow where artist_id = " + unfollowedArtistID + " and user_id = " + userID);
	        prep.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createUserPage() {
		userModel = new DefaultTableModel();
		Object[] adminColName = new Object[5];
		adminColName[0] = "ID";
		adminColName[1] = "Ýsim";
		adminColName[2] = "Email";
		adminColName[3] = "Premium";
		adminColName[4] = "Ülke";
		;
		userModel.setColumnIdentifiers(adminColName);
		userObjects = new Object[5];

		for (int i = 0; i < LogInGUI.user.getUserList().size(); i++) {
			userObjects[0] = LogInGUI.user.getUserList().get(i).getMemberID();
			userObjects[1] = LogInGUI.user.getUserList().get(i).getMemberName();
			userObjects[2] = LogInGUI.user.getUserList().get(i).getMemberEmail();
			userObjects[3] = LogInGUI.user.getUserList().get(i).getPremium();
			userObjects[4] = LogInGUI.user.getUserList().get(i).getMemberCountry();
			userModel.addRow(userObjects);
		}
	}

	public void createFollowedPage() {
		followedModel = new DefaultTableModel();
		Object[] adminColName = new Object[5];
		adminColName[0] = "ID";
		adminColName[1] = "Ýsim";
		adminColName[2] = "Email";
		adminColName[3] = "Premium";
		adminColName[4] = "Ülke";
		followedModel.setColumnIdentifiers(adminColName);
		followedObjects = new Object[5];

		for (int i = 0; i < LogInGUI.user.getFollowedList().size(); i++) {
			followedObjects[0] = LogInGUI.user.getFollowedList().get(i).getMemberID();
			followedObjects[1] = LogInGUI.user.getFollowedList().get(i).getMemberName();
			followedObjects[2] = LogInGUI.user.getFollowedList().get(i).getMemberEmail();
			followedObjects[3] = LogInGUI.user.getFollowedList().get(i).getPremium();
			followedObjects[4] = LogInGUI.user.getFollowedList().get(i).getMemberCountry();
			followedModel.addRow(followedObjects);
		}
	}
	
	public void createFollowArtistPage() {
		followArtistModel = new DefaultTableModel();
		Object[] adminColName = new Object[3];
		adminColName[0] = "ID";
		adminColName[1] = "Ýsim";
		adminColName[2] = "Ülke";
		followArtistModel.setColumnIdentifiers(adminColName);
		followArtistObjects = new Object[3];

		for (int i = 0; i < artist.getFollowedArtistList().size(); i++) {
			followArtistObjects[0] = artist.getFollowedArtistList().get(i).getArtistID();
			followArtistObjects[1] = artist.getFollowedArtistList().get(i).getArtistName();
			followArtistObjects[2] = artist.getFollowedArtistList().get(i).getArtistCountry();
			followArtistModel.addRow(followArtistObjects);
		}
	}

	public void createFollowerPage() {
		followerModel = new DefaultTableModel();
		Object[] adminColName = new Object[5];
		adminColName[0] = "ID";
		adminColName[1] = "Ýsim";
		adminColName[2] = "Email";
		adminColName[3] = "Premium";
		adminColName[4] = "Ülke";

		followerModel.setColumnIdentifiers(adminColName);
		followerObjects = new Object[5];

		for (int i = 0; i < LogInGUI.user.getFollowerList().size(); i++) {
			followerObjects[0] = LogInGUI.user.getFollowerList().get(i).getMemberID();
			followerObjects[1] = LogInGUI.user.getFollowerList().get(i).getMemberName();
			followerObjects[2] = LogInGUI.user.getFollowerList().get(i).getMemberEmail();
			followerObjects[3] = LogInGUI.user.getFollowerList().get(i).getPremium();
			followerObjects[4] = LogInGUI.user.getFollowerList().get(i).getMemberCountry();
			followerModel.addRow(followerObjects);
		}
	}

	public void createArtistPage() {
		artistModel = new DefaultTableModel();
		Object[] adminColNameArtist = new Object[3];
		adminColNameArtist[0] = "ID";
		adminColNameArtist[1] = "Ýsim";
		adminColNameArtist[2] = "Ülke";
		artistModel.setColumnIdentifiers(adminColNameArtist);
		artistObjects = new Object[3];

		for (int i = 0; i < artist.getArtistList().size(); i++) {
			artistObjects[0] = artist.getArtistList().get(i).getArtistID();
			artistObjects[1] = artist.getArtistList().get(i).getArtistName();
			artistObjects[2] = artist.getArtistList().get(i).getArtistCountry();
			artistModel.addRow(artistObjects);
		}
	}

	public void createAlbumPage() {
		albumModel = new DefaultTableModel();
		Object[] adminColNameAlbum = new Object[7];
		adminColNameAlbum[0] = "ID";
		adminColNameAlbum[1] = "Ýsim";
		adminColNameAlbum[2] = "Sanatçý";
		adminColNameAlbum[3] = "Þarký";
		adminColNameAlbum[4] = "Çýkýþ Tarihi";
		adminColNameAlbum[5] = "Tür";
		adminColNameAlbum[6] = "Ülke";
		albumModel.setColumnIdentifiers(adminColNameAlbum);
		albumObjects = new Object[7];

		for (int i = 0; i < album.getAlbumList().size(); i++) {
			albumObjects[0] = album.getAlbumList().get(i).getAlbumID();
			albumObjects[1] = album.getAlbumList().get(i).getAlbumName();
			albumObjects[2] = album.getAlbumList().get(i).getAlbumArtist();
			albumObjects[3] = album.getAlbumList().get(i).getAlbumMusic();
			albumObjects[4] = album.getAlbumList().get(i).getAlbumDate();
			albumObjects[5] = album.getAlbumList().get(i).getAlbumType();
			albumObjects[6] = album.getAlbumList().get(i).getAlbumCountry();
			albumModel.addRow(albumObjects);
		}
	}

	public void createMusicPage() {
		musicModel = new DefaultTableModel();
		popModel = new DefaultTableModel();
		classicalModel = new DefaultTableModel();
		jazzModel = new DefaultTableModel();
		top10PopModel = new DefaultTableModel();
		top10ClassicalModel = new DefaultTableModel();
		top10JazzModel = new DefaultTableModel();
		top10GeneralModel = new DefaultTableModel();
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
		popModel.setColumnIdentifiers(adminColName);
		popObjects = new Object[9];
		classicalModel.setColumnIdentifiers(adminColName);
		classicalObjects = new Object[9];
		jazzModel.setColumnIdentifiers(adminColName);
		jazzObjects = new Object[9];
		top10PopModel.setColumnIdentifiers(adminColName);
		top10PopObjects = new Object[9];
		top10ClassicalModel.setColumnIdentifiers(adminColName);
		top10ClassicalObjects = new Object[9];
		top10JazzModel.setColumnIdentifiers(adminColName);
		top10JazzObjects = new Object[9];
		top10GeneralModel.setColumnIdentifiers(adminColName);
		top10GeneralObjects = new Object[9];

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

	@SuppressWarnings("unchecked")
	public void top10PopPage() {
		ArrayList<Music> popArrayList = music.getMusicList("pop");
		Collections.sort(popArrayList);
		listSize = popArrayList.size();
		if (popArrayList.size() >= 10) {
			listSize = 10;
		}
		for (int i = 0; i < listSize; i++) {
			top10PopObjects[0] = popArrayList.get(i).getMusicID();
			top10PopObjects[1] = popArrayList.get(i).getMusicName();
			top10PopObjects[2] = popArrayList.get(i).getMusicDate();
			top10PopObjects[3] = popArrayList.get(i).getMusicArtist();
			top10PopObjects[4] = popArrayList.get(i).getMusicAlbum();
			top10PopObjects[5] = popArrayList.get(i).getMusicType();
			top10PopObjects[6] = popArrayList.get(i).getMusicTime();
			top10PopObjects[7] = popArrayList.get(i).getMusicPlay();
			top10PopObjects[8] = popArrayList.get(i).getMusicCountry();
			top10PopModel.addRow(top10PopObjects);
		}
	}

	@SuppressWarnings("unchecked")
	public void top10ClassicalPage() {
		ArrayList<Music> classicArrayList = music.getMusicList("klasik");
		Collections.sort(classicArrayList);
		listSize = classicArrayList.size();
		if (classicArrayList.size() >= 10) {
			listSize = 10;
		}
		for (int i = 0; i < listSize; i++) {
			top10ClassicalObjects[0] = classicArrayList.get(i).getMusicID();
			top10ClassicalObjects[1] = classicArrayList.get(i).getMusicName();
			top10ClassicalObjects[2] = classicArrayList.get(i).getMusicDate();
			top10ClassicalObjects[3] = classicArrayList.get(i).getMusicArtist();
			top10ClassicalObjects[4] = classicArrayList.get(i).getMusicAlbum();
			top10ClassicalObjects[5] = classicArrayList.get(i).getMusicType();
			top10ClassicalObjects[6] = classicArrayList.get(i).getMusicTime();
			top10ClassicalObjects[7] = classicArrayList.get(i).getMusicPlay();
			top10ClassicalObjects[8] = classicArrayList.get(i).getMusicCountry();
			top10ClassicalModel.addRow(top10ClassicalObjects);
		}
	}

	@SuppressWarnings("unchecked")
	public void top10JazzPage() {
		ArrayList<Music> jazzArrayList = music.getMusicList("jazz");
		Collections.sort(jazzArrayList);
		listSize = jazzArrayList.size();
		if (jazzArrayList.size() >= 10) {
			listSize = 10;
		}
			for (int i = 0; i < listSize; i++) {
				top10JazzObjects[0] = jazzArrayList.get(i).getMusicID();
				top10JazzObjects[1] = jazzArrayList.get(i).getMusicName();
				top10JazzObjects[2] = jazzArrayList.get(i).getMusicDate();
				top10JazzObjects[3] = jazzArrayList.get(i).getMusicArtist();
				top10JazzObjects[4] = jazzArrayList.get(i).getMusicAlbum();
				top10JazzObjects[5] = jazzArrayList.get(i).getMusicType();
				top10JazzObjects[6] = jazzArrayList.get(i).getMusicTime();
				top10JazzObjects[7] = jazzArrayList.get(i).getMusicPlay();
				top10JazzObjects[8] = jazzArrayList.get(i).getMusicCountry();
				top10JazzModel.addRow(top10JazzObjects);
			}
	}

	@SuppressWarnings("unchecked")
	public void top10GeneralPage() {
		ArrayList<Music> generalArrayList = music.getMusicList("music");
		Collections.sort(generalArrayList);
		listSize = generalArrayList.size();
		if (generalArrayList.size() >= 10) {
			listSize = 10;
		}
			for (int i = 0; i < listSize; i++) {
				top10GeneralObjects[0] = generalArrayList.get(i).getMusicID();
				top10GeneralObjects[1] = generalArrayList.get(i).getMusicName();
				top10GeneralObjects[2] = generalArrayList.get(i).getMusicDate();
				top10GeneralObjects[3] = generalArrayList.get(i).getMusicArtist();
				top10GeneralObjects[4] = generalArrayList.get(i).getMusicAlbum();
				top10GeneralObjects[5] = generalArrayList.get(i).getMusicType();
				top10GeneralObjects[6] = generalArrayList.get(i).getMusicTime();
				top10GeneralObjects[7] = generalArrayList.get(i).getMusicPlay();
				top10GeneralObjects[8] = generalArrayList.get(i).getMusicCountry();
				top10GeneralModel.addRow(top10GeneralObjects);
			}
	}

	public void top10CountryPage() {
		top10CountryModel = new DefaultTableModel();
		Object[] adminColName = new Object[1];
		adminColName[0] = "ÜLKELER";
		top10CountryModel.setColumnIdentifiers(adminColName);
		top10CountryObjects = new Object[1];
		ArrayList<Music> arrayList = new ArrayList<>();
		arrayList = music.getMusicList("music");
		ArrayList<String> countryArrayList = music.removeDuplicates(arrayList);
		for (int i = 0; i < countryArrayList.size(); i++) {
			top10CountryObjects[0] = countryArrayList.get(i);
			top10CountryModel.addRow(top10CountryObjects);
		}
	}
	
	public void updateFollowedArtistPage() {
		DefaultTableModel updateTable = (DefaultTableModel) followArtistTable.getModel();
		updateTable.setRowCount(0);
		for (int i = 0; i < artist.getFollowedArtistList().size(); i++) {
			followArtistObjects[0] = artist.getFollowedArtistList().get(i).getArtistID();
			followArtistObjects[1] = artist.getFollowedArtistList().get(i).getArtistName();
			followArtistObjects[2] = artist.getFollowedArtistList().get(i).getArtistCountry();
			followArtistModel.addRow(followArtistObjects);
		}
	}

	public void updateFollowedPage() {
		DefaultTableModel updateTable = (DefaultTableModel) followedTable.getModel();
		updateTable.setRowCount(0);
		for (int i = 0; i < LogInGUI.user.getFollowedList().size(); i++) {
			followedObjects[0] = LogInGUI.user.getFollowedList().get(i).getMemberID();
			followedObjects[1] = LogInGUI.user.getFollowedList().get(i).getMemberName();
			followedObjects[2] = LogInGUI.user.getFollowedList().get(i).getMemberEmail();
			followedObjects[3] = LogInGUI.user.getFollowedList().get(i).getPremium();
			followedObjects[4] = LogInGUI.user.getFollowedList().get(i).getMemberCountry();
			followedModel.addRow(followedObjects);
		}
	}

	public void updateMusicPage() {
		DefaultTableModel updateTable = (DefaultTableModel) signInMusicTable.getModel();
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

	public void updatePopPage() {
		DefaultTableModel updateTable = (DefaultTableModel) popTable.getModel();
		updateTable.setRowCount(0);
		for (int i = 0; i < playlist.getPopList(LogInGUI.user.getMemberID()).size(); i++) {
			popObjects[0] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicID();
			popObjects[1] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicName();
			popObjects[2] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicDate();
			popObjects[3] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicArtist();
			popObjects[4] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicAlbum();
			popObjects[5] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicType();
			popObjects[6] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicTime();
			popObjects[7] = playlist.getPopList(LogInGUI.user.getMemberID()).get(i).getMusicPlay();
			popModel.addRow(popObjects);
		}
	}

	public void updateClassicalPage() {
		DefaultTableModel updateTable = (DefaultTableModel) classicalTable.getModel();
		updateTable.setRowCount(0);
		for (int i = 0; i < playlist.getClassicalList(LogInGUI.user.getMemberID()).size(); i++) {
			classicalObjects[0] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicID();
			classicalObjects[1] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicName();
			classicalObjects[2] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicDate();
			classicalObjects[3] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicArtist();
			classicalObjects[4] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicAlbum();
			classicalObjects[5] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicType();
			classicalObjects[6] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicTime();
			classicalObjects[7] = playlist.getClassicalList(LogInGUI.user.getMemberID()).get(i).getMusicPlay();
			classicalModel.addRow(classicalObjects);
		}
	}

	public void updateJazzPage() {
		DefaultTableModel updateTable = (DefaultTableModel) jazzTable.getModel();
		updateTable.setRowCount(0);
		for (int i = 0; i < playlist.getJazzList(LogInGUI.user.getMemberID()).size(); i++) {
			jazzObjects[0] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicID();
			jazzObjects[1] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicName();
			jazzObjects[2] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicDate();
			jazzObjects[3] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicArtist();
			jazzObjects[4] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicAlbum();
			jazzObjects[5] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicType();
			jazzObjects[6] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicTime();
			jazzObjects[7] = playlist.getJazzList(LogInGUI.user.getMemberID()).get(i).getMusicPlay();
			jazzModel.addRow(jazzObjects);
		}
	}
}
