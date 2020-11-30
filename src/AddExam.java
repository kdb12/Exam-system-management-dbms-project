import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AddExam {

	JFrame frame;
	private JTextField examTitle;
	private JTextField examDetails;
	private JTextField txtInMinutes;
	private JButton btnSaveExam;
	private JButton button_1;
	private JComboBox comboBox;
	private JLabel lblQuestionType;
	private JButton btnSelectQuestions;
	private JTable table;

	private long fid;
	private String username, password;
	private ArrayList<Long> questionsSelected;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddExam window = new AddExam();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public AddExam(long fid, String username, String password) {
		this.fid = fid;
		this.username = username;
		this.password = password;
		this.questionsSelected = new ArrayList<>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Add Exam");
		frame.setBounds(100, 100, 515, 345);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exam Title :");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitle.setBounds(12, 12, 132, 30);
		panel.add(lblTitle);
		
		JLabel lblDescription = new JLabel("Exam Details :");
		lblDescription.setForeground(new Color(255, 255, 255));
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDescription.setBounds(12, 54, 132, 30);
		panel.add(lblDescription);
		
		JLabel lblOptions = new JLabel("Time :");
		lblOptions.setForeground(new Color(255, 255, 255));
		lblOptions.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOptions.setFont(new Font("Dialog", Font.BOLD, 16));
		lblOptions.setBounds(12, 187, 132, 30);
		panel.add(lblOptions);
		
		examTitle = new JTextField();
		examTitle.setColumns(10);
		examTitle.setBounds(179, 14, 300, 30);
		panel.add(examTitle);
		
		examDetails = new JTextField();
		examDetails.setColumns(10);
		examDetails.setBounds(179, 56, 300, 78);
		panel.add(examDetails);
		
		txtInMinutes = new JTextField();
		txtInMinutes.setForeground(new Color(0, 0, 51));
		txtInMinutes.setText("in minutes");
		txtInMinutes.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtInMinutes.getText().equals("in minutes"))
				{
					txtInMinutes.setText(null);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtInMinutes.getText().equals(""))
				{
					txtInMinutes.setText("in minutes");
				}
			}
		});
		txtInMinutes.setColumns(10);
		txtInMinutes.setBounds(179, 189, 132, 30);
		panel.add(txtInMinutes);
		
		btnSaveExam = new JButton("Save");
		btnSaveExam.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSaveExam.setForeground(new Color(0, 0, 255));
		btnSaveExam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//exam_id, faculty_id, title, description, year, time, max_marks
//				private JTextField examTitle;
//				private JTextField examDetails;
//				private JTextField txtInMinutes;
//				private JButton btnSaveExam;
//				private JButton button_1;
//				private JComboBox comboBox;
//				private JLabel lblQuestionType;
//				private JButton btnSelectQuestions;
				//insert into exam values(?,?,?,?,?,?,?)
				
				try {
					long exam_id = 0L;
					int exam_year = comboBox.getSelectedIndex() + 1;
					int exam_time = Integer.parseInt(txtInMinutes.getText());
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
					
			        Statement statement = connection.createStatement();
			        PreparedStatement preparedStatementUpdateIdTable = connection.prepareStatement("UPDATE ids SET taken=1 WHERE id=? AND taken=0");
			        PreparedStatement addExamCommand = connection.prepareStatement("INSERT INTO exam (exam_id, faculty_id, title, description, year, time, max_marks) VALUES (?,?,?,?,?,?,?)");
			        ResultSet resultSet = statement.executeQuery("SELECT id FROM ids WHERE taken = 0 LIMIT 1");
			        if (resultSet.next()) {
			            exam_id = resultSet.getLong("id");
			            preparedStatementUpdateIdTable.setLong(1, exam_id);
			            preparedStatementUpdateIdTable.executeUpdate();
			            
			            addExamCommand.setLong(1, exam_id);
			            addExamCommand.setLong(2, fid);
			            addExamCommand.setString(3, examTitle.getText());
			            addExamCommand.setString(4, examDetails.getText());
			            addExamCommand.setInt(5, exam_year);
			            addExamCommand.setInt(6, exam_time);
			            addExamCommand.setInt(7, 0);
			            addExamCommand.executeUpdate();		
			            
			            PreparedStatement addSelQue = connection.prepareStatement("insert into selected_questions values (?,?)");
			            if (!questionsSelected.isEmpty()) {
							for (Long long1 : questionsSelected) {
								addSelQue.setLong(1, long1);
								addSelQue.setLong(2, exam_id);
								addSelQue.executeUpdate();
							}
						}
			            
			            JOptionPane.showMessageDialog(frame, "Exam added Added");
			        }
			        connection.close();
			        JOptionPane.showMessageDialog(frame, "Exam added successfully");
			        frame.dispose();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		
		btnSaveExam.setBounds(114, 230, 140, 35);
		panel.add(btnSaveExam);
		
		button_1 = new JButton("Cancel");
		button_1.setFont(new Font("Dialog", Font.BOLD, 18));
		button_1.setForeground(new Color(255, 0, 0));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button_1.setBounds(265, 230, 140, 35);
		panel.add(button_1);
		
		comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 51));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"First Year (FE)", "Second Year (SE)", "Third Year (TE)", "Last Year (BE)"}));
		comboBox.setBounds(179, 146, 132, 30);
		panel.add(comboBox);
		
		lblQuestionType = new JLabel("Year :");
		lblQuestionType.setForeground(new Color(255, 255, 255));
		lblQuestionType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuestionType.setFont(new Font("Dialog", Font.BOLD, 16));
		lblQuestionType.setBounds(12, 145, 132, 30);
		panel.add(lblQuestionType);
		
		btnSelectQuestions = new JButton("Select Questions");
		btnSelectQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//select questions
				SelectQuestions a = new SelectQuestions(questionsSelected);
				a.frame.setVisible(true);
			}
		});
		btnSelectQuestions.setForeground(new Color(0, 0, 51));
		btnSelectQuestions.setBounds(323, 146, 156, 30);
		panel.add(btnSelectQuestions);
		
//		table = new JTable();
//		table.setBounds(12, 228, 491, 126);
//		panel.add(table);
	}

}
