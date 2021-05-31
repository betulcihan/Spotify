package GUI;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import System.Music;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;

public class CountryGUI extends JFrame {

	private JPanel contentPane;
	private JTable musicCountryTop10Table;
	private DefaultTableModel top10MusicCountryModel = null;
	private Object[] top10MusicCountryObjects = null;
	private Music music = new Music();
	int listSize;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String country = null;
					CountryGUI frame = new CountryGUI(country);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CountryGUI(String country) {
		setBackground(new Color(176, 196, 222));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 613);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		top10MusicCountryPage(country);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 90, 829, 478);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 809, 404);
		panel.add(scrollPane);
		
		musicCountryTop10Table = new JTable(top10MusicCountryModel);
		scrollPane.setViewportView(musicCountryTop10Table);
		
		JButton btnGoBackToSignIn = new JButton("Geri Dön");
		btnGoBackToSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignInGUI signInGUI = new SignInGUI(LogInGUI.user);
				signInGUI.setVisible(true);
				dispose();
			}
		});
		btnGoBackToSignIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoBackToSignIn.setBounds(10, 424, 160, 44);
		panel.add(btnGoBackToSignIn);
		
		JLabel lblNewLabel = new JLabel(country + " TOP 10 LÝSTESÝ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(24, 27, 361, 41);
		contentPane.add(lblNewLabel);
	}
	
	@SuppressWarnings("unchecked")
	public void top10MusicCountryPage(String country) {
		top10MusicCountryModel = new DefaultTableModel();
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
		top10MusicCountryModel.setColumnIdentifiers(adminColName);
		top10MusicCountryObjects = new Object[9];
		ArrayList<Music>musicCountryArrayList = music.getMusicCountryList(country);
		Collections.sort(musicCountryArrayList);
		listSize = musicCountryArrayList.size();
		if (musicCountryArrayList.size() >= 10) {
			listSize = 10;
		}
		for (int i = 0; i < listSize; i++) {
			top10MusicCountryObjects[0] = musicCountryArrayList.get(i).getMusicID();
			top10MusicCountryObjects[1] = musicCountryArrayList.get(i).getMusicName();
			top10MusicCountryObjects[2] = musicCountryArrayList.get(i).getMusicDate();
			top10MusicCountryObjects[3] = musicCountryArrayList.get(i).getMusicArtist();
			top10MusicCountryObjects[4] = musicCountryArrayList.get(i).getMusicAlbum();
			top10MusicCountryObjects[5] = musicCountryArrayList.get(i).getMusicType();
			top10MusicCountryObjects[6] = musicCountryArrayList.get(i).getMusicTime();
			top10MusicCountryObjects[7] = musicCountryArrayList.get(i).getMusicPlay();
			top10MusicCountryObjects[8] = musicCountryArrayList.get(i).getMusicCountry();
			top10MusicCountryModel.addRow(top10MusicCountryObjects);
		}
	}
}
