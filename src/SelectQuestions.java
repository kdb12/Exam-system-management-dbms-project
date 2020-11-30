import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SelectQuestions {

	JFrame frame;
	private JTable table;
	private JButton btnCancel;
	private String username, password;
	private ArrayList<Long> questionsSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					SelectQuestions window = new SelectQuestions();
//					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectQuestions(ArrayList<Long> selectedQuestions) {
		this.questionsSelected = selectedQuestions;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.username = "pbd";
		this.password = "pbd@123";
		frame = new JFrame("Select Questions");
		frame.setBounds(100, 100, 530, 435);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(12, 12, 491, 328);
		panel.add(jScrollPane);
		
		table = new JTable();
		
//		table.setBounds(12, 12, 491, 328);
		//panel.add(table);
		jScrollPane.setViewportView(table);
		
		//==
		table.setVisible(true);	
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		ListSelectionModel rowSelMod = table.getSelectionModel();
		table.setRowSelectionAllowed(true);
		try {
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
			//question_id, type, title
			ResultSet Rs= con.createStatement().executeQuery("select question_id, type, title from questions");					
			table.setModel(DbUtils.resultSetToTableModel(Rs));
			JTableHeader tableheader = table.getTableHeader();
			TableColumnModel P = tableheader.getColumnModel();
			tableheader.setFont(new Font("Tahoma",Font.BOLD,25));
			P.getColumn(0).setHeaderValue("ID");
			P.getColumn(0).setPreferredWidth(615);
			P.getColumn(1).setHeaderValue("TYPE");
			P.getColumn(1).setPreferredWidth(615);
			P.getColumn(2).setHeaderValue("TITLE");
			P.getColumn(2).setPreferredWidth(615);
			table.setEnabled(true);
			table.setVisible(true);
			con.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		//==
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(new Color(0, 0, 51));
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 20));
		
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selqs = table.getSelectedRows();
				
				for (int i : selqs) {
					long qid = (long) table.getValueAt(i, 0);
					questionsSelected.add(qid);
				}
				JOptionPane.showMessageDialog(frame, "Questions added successfully");
				frame.dispose();
			}
		});
		
		btnSubmit.setBounds(76, 352, 140, 34);
		panel.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnCancel.setForeground(new Color(255, 0, 0));
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCancel.setBounds(278, 352, 140, 34);
		panel.add(btnCancel);
	}
}
