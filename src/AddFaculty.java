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

public class AddFaculty {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Long faculty_id;
    private int department_id;
    private String faculty_name, username, password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFaculty window = new AddFaculty();
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
	public AddFaculty() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Add Faculty");
		frame.setBounds(100, 100, 525, 330);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblFacultyName = new JLabel("NAME :");
		lblFacultyName.setForeground(new Color(255, 255, 255));
		lblFacultyName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFacultyName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFacultyName.setBounds(12, 43, 132, 30);
		panel.add(lblFacultyName);
		
		JLabel label_1 = new JLabel("USERNAME :");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.BOLD, 16));
		label_1.setBounds(12, 85, 132, 30);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("PASSWORD :");
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.BOLD, 16));
		label_2.setBounds(12, 127, 132, 30);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("DEPARTMENT :");
		label_3.setForeground(new Color(255, 255, 255));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Dialog", Font.BOLD, 16));
		label_3.setBounds(12, 169, 132, 30);
		panel.add(label_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(192, 43, 300, 30);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(192, 85, 300, 30);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(192, 127, 300, 30);
		panel.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 51));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Computer", "Electronics", "ENTC"}));
		comboBox.setBounds(192, 169, 160, 30);
		panel.add(comboBox);
		
		JButton button = new JButton("Save");
		button.setFont(new Font("Dialog", Font.BOLD, 18));
		button.setForeground(new Color(0, 0, 255));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//-------------
				//save faculty
				try {
					//Class.forName("com.mysql.jdbc.Driver");
					department_id = comboBox.getSelectedIndex() + 1;
					faculty_name = textField.getText();
					username = textField_1.getText();
					password = textField_2.getText();
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", "root", "Krishna@sql");
	
			        Statement statement = connection.createStatement();
			        PreparedStatement preparedStatementUpdateIdTable = connection.prepareStatement("UPDATE ids SET taken=1 WHERE id=? AND taken=0");
			        PreparedStatement addStudentCommand = connection.prepareStatement("INSERT INTO faculty (faculty_id, department_id, faculty_name, username, password) VALUES (?,?,?,?,?)");
			        ResultSet resultSet = statement.executeQuery("SELECT id FROM ids WHERE taken = 0 LIMIT 1");
			        if (resultSet.next()) {
			            faculty_id = resultSet.getLong("id");
			            preparedStatementUpdateIdTable.setLong(1, faculty_id);
			            preparedStatementUpdateIdTable.executeUpdate();

			            addStudentCommand.setLong(1, faculty_id);
			            addStudentCommand.setInt(2, department_id);
			            addStudentCommand.setString(3, faculty_name);
			            addStudentCommand.setString(4, username);
			            addStudentCommand.setString(5, password);
			            addStudentCommand.executeUpdate();
			            createFaculty(username, password);			            
			            JOptionPane.showMessageDialog(frame, "Faculty Added");
			        }
			        connection.close();
			        frame.dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
				//-------------	
			}
		});
		
		button.setBounds(192, 222, 140, 35);
		panel.add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setFont(new Font("Dialog", Font.BOLD, 18));
		button_1.setForeground(new Color(255, 0, 0));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button_1.setBounds(343, 222, 140, 35);
		panel.add(button_1);
	}
	
	public static void createFaculty(String username, String password) {
        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system","root","Krishna@sql");
            PreparedStatement createUserCommand = connection.prepareStatement("create user ? identified by ?");
            createUserCommand.setString(1, username);
            createUserCommand.setString(2, password);
            PreparedStatement preparedStatement = connection.prepareStatement("grant insert on online_exam_system.questions to ?");
            preparedStatement.setString(1, username);
            PreparedStatement preparedStatement1 = connection.prepareStatement("grant insert on online_exam_system.question_answers to ?");
            preparedStatement1.setString(1, username);
            PreparedStatement preparedStatement2 = connection.prepareStatement("grant insert on online_exam_system.exam to ?");
            preparedStatement2.setString(1, username);
            PreparedStatement preparedStatement3 = connection.prepareStatement("grant insert on online_exam_system.selected_questions to ?");
            preparedStatement3.setString(1, username);
            PreparedStatement preparedStatement4 = connection.prepareStatement("grant select on online_exam_system.* to ?");
            preparedStatement4.setString(1, username);
            PreparedStatement preparedStatement5 = connection.prepareStatement("grant select, insert, update on online_exam_system.ids to ?");
            preparedStatement5.setString(1, username);
            createUserCommand.executeUpdate();
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();
            preparedStatement4.executeUpdate();
            preparedStatement5.executeUpdate();
            System.out.println("faculty created successfully");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
