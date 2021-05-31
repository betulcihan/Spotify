package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Help.*;
import System.Admin;
import System.User;

public class LogInGUI extends JFrame {

	private JPanel loginContentPane;
	private JTextField usernameField;
	private JPasswordField userPassField;
	private JTextField adminNameField;
	private JPasswordField adminPassField;
	private ConnectionHelper connection = new ConnectionHelper();
	static Admin admin = new Admin();
	public static User user = new User();
	Connection con = null;
	Statement stat;
	ResultSet res;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInGUI frame = new LogInGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogInGUI() {
		setTitle("MyDB Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 486);
		loginContentPane = new JPanel();
		loginContentPane.setBackground(new Color(176, 196, 222));
		loginContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginContentPane);
		loginContentPane.setLayout(null);
		
		JTabbedPane loginTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		loginTabbedPane.setBounds(10, 143, 534, 296);
		loginContentPane.add(loginTabbedPane);
		
		JPanel userLoginPanel = new JPanel();
		loginTabbedPane.addTab("Kullanýcý Giriþ", null, userLoginPanel, null);
		userLoginPanel.setLayout(null);
		
		JLabel lblUserName = new JLabel("Kullanýcý Adý: ");
		lblUserName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblUserName.setBounds(41, 41, 141, 41);
		userLoginPanel.add(lblUserName);
		
		usernameField = new JTextField();
		usernameField.setBounds(198, 47, 238, 35);
		userLoginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUserPassword = new JLabel("Þifre: ");
		lblUserPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblUserPassword.setBounds(41, 115, 141, 41);
		userLoginPanel.add(lblUserPassword);
		
		userPassField = new JPasswordField();
		userPassField.setBounds(198, 121, 238, 35);
		userLoginPanel.add(userPassField);
		
		JButton btnSignIn = new JButton("Giriþ Yap");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usernameField.getText().length() == 0 || userPassField.getText().length() == 0) {
					Helper.showMessage("fillUp");
					usernameField.setText(null);
					userPassField.setText(null);
				}else {
					try {
						boolean a = false;
						con = connection.connectionHelper();
						stat = (Statement) con.createStatement();
			            res = stat.executeQuery("select * from  user");
			            while (res.next()) {
							if(usernameField.getText().equals(res.getString("user_name")) && userPassField.getText().equals(res.getString("user_pass"))){
								a=true;
								user.setMemberID(res.getInt("user_id"));
								user.setMemberPass("user_pass");
								user.setMemberName(res.getString("user_name"));
								user.setMemberEmail(res.getString("user_email"));
								user.setPremium(res.getString("user_premium"));
								user.setMemberCountry(res.getString("user_country"));
								SignInGUI signInGUI = new SignInGUI(user);
								signInGUI.setVisible(true);
								dispose();
							}							
						}
			            if(a==false) {
							Helper.showMessage("wrongPass");
							usernameField.setText(null);
							userPassField.setText(null);
			            }
			            con.close();
			            stat.close();
			            res.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSignIn.setBounds(42, 203, 198, 56);
		userLoginPanel.add(btnSignIn);
		
		JButton btnSignUp = new JButton("Kayýt Ol");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpGUI signUpGUI = new SignUpGUI();
				signUpGUI.setVisible(true);
				dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSignUp.setBounds(289, 203, 198, 56);
		userLoginPanel.add(btnSignUp);
		
		JPanel adminLogInPanel = new JPanel();
		loginTabbedPane.addTab("Admin Giriþ", null, adminLogInPanel, null);
		adminLogInPanel.setLayout(null);
		
		JLabel lblAdminName = new JLabel("Kullanýcý Adý: ");
		lblAdminName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblAdminName.setBounds(47, 41, 141, 41);
		adminLogInPanel.add(lblAdminName);
		
		adminNameField = new JTextField();
		adminNameField.setColumns(10);
		adminNameField.setBounds(204, 47, 238, 35);
		adminLogInPanel.add(adminNameField);
		
		JLabel lblAdminPassword = new JLabel("Þifre: ");
		lblAdminPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblAdminPassword.setBounds(47, 115, 141, 41);
		adminLogInPanel.add(lblAdminPassword);
		
		adminPassField = new JPasswordField();
		adminPassField.setBounds(204, 121, 238, 35);
		adminLogInPanel.add(adminPassField);
		
		JButton btnSignInAdmin = new JButton("Giriþ Yap");
		btnSignInAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adminNameField.getText().length() == 0 || adminPassField.getText().length() == 0) {
					Helper.showMessage("fillUp");
				}else {
					try {
						boolean a = false;
						Connection con = connection.connectionHelper();
						Statement stat = (Statement) con.createStatement();
			            ResultSet res = stat.executeQuery("select * from  admin");
			            while (res.next()) {
							if(adminNameField.getText().equals(res.getString("admin_name")) && adminPassField.getText().equals(res.getString("admin_pass"))){
								a = true;
								admin.setMemberID(res.getInt("admin_id"));
								admin.setMemberPass("admin_pass");
								admin.setMemberName(res.getString("admin_name"));
								admin.setMemberEmail(res.getString("admin_email"));
								admin.setMemberCountry(res.getString("admin_country"));
								System.out.println(admin.getMemberName());
								AdminGUI adminGUI = new AdminGUI(admin);
								adminGUI.setVisible(true);
								dispose();
							}
						}
			            if(a==false) {
							Helper.showMessage("wrongPass");
							adminNameField.setText(null);
							adminPassField.setText(null);
			            }
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnSignInAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSignInAdmin.setBounds(68, 203, 394, 56);
		adminLogInPanel.add(btnSignInAdmin);
		
		JLabel lblNewLabel = new JLabel("MyDB Uygulamasýna Hoþ Geldiniz");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(90, 39, 384, 58);
		loginContentPane.add(lblNewLabel);
	}
}
