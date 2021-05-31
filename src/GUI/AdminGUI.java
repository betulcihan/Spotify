package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import Help.*;
import System.Admin;
import System.Album;
import System.Artist;
import System.Music;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;
import java.awt.Color;

public class AdminGUI extends JFrame {

	private JPanel adminContentPane;
	private Music music = new Music();
	private Artist artist = new Artist();
	private Album album = new Album();
	private DefaultTableModel musicModel = null;
	private DefaultTableModel artistModel = null;
	private DefaultTableModel albumModel = null;
	private Object[] musicObjects = null;
	private Object[] albumObjects = null;
	private Object[] artistObjects = null;
	private JTextField deleteMusicIDField;
	private JTable adminMusicTable;
	private JTable adminArtistTable;
	private JTable adminAlbumTable;
	int idMusic, idArtist, idAlbum;
	String nameAlbum, typeAlbum, musicName, musicArtist, musicAlbum, musicType;
	boolean success;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI(LogInGUI.admin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminGUI(Admin admin) {
		createMusicPage();
		createAlbumPage();
		createArtistPage();

		setTitle("MyDB for Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1169, 677);
		adminContentPane = new JPanel();
		adminContentPane.setBackground(new Color(176, 196, 222));
		adminContentPane.setForeground(new Color(0, 0, 0));
		adminContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(adminContentPane);
		adminContentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Hoþgeldin " + LogInGUI.admin.getMemberName());
		lblWelcome.setBounds(33, 25, 599, 56);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 21));
		adminContentPane.add(lblWelcome);

		JButton btnAdminSignOut = new JButton("Çýkýþ Yap");
		btnAdminSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInGUI logInGUI = new LogInGUI();
				logInGUI.setVisible(true);
				dispose();
			}
		});
		btnAdminSignOut.setBounds(1029, 10, 116, 35);
		adminContentPane.add(btnAdminSignOut);

		JButton btnAddMusic = new JButton("Þarký Ekleme");
		btnAddMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusicGUI musicGUI = new MusicGUI();
				musicGUI.setVisible(true);
				dispose();
			}
		});
		btnAddMusic.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddMusic.setBounds(940, 233, 158, 35);
		adminContentPane.add(btnAddMusic);

		deleteMusicIDField = new JTextField();
		deleteMusicIDField.setBounds(981, 486, 84, 34);
		adminContentPane.add(deleteMusicIDField);
		deleteMusicIDField.setColumns(10);

		JLabel lblSelectedID = new JLabel("ID");
		lblSelectedID.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSelectedID.setBounds(1008, 447, 43, 29);
		adminContentPane.add(lblSelectedID);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(32, 101, 865, 490);
		adminContentPane.add(tabbedPane);

		JPanel musicPanel = new JPanel();
		tabbedPane.addTab("Þarkýlar", null, musicPanel, null);
		musicPanel.setLayout(null);

		JScrollPane adminScrollPane = new JScrollPane();
		adminScrollPane.setBounds(10, 10, 840, 402);
		musicPanel.add(adminScrollPane);

		adminMusicTable = new JTable(musicModel);
		adminScrollPane.setViewportView(adminMusicTable);

		JButton btnDeleteMusic = new JButton("Sil");
		btnDeleteMusic.setBounds(696, 422, 116, 40);
		musicPanel.add(btnDeleteMusic);
		
		btnDeleteMusic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		adminMusicTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					idMusic = (int) adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 0);
					musicName = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 1).toString();
					musicArtist = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 3).toString();
					musicAlbum = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 4).toString();
					musicType = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 5).toString();
					deleteMusicIDField
							.setText(adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				}
			}
		});
		
		btnDeleteMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deleteMusicIDField.getText().length() == 0) {
					Helper.showMessage("Lütfen silmek istediðiniz þarkýyý seçiniz.");
				} else {
					if (Helper.warning("deleteMusic")) {
						boolean deleted = LogInGUI.admin.deleteMusic(Integer.parseInt(deleteMusicIDField.getText()));
						if (deleted) {
							deleteMusicIDField.setText(null);
							LogInGUI.admin.deleteAlbum(musicName, musicArtist, musicAlbum, musicType);
							updateMusicPage();
							updateAlbumPage();
						}
					}
				}
			}
		});


		JPanel ArtistPanel = new JPanel();
		tabbedPane.addTab("Sanatçýlar", null, ArtistPanel, null);
		ArtistPanel.setLayout(null);

		JScrollPane adminScrollPane2 = new JScrollPane();
		adminScrollPane2.setBounds(10, 10, 840, 402);
		ArtistPanel.add(adminScrollPane2);

		adminArtistTable = new JTable(artistModel);
		adminScrollPane2.setViewportView(adminArtistTable);

		JButton btnDeleteArtist = new JButton("Sil");
		btnDeleteArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deleteMusicIDField.getText().length() == 0) {
					Helper.showMessage("Lütfen silmek istediðiniz þarkýyý seçiniz.");
				} else {
					if (Helper.warning("deleteArtist")) {
						boolean deleted = artist.deleteArtist(Integer.parseInt(deleteMusicIDField.getText()));
						if (deleted) {
							deleteMusicIDField.setText(null);
							updateArtistPage();
						}
					}
				}

			}
		});

		btnDeleteArtist.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteArtist.setBounds(696, 422, 116, 40);
		ArtistPanel.add(btnDeleteArtist);
		JPanel AlbumPanel = new JPanel();
		tabbedPane.addTab("Albümler", null, AlbumPanel, null);
		AlbumPanel.setLayout(null);
		adminArtistTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					idArtist = (int) adminArtistTable.getValueAt(adminArtistTable.getSelectedRow(), 0);
					deleteMusicIDField
							.setText(adminArtistTable.getValueAt(adminArtistTable.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 840, 402);
		AlbumPanel.add(scrollPane);

		adminAlbumTable = new JTable(albumModel);
		scrollPane.setViewportView(adminAlbumTable);

		adminAlbumTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					idAlbum = (int) adminAlbumTable.getValueAt(adminAlbumTable.getSelectedRow(), 0);
					nameAlbum = adminAlbumTable.getValueAt(adminAlbumTable.getSelectedRow(), 1).toString();
					typeAlbum = adminAlbumTable.getValueAt(adminAlbumTable.getSelectedRow(), 5).toString();
					deleteMusicIDField
							.setText(adminAlbumTable.getValueAt(adminAlbumTable.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				}
			}
		});

		JButton btnDeleteAlbum = new JButton("Seçili Satýrý Sil");
		btnDeleteAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deleteMusicIDField.getText().length() == 0) {
					Helper.showMessage("Lütfen silmek istediðiniz þarkýyý seçiniz.");
				} else {
					if (Helper.warning("deleteAlbum")) {
						album.deleteAlbum(Integer.parseInt(deleteMusicIDField.getText()));
						album.deleteMusic(nameAlbum, typeAlbum);
						deleteMusicIDField.setText(null);
						updateAlbumPage();
						updateMusicPage();
					}
				}
			}
		});
		btnDeleteAlbum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteAlbum.setBounds(659, 423, 180, 40);
		AlbumPanel.add(btnDeleteAlbum);

		JButton btnDeleteAllAlbum = new JButton("T\u00FCm Alb\u00FCm\u00FC Sil");
		btnDeleteAllAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAllAlbum(nameAlbum, typeAlbum);
				deleteMusicIDField.setText(null);
				updateAlbumPage();
			}
		});
		btnDeleteAllAlbum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteAllAlbum.setBounds(20, 423, 180, 40);
		AlbumPanel.add(btnDeleteAllAlbum);

		JButton btnAddMusicArtist = new JButton("Sanatçý Ekleme");
		btnAddMusicArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArtistGUI artistGUI = new ArtistGUI();
				artistGUI.setVisible(true);
				dispose();
			}
		});
		btnAddMusicArtist.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddMusicArtist.setBounds(940, 279, 158, 35);
		adminContentPane.add(btnAddMusicArtist);

		JButton btnAddAlbum = new JButton("Albüm Oluþtur");
		btnAddAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlbumGUI albumGUI = new AlbumGUI();
				albumGUI.setVisible(true);
				dispose();
			}
		});
		btnAddAlbum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddAlbum.setBounds(940, 324, 158, 35);
		adminContentPane.add(btnAddAlbum);

		adminMusicTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int musicID = (int) adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 0);
					String musicTime = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 6).toString();
					String musicPlay = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 7).toString();
					String musicName = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 1).toString();
					String musicDate = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 2).toString();
					String musicArtist = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 3).toString();
					String musicAlbum = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 4).toString();
					String musicType = adminMusicTable.getValueAt(adminMusicTable.getSelectedRow(), 5).toString();

					music.updateMusic(musicID, musicName, musicDate, musicArtist, musicAlbum, musicType,
							Integer.parseInt(musicTime), Integer.parseInt(musicPlay));
				}
			}
		});

		adminArtistTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					String artistID = adminArtistTable.getValueAt(adminArtistTable.getSelectedRow(), 0).toString();
					String artistName = adminArtistTable.getValueAt(adminArtistTable.getSelectedRow(), 1).toString();
					String artistCountry = adminArtistTable.getValueAt(adminArtistTable.getSelectedRow(), 2).toString();
					artist.updateArtist(Integer.parseInt(artistID), artistName, artistCountry);
				}
			}
		});
	}

	private void selectRows(JTable table, int start, int end) {
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setRowSelectionInterval(start, end - 1);
	}

	private ArrayList<Integer> getSelectedRowValues(JTable table) {
		ArrayList<Integer> values = new ArrayList<>();
		int[] vals = table.getSelectedRows();
		for (int i = 0; i < vals.length; i++) {
			for (int x = 0; x < 1; x++) {
				values.add((Integer) table.getValueAt(i, x));
			}
		}
		return values;
	}

	public void deleteAllAlbum(String nameAlbum, String typeAlbum) {
		selectRows(adminAlbumTable, 0, adminAlbumTable.getRowCount());
		ArrayList<Integer> values = getSelectedRowValues(adminAlbumTable);
		for (@SuppressWarnings("unused")
		int integer : values) {
			success = album.deleteAllAlbum(nameAlbum, typeAlbum);
		}
		if (success) {
			album.deleteMusic(nameAlbum, typeAlbum);
			updateMusicPage();
		}
		Helper.showMessage("deleteAllAlbum");
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
		Object[] adminColName = new Object[7];
		adminColName[0] = "ID";
		adminColName[1] = "Ýsim";
		adminColName[2] = "Sanatçý";
		adminColName[3] = "Þarký";
		adminColName[4] = "Çýkýþ Tarihi";
		adminColName[5] = "Tür";
		adminColName[6] = "Ülke";
		albumModel.setColumnIdentifiers(adminColName);
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
		DefaultTableModel updateTable = (DefaultTableModel) adminMusicTable.getModel();
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

	public void updateArtistPage() {
		DefaultTableModel updateTable = (DefaultTableModel) adminArtistTable.getModel();
		updateTable.setRowCount(0);
		for (int i = 0; i < artist.getArtistList().size(); i++) {
			artistObjects[0] = artist.getArtistList().get(i).getArtistID();
			artistObjects[1] = artist.getArtistList().get(i).getArtistName();
			artistObjects[2] = artist.getArtistList().get(i).getArtistCountry();
			artistModel.addRow(artistObjects);
		}
	}

	public void updateAlbumPage() {
		DefaultTableModel updateTable = (DefaultTableModel) adminAlbumTable.getModel();
		updateTable.setRowCount(0);
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
}
