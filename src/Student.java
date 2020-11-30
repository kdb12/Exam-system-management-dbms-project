import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;
//import sample.Student;

public class Student extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private CardLayout card;
	private long student_id;
	private String username, password;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Student frame = new Student();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Student(long student_id, String username, String password) {
		this.student_id = student_id;
		this.username = username;
		this.password = password;
		setTitle("Student");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, ScreenSize.width, ScreenSize.height-50);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 51));
		menuBar.setPreferredSize(new Dimension(0, 70));
		setJMenuBar(menuBar);
		
		mntmNewMenuItem_3 = new JMenuItem("AVAILABLE EXAMS");
		mntmNewMenuItem_3.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmNewMenuItem_3.setBackground(new Color(0, 0, 51));
		mntmNewMenuItem_3.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane,"EXAMS");
			try {
					
					table.setVisible(false);
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
					
					ResultSet Rs= con.createStatement().executeQuery("select * from examviewforstudents");
					
					table.setModel(DbUtils.resultSetToTableModel(Rs));
					
					JTableHeader tableheader = table.getTableHeader();
					 TableColumnModel P = tableheader.getColumnModel();
					 
					 P.getColumn(0).setHeaderValue("ID");
					 P.getColumn(0).setPreferredWidth(369);
					 
					 P.getColumn(1).setHeaderValue("TITLE");
					 P.getColumn(1).setPreferredWidth(369);
					 
					 P.getColumn(2).setHeaderValue("FACULTY");
					 P.getColumn(2).setPreferredWidth(369);
					 					 
					table.setEnabled(false);
					
					table.setVisible(true);
					
					con.close();
					
				} catch (ClassNotFoundException eS) {
					
					eS.printStackTrace();
				} catch (SQLException eS) {
					eS.printStackTrace();
				}	
			}
		});
		menuBar.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_1 = new JMenuItem("SOLVED EXAMS");
		mntmNewMenuItem_1.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmNewMenuItem_1.setBackground(new Color(0, 0, 51));
		mntmNewMenuItem_1.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane,"EXAMS2");
try {
					
					table_1.setVisible(false);
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
					
					ResultSet Rs= con.createStatement().executeQuery("select * from solvedexamviewforstudents");
					
					
					table_1.setModel(DbUtils.resultSetToTableModel(Rs));
					
					JTableHeader tableheader = table_1.getTableHeader();
					 TableColumnModel P = tableheader.getColumnModel();
					 
					 P.getColumn(0).setHeaderValue("EXAM ID");
					 P.getColumn(0).setPreferredWidth(369);
					 
					 P.getColumn(1).setHeaderValue("TITLE");
					 P.getColumn(1).setPreferredWidth(369);
					 
					 P.getColumn(2).setHeaderValue("MARKS OBTAINED");
					 P.getColumn(2).setPreferredWidth(369);
					 
					 P.getColumn(3).setHeaderValue("APPEARING DATE");
					 P.getColumn(3).setPreferredWidth(369);
					 
					table_1.setEnabled(false);
					
					table_1.setVisible(true);
					
					con.close();
					
					
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException ew) {
					// TODO Auto-generated catch block
					ew.printStackTrace();
				}	
			}
		});
		menuBar.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_2 = new JMenuItem("USER DETAILS");
		mntmNewMenuItem_2.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmNewMenuItem_2.setBackground(new Color(0, 0, 51));
		mntmNewMenuItem_2.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane,"PROFILE");
			}
		});
		menuBar.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem = new JMenuItem("LOG OUT");
		mntmNewMenuItem.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmNewMenuItem.setBackground(new Color(0, 0, 51));
		mntmNewMenuItem.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int p=JOptionPane.showConfirmDialog(null, "DO YOU WANT TOO LOG OUT??","LOG OUT",JOptionPane.YES_NO_OPTION);
				if(p==JOptionPane.YES_OPTION)
				{
					new LoginPage().frame.setVisible(true);
					dispose();
				}
				else
				{
					
				}
			}
		});
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(card=new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 51));
		panel.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		contentPane.add(panel, "EXAMS");
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(50);
		table.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = table.rowAtPoint(e.getPoint());
				        int col = table.columnAtPoint(e.getPoint());
				        if (row >= 0 && col >= 0) {
							long clickedExamId =(long) table.getValueAt(row, 0);
							//new ExamPage(clickedExamId, student_id, username, password).setVisible(true);
							//long exam_id, String exam_title, String faculty_name, long student_id, String username, String password
							new ExamStartPage(clickedExamId, (String)table.getValueAt(row, 1), String.valueOf(table.getValueAt(row, 2)), student_id, username, password).setVisible(true);
						}
						
					}
					
				});
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 51));
		panel_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		contentPane.add(panel_1, "EXAMS2");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setRowHeight(50);
		
		
		
//		table.addMouseListener(new java.awt.event.MouseAdapter() {
//			   @Override
//			   public void mouseClicked(java.awt.event.MouseEvent evt) {
//			   
//			       int row = table.rowAtPoint(evt.getPoint());
//			       int col = table.columnAtPoint(evt.getPoint());
//			       if (row >= 0 && col >= 0) {
//			       
//			        //start the exam  
//			        //exam id can be get at table.getValueAt(row,0);
//			        //start exam by exam id..........................................................
//			       
//			           JOptionPane.showMessageDialog(null,table.getValueAt(row, 0)+" "+table.getValueAt(row, col)+" ");
//			           
//			           
//			       }
//			   }
//			});
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel_2.setForeground(new Color(0, 0, 51));
		contentPane.add(panel_2, "PROFILE");
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("NAME :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(new Color(0, 0, 51));
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setBounds(382, 148, 132, 30);
		label.setVisible(false);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("USERNAME :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(new Color(0, 0, 51));
		label_1.setFont(new Font("Dialog", Font.BOLD, 16));
		label_1.setBounds(382, 190, 132, 30);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("PASSWORD :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(new Color(0, 0, 51));
		label_2.setFont(new Font("Dialog", Font.BOLD, 16));
		label_2.setBounds(382, 232, 132, 30);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("YEAR :");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(new Color(0, 0, 51));
		label_3.setFont(new Font("Dialog", Font.BOLD, 16));
		label_3.setBounds(382, 274, 132, 30);
		label_3.setVisible(false);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("DEPARTMENT :");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setForeground(new Color(0, 0, 51));
		label_4.setFont(new Font("Dialog", Font.BOLD, 16));
		label_4.setBounds(382, 316, 132, 30);
		label_4.setVisible(false);
		panel_2.add(label_4);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 51));
		textField.setColumns(10);
		textField.setBounds(555, 150, 330, 30);
		textField.setVisible(false);
		panel_2.add(textField);
		
		
		
		
		//name
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(0, 0, 51));
		textField_1.setColumns(10);
		textField_1.setBounds(555, 192, 330, 30);
		textField_1.setText(username);
		panel_2.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(0, 0, 51));
		textField_2.setColumns(10);
		textField_2.setBounds(555, 234, 330, 30);
		textField_2.setText(password);
		panel_2.add(textField_2);
		
		//year
		textField_3 = new JTextField();
		textField_3.setForeground(new Color(0, 0, 51));
		textField_3.setColumns(10);
		textField_3.setBounds(555, 276, 330, 30);
		textField_3.setVisible(false);
		panel_2.add(textField_3);
		
		//department
		textField_4 = new JTextField();
		textField_4.setForeground(new Color(0, 0, 51));
		textField_4.setColumns(10);
		textField_4.setBounds(555, 318, 330, 30);
		textField_4.setVisible(false);
		panel_2.add(textField_4);
		
		JLabel label_5 = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/Manager-icon.png")).getImage();
		ImageIcon icon1 = new ImageIcon(img1);
		label_5.setIcon(icon1);
		label_5.setBounds(175, 176, 128, 128);
		panel_2.add(label_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(0, 0, 51));
		panel_3.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		contentPane.add(panel_3, "LOG OUT");
	}
}
