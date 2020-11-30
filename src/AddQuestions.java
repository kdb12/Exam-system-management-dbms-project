import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
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
import java.sql.Statement;

public class AddQuestions {

	JFrame frame;
	private JTextField queTitle;
	private JTextField queDescr;
	private JTextField option_1;
	private JTextField option_2;
	private JTextField option_3;
	private JTextField option_4;
	private long fid;
	private String username, password;
		

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddQuestions window = new AddQuestions();
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
	public AddQuestions(long fid, String username, String password) {
		this.fid = fid;
		this.username = username;
		this.password = password;
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Add Questions");
		frame.setBounds(100, 100, 597, 420);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Question Type :");
		label.setForeground(new Color(255, 255, 255));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(12, 12, 132, 30);
		panel.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 51));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Single Answer", "Multiple Answer"}));
		comboBox.setBounds(180, 12, 160, 30);
		panel.add(comboBox);
		
		JLabel label_1 = new JLabel("Title :");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.BOLD, 16));
		label_1.setBounds(12, 54, 132, 30);
		panel.add(label_1);
		
		queTitle = new JTextField();
		queTitle.setColumns(10);
		queTitle.setBounds(179, 54, 300, 30);
		panel.add(queTitle);
		
		queDescr = new JTextField();
		queDescr.setColumns(10);
		queDescr.setBounds(179, 98, 300, 78);
		panel.add(queDescr);
		
		JLabel label_2 = new JLabel("Description:");
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.BOLD, 16));
		label_2.setBounds(12, 96, 132, 30);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Options :");
		label_3.setForeground(new Color(255, 255, 255));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Dialog", Font.BOLD, 16));
		label_3.setBounds(12, 190, 132, 30);
		panel.add(label_3);
		
		option_1 = new JTextField();
		option_1.setForeground(new Color(0, 0, 51));
		option_1.setText("1st Option");
		option_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(option_1.getText().equals("1st Option"))
				{
					option_1.setText(null);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(option_1.getText().equals(""))
				{
					option_1.setText("1st Option");
				}
			}
		});
		option_1.setColumns(10);
		option_1.setBounds(179, 190, 300, 30);
		panel.add(option_1);
		
		option_2 = new JTextField();
		option_2.setForeground(new Color(0, 0, 51));
		option_2.setText("2nd Option");
		option_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(option_2.getText().equals("2nd Option"))
				{
					option_2.setText(null);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(option_2.getText().equals(""))
				{
					option_2.setText("2nd Option");
				}
			}
		});
		option_2.setColumns(10);
		option_2.setBounds(179, 224, 300, 30);
		panel.add(option_2);
		
		option_3 = new JTextField();
		option_3.setForeground(new Color(0, 0, 51));
		option_3.setText("3rd Option");
		option_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(option_3.getText().equals("3rd Option"))
				{
					option_3.setText(null);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(option_3.getText().equals(""))
				{
					option_3.setText("3rd Option");
				}
			}
		});
		option_3.setColumns(10);
		option_3.setBounds(179, 258, 300, 30);
		panel.add(option_3);
		
		option_4 = new JTextField();
		option_4.setForeground(new Color(0, 0, 51));
		option_4.setText("4th Option");
		option_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(option_4.getText().equals("4th Option"))
				{
					option_4.setText(null);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(option_4.getText().equals(""))
				{
					option_4.setText("4th Option");
				}
			}
		});
		option_4.setColumns(10);
		option_4.setBounds(179, 292, 300, 30);
		panel.add(option_4);
		
		JCheckBox opt1 = new JCheckBox("");
		opt1.setBackground(new Color(0, 0, 51));
		opt1.setBounds(502, 195, 21, 23);
		panel.add(opt1);
		
		JCheckBox opt2 = new JCheckBox("");
		opt2.setBackground(new Color(0, 0, 51));
		opt2.setBounds(502, 227, 21, 23);
		panel.add(opt2);
		
		JCheckBox opt3 = new JCheckBox("");
		opt3.setBackground(new Color(0, 0, 51));
		opt3.setBounds(502, 261, 21, 23);
		panel.add(opt3);
		
		JCheckBox opt4 = new JCheckBox("");
		opt4.setBackground(new Color(0, 0, 51));
		opt4.setBounds(502, 295, 21, 23);
		panel.add(opt4);
		
		JButton btnSaveQue = new JButton("Save");
		btnSaveQue.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSaveQue.setForeground(new Color(0, 0, 255));
		btnSaveQue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//validation pending
				//save question here
				//question_id,faculty_id,type,title,description,ops1234
				//question_id: to generate
				//fid: dm
				//type:comboBox var
				//title: queTitle
				//description:queDescr
				//ops1234: option_1 option_2 option_3 option_4
				//ans: opt1 opt2 opt3 opt4
				
				long question_id;
				try {
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
					
			        Statement statement = connection.createStatement();
			        PreparedStatement preparedStatementUpdateIdTable = connection.prepareStatement("UPDATE ids SET taken=1 WHERE id=? AND taken=0");
			        ResultSet resultSet = statement.executeQuery("SELECT id FROM ids WHERE taken = 0 LIMIT 1");
			        if (resultSet.next()) {
			            question_id = resultSet.getLong("id");
			            preparedStatementUpdateIdTable.setLong(1, question_id);
			            preparedStatementUpdateIdTable.executeUpdate();
			            int queType = comboBox.getSelectedIndex() + 1;
			            //INSERT INTO questions VALUES (?,?,?,?,?,?,?,?,?)
			            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO questions(question_id, faculty_id, type, title, description, option_1, option_2, option_3, option_4) VALUES (?,?,?,?,?,?,?,?,?)");
			            preparedStatement.setLong(1, question_id);
			            preparedStatement.setLong(2, fid);
			            preparedStatement.setInt(3, queType);
			            preparedStatement.setString(4, queTitle.getText());
			            preparedStatement.setString(5, queDescr.getText());
			            preparedStatement.setString(6, option_1.getText());
			            preparedStatement.setString(7, option_2.getText());
			            preparedStatement.setString(8, option_3.getText());
			            preparedStatement.setString(9, option_4.getText());
			            
			            preparedStatement.executeUpdate();
			            
			            //INSERT INTO question_answers VALUES (?,?,?)
			            PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO question_answers VALUES (?,?,?)");
			            preparedStatement2.setLong(1, question_id);
			            preparedStatement2.setInt(2, queType);
			            
			            if (opt1.isSelected()) {
							preparedStatement2.setInt(3, 1);
							preparedStatement2.executeUpdate();
						} 
			            if (opt2.isSelected()) {
							preparedStatement2.setInt(3, 2);
							preparedStatement2.executeUpdate();
						} 
			            if (opt3.isSelected()) {
							preparedStatement2.setInt(3, 3);
							preparedStatement2.executeUpdate();
						} 
			            if (opt4.isSelected()) {
							preparedStatement2.setInt(3, 4);
							preparedStatement2.executeUpdate();
						}
			            	            
			            JOptionPane.showMessageDialog(frame, "Question added succesfully.");
			        }		        		        
			        			        
			        connection.close();		
			        frame.dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
				
		btnSaveQue.setBounds(179, 336, 140, 35);
		panel.add(btnSaveQue);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setFont(new Font("Dialog", Font.BOLD, 18));
		button_1.setForeground(new Color(255, 0, 0));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button_1.setBounds(330, 336, 140, 35);
		panel.add(button_1);
		
		
	}
}
