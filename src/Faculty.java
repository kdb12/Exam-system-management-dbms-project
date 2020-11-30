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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;
//import sample.Faculty;

public class Faculty extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private CardLayout card;
	private JMenuBar menuBar;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel label;
	
	private long fid;
	private String username, password;
	
//	protected Object frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Faculty frame = new Faculty();
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
	public Faculty(long fid, String username, String password) {
		this.fid = fid;
		this.username = username;
		this.password = password;
		setTitle("Faculty");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, ScreenSize.width, ScreenSize.height-50);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 51));
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		menuBar.setPreferredSize(new Dimension(0, 70));
		setJMenuBar(menuBar);
		
		mntmNewMenuItem_2 = new JMenuItem("HOME");
		mntmNewMenuItem_2.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmNewMenuItem_2.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_2.setBackground(new Color(0, 0, 51));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane, "HOME");
			}
		});
		menuBar.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_3 = new JMenuItem("EXAMS");
		mntmNewMenuItem_3.setBackground(new Color(0, 0, 51));
		mntmNewMenuItem_3.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmNewMenuItem_3.setForeground(new Color(255, 255, 255));
		//exam table display tab
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane, "EXAMS");
			try {
					//exam tables
				
					table.setVisible(false);
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
					//exam_id, title, max_marks, date_time_created, 
					ResultSet Rs= con.createStatement().executeQuery("select exam_id, title, max_marks, date_time_created from exam");
					table.setModel(DbUtils.resultSetToTableModel(Rs));
					
					JTableHeader tableheader = table.getTableHeader();
					 TableColumnModel P = tableheader.getColumnModel();
					 
					 P.getColumn(0).setHeaderValue("ID");
					 P.getColumn(0).setPreferredWidth(369);
					 
					 P.getColumn(1).setHeaderValue("TITLE");
					 P.getColumn(1).setPreferredWidth(369);
					 
					 P.getColumn(2).setHeaderValue("MARKS");
					 P.getColumn(2).setPreferredWidth(369);
					 
					 P.getColumn(3).setHeaderValue("DATE CREATED");
					 P.getColumn(3).setPreferredWidth(369);
					 			 
					table.setEnabled(false);
					
					table.setVisible(true);
					
					con.close();
					
					
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}
		});
		menuBar.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_1 = new JMenuItem("QUESTIONS");
		mntmNewMenuItem_1.setBackground(new Color(0, 0, 51));
		mntmNewMenuItem_1.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmNewMenuItem_1.setForeground(new Color(255, 255, 255));
		//question table display tab
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane, "QUESTIONS");
				try {
					table_1.setVisible(false);
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
					//question_id, type, title
					ResultSet Rs= con.createStatement().executeQuery("select question_id, type, title from questions");					
					
					table_1.setModel(DbUtils.resultSetToTableModel(Rs));
					
					JTableHeader tableheader = table_1.getTableHeader();
					 TableColumnModel P = tableheader.getColumnModel();
					 tableheader.setFont(new Font("Tahoma",Font.BOLD,25));
					 
					 P.getColumn(0).setHeaderValue("ID");
					 P.getColumn(0).setPreferredWidth(615);
					 
					 P.getColumn(1).setHeaderValue("TITLE");
					 P.getColumn(1).setPreferredWidth(615);
					 
					 P.getColumn(2).setHeaderValue("TYPE");
					 P.getColumn(2).setPreferredWidth(615);
					 					
					table_1.setEnabled(false);
					
					table_1.setVisible(true);
					
					con.close();
					}
					catch(Exception ee)
					{
						System.out.println(ee);
					}
			}
		});
		menuBar.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem = new JMenuItem("LOG OUT");
		mntmNewMenuItem.setBackground(new Color(0, 0, 51));
		mntmNewMenuItem.setFont(new Font("Dialog", Font.BOLD, 18));
		mntmNewMenuItem.setForeground(new Color(255, 255, 255));
		//logout tab
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
		contentPane.setLayout(card =new CardLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		contentPane.add(panel, "HOME");
		panel.setLayout(null);
		
		//add exam button
		JButton btnNewButton = new JButton("Add Exam");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddExam a = new AddExam(fid, username, password);
				a.frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton.setForeground(new Color(0, 0, 51));
		btnNewButton.setBounds(261, 81, 200, 45);
		panel.add(btnNewButton);
		
		//add question button
		JButton btnNewButton_1 = new JButton("Add Questions");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddQuestions a = new AddQuestions(fid, username, password);
				a.frame.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton_1.setForeground(new Color(0, 0, 51));
		btnNewButton_1.setBounds(261, 164, 200, 45);
		panel.add(btnNewButton_1);
		
		lblNewLabel = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/document-add-icon.png")).getImage();
		ImageIcon icon1 = new ImageIcon(img1);
		lblNewLabel.setIcon(icon1);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(50, 81, 128, 128);
		panel.add(lblNewLabel);
		
		label = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("/Supervisor-icon.png")).getImage();
		ImageIcon icon2 = new ImageIcon(img2);
		label.setIcon(icon2);
		label.setBounds(538, 77, 512, 512);
		panel.add(label);
		
		panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 51));
		panel_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		contentPane.add(panel_1, "EXAMS");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowHeight(50);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		panel_2 = new JPanel();
		panel_2.setForeground(new Color(0, 0, 51));
		panel_2.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		contentPane.add(panel_2, "QUESTIONS");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setRowHeight(70);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		contentPane.add(panel_3, "LOG OUT");
	}

}
