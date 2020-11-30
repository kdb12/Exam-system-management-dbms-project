import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class ExamStartPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private long exam_id, student_id;
	private String exam_title, faculty_name, username, password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					ExamStartPage frame = new ExamStartPage(9423776793L, "Grand Old Test", "Mitch Mconnell");
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExamStartPage(long exam_id, String exam_title, String faculty_name, long student_id, String username, String password) {
		this.exam_id = exam_id;
		this.exam_title = exam_title;
		this.faculty_name = faculty_name;
		this.student_id = student_id;
		this.username = username;
		this.password = password;
		this.setUndecorated(true);	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 877, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(0, 0, 51));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "EXAM", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Tahoma",Font.BOLD,40), new Color(255, 255, 255)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EXAM ID :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(113, 108, 240, 55);
		panel.add(lblNewLabel);
		
		JLabel lblExamTitle = new JLabel("EXAM TITLE :");
		lblExamTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExamTitle.setForeground(Color.WHITE);
		lblExamTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblExamTitle.setBounds(113, 190, 240, 55);
		panel.add(lblExamTitle);
		
		JLabel lblCreatedBy = new JLabel("CREATED BY :");
		lblCreatedBy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreatedBy.setForeground(Color.WHITE);
		lblCreatedBy.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCreatedBy.setBounds(113, 285, 240, 55);
		panel.add(lblCreatedBy);
		
		JLabel lblTotalMarks = new JLabel("TOTAL MARKS :");
		lblTotalMarks.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalMarks.setForeground(Color.WHITE);
		lblTotalMarks.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTotalMarks.setBounds(113, 377, 240, 55);
		panel.add(lblTotalMarks);
		
		//textField - examid
		textField = new JTextField();
		textField.setText(Long.toString(exam_id));
		textField.setCaretColor(Color.WHITE);
		textField.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		textField.setForeground(new Color(0, 0, 51));
		textField.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField.setBounds(420, 110, 349, 55);
		panel.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		textField_1 = new JTextField();
		textField_1.setText(exam_title);
		textField_1.setForeground(new Color(0, 0, 51));
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField_1.setColumns(10);
		textField_1.setCaretColor(Color.WHITE);
		textField_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		textField_1.setBounds(420, 192, 349, 55);
		textField_1.setEditable(false);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText(faculty_name);
		textField_2.setForeground(new Color(0, 0, 51));
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField_2.setColumns(10);
		textField_2.setCaretColor(Color.WHITE);
		textField_2.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		textField_2.setBounds(420, 287, 349, 55);
		textField_2.setEditable(false);
		panel.add(textField_2);
		
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setForeground(new Color(0, 0, 51));
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField_3.setColumns(10);
		textField_3.setCaretColor(Color.WHITE);
		textField_3.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		textField_3.setBounds(420, 377, 349, 55);
		textField_3.setEditable(false);
		panel.add(textField_3);
		
		JButton btnNewButton = new JButton("START EXAM");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ExamPage(exam_id,student_id, username, password).setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBackground(new Color(0,0,51));
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.setBounds(113, 508, 260, 65);
		panel.add(btnNewButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				//new Student().setVisible(true);
			}
		});
		btnCancel.setForeground(Color.ORANGE);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCancel.setBackground(new Color(0,0,51));
		btnCancel.setBounds(554, 508, 260, 65);
		panel.add(btnCancel);
//		Image img = new ImageIcon(this.getClass().getResource("/Actions-mail-mark-task-icon.png")).getImage();
//		ImageIcon icon = new ImageIcon(img);
//		ImageIcon ic=new ImageIcon("C:\\Users\\91724\\Downloads\\back.png");
//		img=ic.getImage();
//		img=img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
//		ic=new ImageIcon(img);
	}
}
