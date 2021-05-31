package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Help.*;
import System.Album;
import System.Artist;
import System.Music;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MusicGUI extends JFrame {
	private DefaultTableModel albumModel = null;
	private Object[] albumObjects = null;
	private JPanel MusicContentPane;
	private JTextField addMusicName;
	private JTextField addMusicDate;
	private JTextField addMusicArtist;
	private JTextField addMusicAlbum;
	private JTextField addMusicType;
	private JTextField addMusicTime;
	public Music music = new Music();
	public Album album = new Album();
	public Artist artist = new Artist();
	private JTextField addArtistCountry;
	Random random = new Random();
	int randomMusicPlay;
	private JTable addMusicAlbumTable;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicGUI frame = new MusicGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MusicGUI() {
		view();
		createAlbumPage();
		JButton btnAddMusicUpd = new JButton("Ekle");
		btnAddMusicUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 randomMusicPlay = random.nextInt(200000);
				if(addArtistCountry.getText().length() == 0 || addMusicName.getText().length() == 0 || addMusicDate.getText().length() == 0 || addMusicArtist.getText().length() == 0 || addMusicAlbum.getText().length() == 0  || 
						addMusicType.getText().length() == 0  || addMusicTime.getText().length() == 0) {
					Helper.showMessage("fillUp");
				}else{
					boolean control = LogInGUI.admin.addMusic(addArtistCountry.getText(), Integer.parseInt(addMusicAlbum.getText()),addMusicName.getText(), addMusicDate.getText(), addMusicArtist.getText(), 
							addMusicType.getText(), Integer.parseInt(addMusicTime.getText()), randomMusicPlay);
				if(control) {
					music.getMusicList("music");
					album.getAlbumList();
					artist.getArtistList();
					addMusicName.setText(null);
					addMusicDate.setText(null);
					addMusicArtist.setText(null);
					addMusicAlbum.setText(null);
					addMusicType.setText(null);
					addMusicTime.setText(null);
					addArtistCountry.setText(null);
				}
				AdminGUI adminGUI = new AdminGUI(LogInGUI.admin);
				adminGUI.setVisible(true);
				dispose();
				}
			}
		});
		btnAddMusicUpd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddMusicUpd.setBounds(501, 599, 169, 46);
		MusicContentPane.add(btnAddMusicUpd);
		
		JButton btnGoBack = new JButton("Geri Dön");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminGUI adminGUI2 = new AdminGUI(LogInGUI.admin);
				adminGUI2.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBounds(84, 599, 169, 46);
		MusicContentPane.add(btnGoBack);
		
		JLabel lblArtistCountry = new JLabel("Sanatçý Ülkesi:");
		lblArtistCountry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtistCountry.setBounds(110, 550, 169, 38);
		MusicContentPane.add(lblArtistCountry);
		
		addArtistCountry = new JTextField();
		addArtistCountry.setColumns(10);
		addArtistCountry.setBounds(318, 558, 257, 31);
		MusicContentPane.add(addArtistCountry);
		
		JScrollPane addMusicAlbumScrollPane = new JScrollPane();
		addMusicAlbumScrollPane.setBounds(44, 49, 643, 192);
		MusicContentPane.add(addMusicAlbumScrollPane);
		
		addMusicAlbumTable = new JTable(albumModel);
		addMusicAlbumScrollPane.setViewportView(addMusicAlbumTable);
		
		JLabel lblNewLabel_1 = new JLabel("Alb\u00FCm Bilgileri");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(44, 21, 209, 29);
		MusicContentPane.add(lblNewLabel_1);
	}
	
	public void view() {
		setTitle("Add Music");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 692);
		MusicContentPane = new JPanel();
		MusicContentPane.setBackground(new Color(176, 196, 222));
		MusicContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MusicContentPane);
		MusicContentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Þarký Adý: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(110, 261, 169, 38);
		MusicContentPane.add(lblNewLabel);
		
		addMusicName = new JTextField();
		addMusicName.setBounds(318, 269, 257, 31);
		MusicContentPane.add(addMusicName);
		addMusicName.setColumns(10);
		
		JLabel lblkTarihi = new JLabel("Çýkýþ Tarihi: ");
		lblkTarihi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblkTarihi.setBounds(110, 309, 169, 38);
		MusicContentPane.add(lblkTarihi);
		
		addMusicDate = new JTextField();
		addMusicDate.setColumns(10);
		addMusicDate.setBounds(318, 317, 257, 31);
		MusicContentPane.add(addMusicDate);
		
		JLabel lblSanat = new JLabel("Sanatçý: ");
		lblSanat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSanat.setBounds(110, 357, 169, 38);
		MusicContentPane.add(lblSanat);
		
		addMusicArtist = new JTextField();
		addMusicArtist.setColumns(10);
		addMusicArtist.setBounds(318, 365, 257, 31);
		MusicContentPane.add(addMusicArtist);
		
		JLabel lblAlbm = new JLabel("Alb\u00FCm ID: ");
		lblAlbm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAlbm.setBounds(110, 405, 169, 38);
		MusicContentPane.add(lblAlbm);
		
		addMusicAlbum = new JTextField();
		addMusicAlbum.setColumns(10);
		addMusicAlbum.setBounds(318, 413, 257, 31);
		MusicContentPane.add(addMusicAlbum);
		
		JLabel lblTr = new JLabel("Tür: ");
		lblTr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTr.setBounds(110, 453, 169, 38);
		MusicContentPane.add(lblTr);
		
		addMusicType = new JTextField();
		addMusicType.setColumns(10);
		addMusicType.setBounds(318, 461, 257, 31);
		MusicContentPane.add(addMusicType);
		
		JLabel lblSre = new JLabel("Süre: ");
		lblSre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSre.setBounds(110, 501, 169, 38);
		MusicContentPane.add(lblSre);
		
		addMusicTime = new JTextField();
		addMusicTime.setColumns(10);
		addMusicTime.setBounds(318, 509, 257, 31);
		MusicContentPane.add(addMusicTime);
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
}
