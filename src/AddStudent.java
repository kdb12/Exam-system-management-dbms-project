import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddStudent {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Long student_id;
    private int department_id;
    private int year;
    private String student_name, username, password;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent window = new AddStudent();
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
	public AddStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Add Student");
		frame.setBounds(100, 100, 520, 330);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName.setBounds(12, 12, 132, 30);
		panel.add(lblName);
		
		JLabel lblUsername = new JLabel("USERNAME :");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUsername.setBounds(12, 54, 132, 30);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD :");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setBounds(12, 96, 132, 30);
		panel.add(lblPassword);
		
		JLabel lblDepartment = new JLabel("DEPARTMENT :");
		lblDepartment.setForeground(new Color(255, 255, 255));
		lblDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartment.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDepartment.setBounds(12, 138, 132, 30);
		panel.add(lblDepartment);
		
		JLabel lblYear = new JLabel("YEAR :");
		lblYear.setForeground(new Color(255, 255, 255));
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setFont(new Font("Dialog", Font.BOLD, 16));
		lblYear.setBounds(12, 180, 132, 30);
		panel.add(lblYear);
		
		textField = new JTextField();
		textField.setBounds(162, 12, 330, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(162, 54, 330, 30);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(162, 96, 330, 30);
		panel.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 51));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Computer", "IT", "ENTC"}));
		comboBox.setBounds(162, 138, 160, 30);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setForeground(new Color(0, 0, 51));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"First Year (FE)", "Second Year (SE)", "Third Year (TE)", "Last Year (BE)"}));
		comboBox_1.setBounds(162, 180, 160, 30);
		panel.add(comboBox_1);
		
			
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//save student
				try {
					//Class.forName("com.mysql.jdbc.Driver");
					department_id = comboBox.getSelectedIndex() + 1;
					year = comboBox_1.getSelectedIndex() + 1;
					student_name = textField.getText();
					username = textField_1.getText();
					password = textField_2.getText();
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", "root", "Krishna@sql");
	
			        Statement statement = connection.createStatement();
			        PreparedStatement preparedStatementUpdateIdTable = connection.prepareStatement("UPDATE ids SET taken=1 WHERE id=? AND taken=0");
			        PreparedStatement addStudentCommand = connection.prepareStatement("INSERT INTO student (student_id, department_id, year, student_name, username, password) VALUES (?,?,?,?,?,?)");
			        ResultSet resultSet = statement.executeQuery("SELECT id FROM ids WHERE taken = 0 LIMIT 1");
			        if (resultSet.next()) {
			            student_id = resultSet.getLong("id");
			            preparedStatementUpdateIdTable.setLong(1, student_id);
			            preparedStatementUpdateIdTable.executeUpdate();

			            addStudentCommand.setLong(1, student_id);
			            addStudentCommand.setInt(2, department_id);
			            addStudentCommand.setInt(3, year);
			            addStudentCommand.setString(4, student_name);
			            addStudentCommand.setString(5, username);
			            addStudentCommand.setString(6, password);
			            addStudentCommand.executeUpdate();
			            createStudent(username, password);
			            JOptionPane.showMessageDialog(frame, "Student Added");
			        }
			        connection.close();
			        frame.dispose();
														
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSave.setForeground(new Color(0, 0, 255));
		btnSave.setBounds(162, 232, 140, 35);
		panel.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 18));
		btnCancel.setForeground(new Color(255, 0, 0));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(313, 232, 140, 35);
		panel.add(btnCancel);
	}
	
    public static void createStudent(String username, String password) {
        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system","root","Krishna@sql");
            PreparedStatement createUserCommand = connection.prepareStatement("create user ? identified by ?");
            createUserCommand.setString(1, username);
            createUserCommand.setString(2, password);
            PreparedStatement preparedStatement = connection.prepareStatement("grant insert on online_exam_system.appears to ?");
            preparedStatement.setString(1, username);
            PreparedStatement preparedStatement1 = connection.prepareStatement("grant select on online_exam_system.* to ?");
            preparedStatement1.setString(1, username);
            createUserCommand.executeUpdate();
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            System.out.println("student created successfully");
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
