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
import System.Playlist;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PremiumPlaylistGUI extends JFrame {
	Playlist playlist = new Playlist();
	SignInGUI signInGUI = new SignInGUI(LogInGUI.user);
	private DefaultTableModel popModel = null;
	private DefaultTableModel classicalModel = null;
	private DefaultTableModel jazzModel = null;
	private Object[] popObjects = null;
	private Object[] classicalObjects = null;
	private Object[] jazzObjects = null;
	private JPanel premiumPlaylistContentPane;
	private JTable premiumPopTable;
	private JTable premiumJazzTable;
	int selectedID;
	String selectedType;
	Statement statement = null;
	ResultSet result = null;
	ConnectionHelper connectionHelper = new ConnectionHelper();
	Connection con = null;
	PreparedStatement prep = null;
	private JTable premiumClassicalTable;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PremiumPlaylistGUI frame = new PremiumPlaylistGUI(SignInGUI.followID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PremiumPlaylistGUI(int followID) {
		createPopPage();
		createClassicalPage();
		createJazzPage();

		setTitle("Premium Playlist");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 649);
		premiumPlaylistContentPane = new JPanel();
		premiumPlaylistContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(premiumPlaylistContentPane);
		premiumPlaylistContentPane.setLayout(null);

		JTabbedPane premiumPlaylistTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		premiumPlaylistTabbedPane.setBounds(10, 20, 924, 582);
		premiumPlaylistContentPane.add(premiumPlaylistTabbedPane);

		JPanel premiumPopPanel = new JPanel();
		premiumPlaylistTabbedPane.addTab("POP", null, premiumPopPanel, null);
		premiumPopPanel.setLayout(null);

		JScrollPane premiumPopScrollPane = new JScrollPane();
		premiumPopScrollPane.setBounds(10, 10, 899, 466);
		premiumPopPanel.add(premiumPopScrollPane);

		premiumPopTable = new JTable(popModel);
		premiumPopScrollPane.setViewportView(premiumPopTable);

		JPanel premiumClassicalPanel = new JPanel();
		premiumPlaylistTabbedPane.addTab("KLASÝK", null, premiumClassicalPanel, null);
		premiumClassicalPanel.setLayout(null);

		JScrollPane premiumClassicalScrolPane = new JScrollPane();
		premiumClassicalScrolPane.setBounds(10, 10, 899, 461);
		premiumClassicalPanel.add(premiumClassicalScrolPane);

		premiumClassicalTable = new JTable(classicalModel);
		premiumClassicalScrolPane.setViewportView(premiumClassicalTable);

		JPanel premiumJazzPanel = new JPanel();
		premiumPlaylistTabbedPane.addTab("JAZZ", null, premiumJazzPanel, null);
		premiumJazzPanel.setLayout(null);

		JScrollPane premiumJazzScrollPane = new JScrollPane();
		premiumJazzScrollPane.setBounds(10, 10, 899, 462);
		premiumJazzPanel.add(premiumJazzScrollPane);

		premiumJazzTable = new JTable(jazzModel);
		premiumJazzScrollPane.setViewportView(premiumJazzTable);

		premiumPopTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					selectedID = (int) premiumPopTable.getValueAt(premiumPopTable.getSelectedRow(), 0);
					selectedType = (String) premiumPopTable.getValueAt(premiumPopTable.getSelectedRow(), 5);
				} catch (Exception e2) {
				}
			}
		});

		premiumClassicalTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					selectedID = (int) premiumClassicalTable.getValueAt(premiumClassicalTable.getSelectedRow(), 0);
					selectedType = (String) premiumClassicalTable.getValueAt(premiumClassicalTable.getSelectedRow(), 5);
				} catch (Exception e2) {
				}
			}
		});

		premiumJazzTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					selectedID = (int) premiumJazzTable.getValueAt(premiumJazzTable.getSelectedRow(), 0);
					selectedType = (String) premiumJazzTable.getValueAt(premiumJazzTable.getSelectedRow(), 5);
				} catch (Exception e2) {
				}
			}
		});

		JButton btnAddPlaylistPop = new JButton("Seçilen Þarkýyý Çalma Listesine Ekle");
		btnAddPlaylistPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b_pop = playlist.isAddPlaylist(selectedID, selectedType);
				if (b_pop) {
					Helper.showMessage("selectedAddPlaylist");
				} else {
					Helper.showMessage("alreadyAddToPlaylist");
				}
			}
		});

		JButton btnAddPlaylistClassical = new JButton("Seçilen Þarkýyý Çalma Listesine Ekle");
		btnAddPlaylistClassical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b_pop = playlist.isAddPlaylist(selectedID, selectedType);
				if (b_pop) {
					Helper.showMessage("selectedAddPlaylist");
				} else {
					Helper.showMessage("alreadyAddToPlaylist");
				}
			}
		});

		JButton btnAddPlaylistJazz = new JButton("Seçilen Þarkýyý Çalma Listesine Ekle");
		btnAddPlaylistJazz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b_pop = playlist.isAddPlaylist(selectedID, selectedType);
				if (b_pop) {
					Helper.showMessage("selectedAddPlaylist");
				} else {
					Helper.showMessage("alreadyAddToPlaylist");
				}
			}
		});

		btnAddPlaylistPop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddPlaylistPop.setBounds(264, 486, 335, 57);
		premiumPopPanel.add(btnAddPlaylistPop);

		JButton btnGoBackPop = new JButton("Geri Dön");
		btnGoBackPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInGUI signInGUI2 = new SignInGUI(LogInGUI.user);
				signInGUI2.setVisible(true);
				dispose();
			}
		});
		btnGoBackPop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGoBackPop.setBounds(30, 486, 206, 57);
		premiumPopPanel.add(btnGoBackPop);

		JButton btnAddAllPop = new JButton("Tümünü Ekle");
		btnAddAllPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAllToPopPlaylist();
			}
		});
		btnAddAllPop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddAllPop.setBounds(631, 486, 267, 57);
		premiumPopPanel.add(btnAddAllPop);

		btnAddPlaylistClassical.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddPlaylistClassical.setBounds(329, 488, 267, 57);
		premiumClassicalPanel.add(btnAddPlaylistClassical);

		JButton btnGoBackClassical = new JButton("Geri Dön");
		btnGoBackClassical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInGUI signInGUI2 = new SignInGUI(LogInGUI.user);
				signInGUI2.setVisible(true);
				dispose();
			}
		});
		btnGoBackClassical.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGoBackClassical.setBounds(20, 488, 267, 57);
		premiumClassicalPanel.add(btnGoBackClassical);

		JButton btnAddAllClassical = new JButton("Tümünü Ekle");
		btnAddAllClassical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAllToClassicalPlaylist();
			}
		});
		btnAddAllClassical.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddAllClassical.setBounds(625, 488, 267, 57);
		premiumClassicalPanel.add(btnAddAllClassical);

		btnAddPlaylistJazz.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddPlaylistJazz.setBounds(322, 488, 267, 57);
		premiumJazzPanel.add(btnAddPlaylistJazz);

		JButton btnGoBackJazz = new JButton("Geri Dön");
		btnGoBackJazz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInGUI signInGUI2 = new SignInGUI(LogInGUI.user);
				signInGUI2.setVisible(true);
				dispose();
			}
		});
		btnGoBackJazz.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGoBackJazz.setBounds(20, 488, 267, 57);
		premiumJazzPanel.add(btnGoBackJazz);

		JButton btnAddAllJazz = new JButton("Tümünü Ekle");
		btnAddAllJazz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAllToJazzPlaylist();
			}
		});
		btnAddAllJazz.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddAllJazz.setBounds(620, 488, 267, 57);
		premiumJazzPanel.add(btnAddAllJazz);

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

	public void addAllToPopPlaylist() {
		selectRows(premiumPopTable, 0, premiumPopTable.getRowCount());
		ArrayList<Integer> values = getSelectedRowValues(premiumPopTable);
		for (int integer : values) {
			boolean success = playlist.isAddPlaylist(integer, "pop");
		}
		Helper.showMessage("addAllToPlaylist");
	}
	
	public void addAllToClassicalPlaylist() {
		selectRows(premiumClassicalTable, 0, premiumClassicalTable.getRowCount());
		ArrayList<Integer> values = getSelectedRowValues(premiumClassicalTable);
		for (int integer : values) {
			boolean success = playlist.isAddPlaylist(integer, "klasik");
		}
		Helper.showMessage("addAllToPlaylist");
	}
	
	public void addAllToJazzPlaylist() {
		selectRows(premiumJazzTable, 0, premiumJazzTable.getRowCount());
		ArrayList<Integer> values = getSelectedRowValues(premiumJazzTable);
		for (int integer : values) {
			boolean success = playlist.isAddPlaylist(integer, "jazz");
		}
		Helper.showMessage("addAllToPlaylist");
	}

	public void createPopPage() {
		popModel = new DefaultTableModel();
		classicalModel = new DefaultTableModel();
		jazzModel = new DefaultTableModel();
		Object[] adminColName = new Object[8];
		adminColName[0] = "ID";
		adminColName[1] = "Ýsim";
		adminColName[2] = "Çýkýþ Tarihi";
		adminColName[3] = "Sanatçý";
		adminColName[4] = "Albüm";
		adminColName[5] = "Tür";
		adminColName[6] = "Süre";
		adminColName[7] = "Oynatma Sayýsý";
		popModel.setColumnIdentifiers(adminColName);
		popObjects = new Object[8];
		classicalModel.setColumnIdentifiers(adminColName);
		classicalObjects = new Object[8];
		jazzModel.setColumnIdentifiers(adminColName);
		jazzObjects = new Object[8];

		for (int i = 0; i < playlist.getPopList(SignInGUI.followID).size(); i++) {
			popObjects[0] = playlist.getPopList(SignInGUI.followID).get(i).getMusicID();
			popObjects[1] = playlist.getPopList(SignInGUI.followID).get(i).getMusicName();
			popObjects[2] = playlist.getPopList(SignInGUI.followID).get(i).getMusicDate();
			popObjects[3] = playlist.getPopList(SignInGUI.followID).get(i).getMusicArtist();
			popObjects[4] = playlist.getPopList(SignInGUI.followID).get(i).getMusicAlbum();
			popObjects[5] = playlist.getPopList(SignInGUI.followID).get(i).getMusicType();
			popObjects[6] = playlist.getPopList(SignInGUI.followID).get(i).getMusicTime();
			popObjects[7] = playlist.getPopList(SignInGUI.followID).get(i).getMusicPlay();
			popModel.addRow(popObjects);
		}
	}

	public void createClassicalPage() {
		for (int i = 0; i < playlist.getClassicalList(SignInGUI.followID).size(); i++) {
			classicalObjects[0] = playlist.getClassicalList(SignInGUI.followID).get(i).getMusicID();
			classicalObjects[1] = playlist.getClassicalList(SignInGUI.followID).get(i).getMusicName();
			classicalObjects[2] = playlist.getClassicalList(SignInGUI.followID).get(i).getMusicDate();
			classicalObjects[3] = playlist.getClassicalList(SignInGUI.followID).get(i).getMusicArtist();
			classicalObjects[4] = playlist.getClassicalList(SignInGUI.followID).get(i).getMusicAlbum();
			classicalObjects[5] = playlist.getClassicalList(SignInGUI.followID).get(i).getMusicType();
			classicalObjects[6] = playlist.getClassicalList(SignInGUI.followID).get(i).getMusicTime();
			classicalObjects[7] = playlist.getClassicalList(SignInGUI.followID).get(i).getMusicPlay();
			classicalModel.addRow(classicalObjects);
		}
	}

	public void createJazzPage() {
		for (int i = 0; i < playlist.getJazzList(SignInGUI.followID).size(); i++) {
			jazzObjects[0] = playlist.getJazzList(SignInGUI.followID).get(i).getMusicID();
			jazzObjects[1] = playlist.getJazzList(SignInGUI.followID).get(i).getMusicName();
			jazzObjects[2] = playlist.getJazzList(SignInGUI.followID).get(i).getMusicDate();
			jazzObjects[3] = playlist.getJazzList(SignInGUI.followID).get(i).getMusicArtist();
			jazzObjects[4] = playlist.getJazzList(SignInGUI.followID).get(i).getMusicAlbum();
			jazzObjects[5] = playlist.getJazzList(SignInGUI.followID).get(i).getMusicType();
			jazzObjects[6] = playlist.getJazzList(SignInGUI.followID).get(i).getMusicTime();
			jazzObjects[7] = playlist.getJazzList(SignInGUI.followID).get(i).getMusicPlay();
			jazzModel.addRow(jazzObjects);
		}
	}
}
