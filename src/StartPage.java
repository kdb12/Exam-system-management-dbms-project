import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JProgressBar;

public class StartPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtExamManagementSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartPage frame = new StartPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartPage() {
		setPreferredSize(new Dimension(1023, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated(true);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setBounds(100, 100, 1567, 822);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ToolTip.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txtExamManagementSystem = new JTextField();
		txtExamManagementSystem.setForeground(Color.YELLOW);
		txtExamManagementSystem.setBackground(Color.BLACK);
		txtExamManagementSystem.setEditable(false);
		txtExamManagementSystem.setFont(new Font("Sitka Display", Font.BOLD, 100));
		txtExamManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		txtExamManagementSystem.setText("EXAM MANAGEMENT SYSTEM");
		txtExamManagementSystem.setPreferredSize(new Dimension(6, 125));
		contentPane.add(txtExamManagementSystem, BorderLayout.NORTH);
		txtExamManagementSystem.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CREATED BY : ");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 50));
		contentPane.add(lblNewLabel, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Tree.selectionBackground"));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lbl31209 = new JLabel("SOURAV BATGIRI : 31209");
		lbl31209.setFont(new Font("Sitka Display", Font.BOLD, 80));
		lbl31209.setHorizontalAlignment(SwingConstants.LEADING);
		panel.add(lbl31209);
		
		JLabel lbl31212 = new JLabel("KRISHNA BHUTADA : 31212");
		lbl31212.setFont(new Font("Sitka Display", Font.BOLD, 80));
		lbl31212.setHorizontalAlignment(SwingConstants.LEADING);
		panel.add(lbl31212);
		
		JLabel lbl31211 = new JLabel("PRATIK BHOJANE : 31211");
		lbl31211.setFont(new Font("Sitka Display", Font.BOLD, 80));
		lbl31211.setHorizontalAlignment(SwingConstants.LEADING);
		panel.add(lbl31211);
		
		JLabel lbl31215 = new JLabel("HRISHIKESH CHAVAN : 31215");
		lbl31215.setFont(new Font("Sitka Display", Font.BOLD, 80));
		lbl31215.setHorizontalAlignment(SwingConstants.LEADING);
		panel.add(lbl31215);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("Sitka Display", Font.BOLD, 50));
		progressBar.setOpaque(true);
		progressBar.setForeground(Color.RED);
		progressBar.setBackground(Color.BLACK);
		progressBar.setPreferredSize(new Dimension(146, 70));
		progressBar.setStringPainted(true);
		
		contentPane.add(progressBar, BorderLayout.SOUTH);
		
		new Thread(new Runnable()
				{

					@Override
					public void run() {
						
						for(int i=0;i<=100;i++)
						{
							progressBar.setValue(i);
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						JOptionPane.showMessageDialog(contentPane,"APPLICATION STARTED SUCCESSFULLY");
						
						new LoginPage().frame.setVisible(true);
						dispose();
						
					}
			
				}).start();
	}

}
