import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Image;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.UIManager;

public class LoginPage {

	JFrame frame;
	private JTextField txtUsername;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblOnlineExamSystem = new JLabel("Online Exam System");
		lblOnlineExamSystem.setForeground(new Color(255, 255, 255));
		lblOnlineExamSystem.setFont(new Font("Liberation Sans", Font.BOLD, 24));
		lblOnlineExamSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineExamSystem.setBounds(132, 12, 300, 45);
		panel.add(lblOnlineExamSystem);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(new Color(0, 0, 51));
		txtUsername.setBackground(new Color(184, 207, 229));
		txtUsername.setText("UserName");
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtUsername.getText().equals("UserName"))
				{
					txtUsername.setText(null);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsername.getText().equals(""))
				{
					txtUsername.setText("UserName");
				}
			}
		});
		txtUsername.setBounds(176, 69, 200, 39);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setForeground(new Color(0, 0, 51));
		textField_1.setBackground(UIManager.getColor("Button.shadow"));
		textField_1.setEchoChar((char)0);
		textField_1.setText("Password");
		textField_1.addFocusListener(new FocusAdapter() {
			String value2=new String(textField_1.getPassword());
			@Override
			public void focusGained(FocusEvent e) {
				
				if(value2.equals("Password"))
				{
					textField_1.setText(null);
					textField_1.setEchoChar('*');
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(value2.equals(""))
				{
					value2.equals("Password");
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(176, 132, 200, 39);
		panel.add(textField_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 51));
		comboBox.setBackground(UIManager.getColor("Button.background"));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Faculty", "Student"}));
		comboBox.setBounds(208, 223, 140, 39);
		panel.add(comboBox);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 0, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username=txtUsername.getText();
				String password=new String((textField_1).getPassword());
				String login = (String)comboBox.getSelectedItem();
				if (login=="Admin")
				{
					//admin login
					if (username.equals("Admin") && password.equals("admin123")) {
						Admin a = new Admin();
						a.setVisible(true);
						frame.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "WRONG CREDENTIALS");
					}
					
				}
				else if (login=="Faculty")
				{
					long faculty_id = 0;
					//faculty login
					//select faculty_id, password from faculty where username = ?
					try {
						Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", "root", "Krishna@sql");
						PreparedStatement preparedStatement = connection.prepareStatement("select faculty_id, password from faculty where username = ?");
						preparedStatement.setString(1, username);
						ResultSet resultSet = preparedStatement.executeQuery();
						if (resultSet.next()) {
							faculty_id = resultSet.getLong("faculty_id");
							String userPassword = resultSet.getString("password");
							if (password.equals(userPassword)) {
								Faculty f = new Faculty(faculty_id, username, password);
								f.setVisible(true);
								frame.dispose();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "WRONG CREDENTIALS");
						}
						connection.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if (login=="Student")
				{
					
					long student_id = 0;
					//student login
					//select student_id, password from faculty where username = ?
					try {
						Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", "root", "Krishna@sql");
						PreparedStatement preparedStatement = connection.prepareStatement("select student_id, password from student where username = ?");
						preparedStatement.setString(1, username);
						ResultSet resultSet = preparedStatement.executeQuery();
						if (resultSet.next()) {
							student_id = resultSet.getLong("student_id");
							String userPassword = resultSet.getString("password");
							System.out.println("username: " + username);
							System.out.println("password: " + password );
							System.out.println("userpassword: " + userPassword);
							if (password.equals(userPassword)) {
								Student s = new Student(student_id, username, password);
								s.setVisible(true);
								frame.dispose();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "WRONG CREDENTIALS");
						}
						connection.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				txtUsername.setText("UserName");
				textField_1.setEchoChar((char)0);
				textField_1.setText("Password");
			}
		});
		btnLogin.setBounds(117, 285, 140, 45);
		panel.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 18));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(255, 0, 0));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(292, 285, 140, 45);
		panel.add(btnCancel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected())
				{
					textField_1.setEchoChar((char)0);
				}
				else
					textField_1.setEchoChar('*');
			}
		});
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setBackground(new Color(0, 0, 51));
		chckbxNewCheckBox.setBounds(208, 185, 155, 23);
		panel.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Apps-preferences-system-login-icon (3).png")).getImage();
		ImageIcon icon = new ImageIcon(img);
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(18, 69, 140, 128);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/login-icon (1).png")).getImage();
		ImageIcon icon1 = new ImageIcon(img1);
		label.setIcon(icon1);
		label.setBounds(394, 69, 140, 128);
		panel.add(label);
	}
}
