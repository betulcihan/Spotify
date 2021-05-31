package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Help.Helper;
import System.User;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpGUI extends JFrame {

	private JPanel signUpPane;
	private JTextField signUpNameField;
	public User user = new User();
	private JTextField signUpEmailField;
	private JTextField signUpCountryField;
	private JTextField signUpPassField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpGUI frame = new SignUpGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUpGUI() {
		setTitle("Kayýt Ol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 434);
		signUpPane = new JPanel();
		signUpPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(signUpPane);
		signUpPane.setLayout(null);
		
		signUpNameField = new JTextField();
		signUpNameField.setColumns(10);
		signUpNameField.setBounds(185, 45, 238, 35);
		signUpPane.add(signUpNameField);
		
		JLabel lblSignUpUserName = new JLabel("Kullanýcý Adý: ");
		lblSignUpUserName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblSignUpUserName.setBounds(28, 39, 141, 41);
		signUpPane.add(lblSignUpUserName);
		
		JLabel lblSignUpUserPassword = new JLabel("Þifre: ");
		lblSignUpUserPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblSignUpUserPassword.setBounds(28, 227, 141, 35);
		signUpPane.add(lblSignUpUserPassword);
		
		JLabel lblSignUpEmail = new JLabel("Email:");
		lblSignUpEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblSignUpEmail.setBounds(28, 102, 141, 41);
		signUpPane.add(lblSignUpEmail);
		
		JLabel lblSignUpCountry = new JLabel("Ülke:");
		lblSignUpCountry.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblSignUpCountry.setBounds(28, 164, 141, 35);
		signUpPane.add(lblSignUpCountry);
		
		JButton btnNewButton = new JButton("Premium Kayýt Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(signUpNameField.getText().length() == 0 || signUpEmailField.getText().length() == 0 || signUpPassField.getText().length() == 0 || signUpCountryField.getText().length() == 0) {
					Helper.showMessage("fillUp");
				}else {
					boolean signUp = user.isSignUp(signUpNameField.getText(), signUpEmailField.getText(), signUpPassField.getText(), "y", signUpCountryField.getText());
					if(signUp) {
						LogInGUI logInGUI = new LogInGUI();
						logInGUI.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(33, 314, 197, 62);
		signUpPane.add(btnNewButton);
		
		JButton btnKaytOl = new JButton("Üretsiz Kayýt Ol");
		btnKaytOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(signUpNameField.getText().length() == 0 || signUpEmailField.getText().length() == 0 || signUpPassField.getText().length() == 0 || signUpCountryField.getText().length() == 0) {
					Helper.showMessage("fillUp");
				}else {
					boolean signUp = user.isSignUp(signUpNameField.getText(), signUpEmailField.getText(), signUpPassField.getText(), "n", signUpCountryField.getText());
					if(signUp) {
						LogInGUI logInGUI = new LogInGUI();
						logInGUI.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnKaytOl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnKaytOl.setBounds(258, 314, 197, 62);
		signUpPane.add(btnKaytOl);
		
		signUpEmailField = new JTextField();
		signUpEmailField.setColumns(10);
		signUpEmailField.setBounds(185, 110, 238, 35);
		signUpPane.add(signUpEmailField);
		
		signUpCountryField = new JTextField();
		signUpCountryField.setColumns(10);
		signUpCountryField.setBounds(185, 169, 238, 35);
		signUpPane.add(signUpCountryField);
		
		signUpPassField = new JTextField();
		signUpPassField.setColumns(10);
		signUpPassField.setBounds(185, 232, 238, 35);
		signUpPane.add(signUpPassField);
	}
}
