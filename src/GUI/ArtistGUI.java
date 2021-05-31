package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Help.Helper;
import System.Artist;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArtistGUI extends JFrame {
	
	Artist artist  = new Artist();
	private JPanel addArtistContentPane;
	private JTextField artistNameField;
	private JTextField artistCountryField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArtistGUI frame = new ArtistGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ArtistGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 347);
		addArtistContentPane = new JPanel();
		addArtistContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(addArtistContentPane);
		addArtistContentPane.setLayout(null);
		
		JLabel lblArtistName = new JLabel("Sanat\u00E7\u0131 Ad\u0131: ");
		lblArtistName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtistName.setBounds(38, 39, 169, 38);
		addArtistContentPane.add(lblArtistName);
		
		artistNameField = new JTextField();
		artistNameField.setColumns(10);
		artistNameField.setBounds(234, 47, 257, 31);
		addArtistContentPane.add(artistNameField);
		
		JLabel lblArtistCountry = new JLabel("\u00DClke: ");
		lblArtistCountry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArtistCountry.setBounds(38, 97, 169, 38);
		addArtistContentPane.add(lblArtistCountry);
		
		artistCountryField = new JTextField();
		artistCountryField.setColumns(10);
		artistCountryField.setBounds(234, 105, 257, 31);
		addArtistContentPane.add(artistCountryField);
		
		JButton btnGoBackArtist = new JButton("Geri D\u00F6n");
		btnGoBackArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminGUI adminGUI = new AdminGUI(LogInGUI.admin);
				adminGUI.setVisible(true);
				dispose();
			}
		});
		btnGoBackArtist.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBackArtist.setBounds(26, 229, 169, 46);
		addArtistContentPane.add(btnGoBackArtist);
		
		JButton btnAddArtist = new JButton("Ekle");
		btnAddArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(artistNameField.getText().length() == 0 || artistCountryField.getText().length() == 0  ) {
					Helper.showMessage("fillUp");
				}else{
					boolean control = artist.addArtist(artistNameField.getText(), artistCountryField.getText());
				if(control) {
					artist.getArtistList();
					artistNameField.setText(null);
					artistCountryField.setText(null);
				}
				AdminGUI adminGUI2 = new AdminGUI(LogInGUI.admin);
				adminGUI2.setVisible(true);
				dispose();
				}
			}
		});
		btnAddArtist.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddArtist.setBounds(309, 229, 169, 46);
		addArtistContentPane.add(btnAddArtist);
	}
}
