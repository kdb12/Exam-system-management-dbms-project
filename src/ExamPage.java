
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.Icon;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;

public class ExamPage extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JTextField txtQuestionTitle;
	private CardLayout card;
	private ButtonGroup bg;
	private JRadioButton ROption_1;
	private JRadioButton ROption_2;
	private JRadioButton ROption_3;
	private JRadioButton ROption_4;
	private JCheckBox COption_4;
	private JCheckBox COption_1;
	private JCheckBox COption_2;
	private JCheckBox COption_3;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTextArea txtrQuestionDescription;
	private JPanel panel_4;
	private JLabel lblNewLabel_1;
	private JPanel panel_5;
	private JPanel panel_6;
	private Timer timer;
	private JPanel panel_8;
	int min=0,sec=0;
	private JPanel panel_9;
	private JLabel lblNewLabel;
	private JLabel lblTime;
	private JButton btnPrev;
	private JButton btnSave;
	private JButton btnNext;
	ArrayList<Questions> questionsList;
	HashMap<Long,boolean[]> selected;
//	int qid=0;
//	long cqid=0;
	int qid;
	long cqid;
	private JPanel panel_10;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	
	private long exid, sid;
	private String username,password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//1060151698059274624
					ExamPage frame = new ExamPage(1060151698059274624L,823723283021680640L, "rishabhpanth", "rishabhpanth123");
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
	public ExamPage(long exid, long sid, String username, String password)
	{
		qid=0;
		cqid=0;
		this.exid = exid;
		this.sid = sid;
		this.username = username;
		this.password = password;
		questionsList=new ArrayList<>();
		selected=new HashMap<>();
		getQuestionsAndAnswers();
		
		//==
		String lExTitle = null, lstuName = null;
		int max_marks = 0, time_alloted = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery("select student_name from student where student_id = " + sid);
			while (rs1.next()) {
				lstuName = rs1.getString(1);				
			}
			ResultSet rs2 = statement.executeQuery("select title, max_marks, time from exam where exam_id = " + exid);
			while (rs2.next()) {
				lExTitle = rs2.getString(1);
				max_marks = rs2.getInt(2);
				time_alloted = rs2.getInt(3);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//==
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		this.setUndecorated(true);	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, ScreenSize.width, ScreenSize.height-50);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_1.setBackground(new Color(0, 0, 51));
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 70));
		
		
		Image img2 = new ImageIcon(this.getClass().getResource("/Actions-process-stop-icon.png")).getImage();
		ImageIcon icon2 = new ImageIcon(img2);
		btnNewButton = new JButton();
		btnNewButton.setBorder(new LineBorder(Color.RED, 3));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setText("SUBMIT");
		btnNewButton.setBackground(new Color(0,0,51));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				submit();
			}

			
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setPreferredSize(new Dimension(300, 50));
		panel_1.add(btnNewButton, BorderLayout.SOUTH);
		
		panel_9 = new JPanel();
		panel_9.setBackground(new Color(0,0,51));
		panel_1.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("TIME");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setPreferredSize(new Dimension(300, 50));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNewLabel, BorderLayout.NORTH);
		
		lblTime = new JLabel("0:0");
		lblTime.setBorder(new LineBorder(Color.RED, 3));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.RED);
		lblTime.setPreferredSize(new Dimension(56, 50));
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_9.add(lblTime, BorderLayout.SOUTH);
		
		panel_10 = new JPanel();
		panel_10.setBackground(new Color(0,0,51));
		panel_1.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new GridLayout(0, 1, 0, 10));
		
		label = new JLabel(Long.toString(exid));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		//label.setForeground(Color.CYAN);
		label.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBorder(new TitledBorder(new LineBorder(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"),1), "EXAM ID", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("Tahoma",Font.BOLD,15), Color.CYAN));
		panel_10.add(label);
		
		label_1 = new JLabel(lExTitle);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBorder(new TitledBorder(new LineBorder(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"),1), "EXAM TITLE", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("Tahoma",Font.BOLD,15), Color.CYAN));
		panel_10.add(label_1);
		
		label_2 = new JLabel(lstuName);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBorder(new TitledBorder(new LineBorder(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"),1), "STUDENT NAME", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("Tahoma",Font.BOLD,15), Color.CYAN));
		panel_10.add(label_2);
		
		label_3 = new JLabel(username);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setBorder(new TitledBorder(new LineBorder(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"),1), "STUDENT USERNAME", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("Tahoma",Font.BOLD,15), Color.CYAN));
		panel_10.add(label_3);
		
		label_4 = new JLabel(Integer.toString(max_marks));
		label_4.setBackground(new Color(240,240,240));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4.setBorder(new TitledBorder(new LineBorder(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"),1), "TOTAL MARKS", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("Tahoma",Font.BOLD,15), Color.CYAN));
		panel_10.add(label_4);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 51));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0,0,0), 5));
		scrollPane.setPreferredSize(new Dimension(2, 150));
		panel_3.add(scrollPane, BorderLayout.SOUTH);
		
		txtrQuestionDescription = new JTextArea();
		txtrQuestionDescription.setEnabled(false);
		txtrQuestionDescription.setForeground(new Color(0, 0, 51));
		txtrQuestionDescription.setFont(new Font("Constantia", Font.BOLD, 30));
		txtrQuestionDescription.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		txtrQuestionDescription.setText("QUESTION DESCRIPTION");
		scrollPane.setViewportView(txtrQuestionDescription);
		
		
		
		
		txtQuestionTitle = new JTextField();
		txtQuestionTitle.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		txtQuestionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuestionTitle.setEnabled(false);
		txtQuestionTitle.setFont(new Font("Constantia", Font.BOLD, 30));
		txtQuestionTitle.setForeground(new Color(0, 0, 51));
		txtQuestionTitle.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		txtQuestionTitle.setText("QUESTION TITLE");
		txtQuestionTitle.setPreferredSize(new Dimension(6, 70));
		
		txtQuestionTitle.setColumns(10);
		panel_3.add(txtQuestionTitle, BorderLayout.NORTH);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 6));
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("SINGLE CORRECT / MULTI CORRECT");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setPreferredSize(new Dimension(56, 70));
		lblNewLabel_1.setVisible(false);
		panel_4.add(lblNewLabel_1, BorderLayout.NORTH);
		
		panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(card=new CardLayout(0, 0));
		
		panel_6 = new JPanel();
		panel_5.add(panel_6, "CHECKBOX_PANEL");
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		COption_1 = new JCheckBox("New check box");
		COption_1.addMouseListener(this);
		
		COption_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		COption_1.setForeground(new Color(0, 0, 51));
		COption_1.setFont(new Font("Georgia", Font.BOLD, 30));
		panel_6.add(COption_1);
		
		COption_2 = new JCheckBox("New check box");
		COption_2.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		COption_2.setForeground(new Color(0, 0, 51));
		COption_2.setFont(new Font("Georgia", Font.BOLD, 30));
		panel_6.add(COption_2);
		
		COption_3 = new JCheckBox("New check box");
		COption_3.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		COption_3.setForeground(new Color(0, 0, 51));
		COption_3.setFont(new Font("Georgia", Font.BOLD, 30));
		panel_6.add(COption_3);
		
		COption_4 = new JCheckBox("New check box");
		COption_4.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		COption_4.setForeground(new Color(0, 0, 51));
		COption_4.setFont(new Font("Georgia", Font.BOLD, 30));
		panel_6.add(COption_4);
		
		COption_2.addMouseListener(this);
		COption_3.addMouseListener(this);
		COption_4.addMouseListener(this);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, "RADIO_PANEL");
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		ROption_1 = new JRadioButton("New radio button");
		ROption_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		ROption_1.setFont(new Font("Georgia", Font.BOLD, 30));
		panel_7.add(ROption_1);
		
		ROption_2 = new JRadioButton("New radio button");
		ROption_2.setFont(new Font("Georgia", Font.BOLD, 30));
		panel_7.add(ROption_2);
		
		ROption_3 = new JRadioButton("New radio button");
		ROption_3.setFont(new Font("Georgia", Font.BOLD, 30));
		panel_7.add(ROption_3);
		
		ROption_4 = new JRadioButton("New radio button");
		ROption_4.setFont(new Font("Georgia", Font.BOLD, 30));
		panel_7.add(ROption_4);
		
		ROption_2.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		ROption_3.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		ROption_4.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		
		bg = new ButtonGroup(); 
		bg.add(ROption_1);
		bg.add(ROption_2);
		bg.add(ROption_3);
		bg.add(ROption_4);
		
		ROption_1.addMouseListener(this);
		ROption_2.addMouseListener(this);
		ROption_3.addMouseListener(this);
		ROption_4.addMouseListener(this);
		
		panel_8 = new JPanel();
		panel_8.setBackground(new Color(0,0,51));
		panel_8.setPreferredSize(new Dimension(10, 70));
		panel_2.add(panel_8, BorderLayout.SOUTH);
		
		Image img1 = new ImageIcon(this.getClass().getResource("/Actions-go-next-icon.png")).getImage();
		img1=img1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon icon1 = new ImageIcon(img1);
		
		Image img3 = new ImageIcon(this.getClass().getResource("/Actions-go-previous-icon.png")).getImage();
		img3=img3.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon icon3 = new ImageIcon(img3);
		
		btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qid++;
				insertQuestion();
			}
		});
		panel_8.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Questions Question=questionsList.get(qid);
				if(Question.type==1)
				{
					boolean[] answers=new boolean[5];
					for(int i=0;i<5;i++)
					{
						answers[i]=false;
					}
					if(ROption_1.isSelected())
					{
						answers[1]=true;
						if(selected.containsKey(cqid))
						{
							selected.replace(cqid, answers);
						}
						else {
							selected.put(cqid, answers);
						}
						
					}
					else if(ROption_2.isSelected())
					{
						answers[2]=true;
						if(selected.containsKey(cqid))
						{
							selected.replace(cqid, answers);
						}
						else {
							selected.put(cqid, answers);
						}
					}
					else if(ROption_3.isSelected())
					{
						answers[3]=true;
						if(selected.containsKey(cqid))
						{
							selected.replace(cqid, answers);
						}
						else {
							selected.put(cqid, answers);
						}
					}
					else
					{
						answers[4]=true;
						if(selected.containsKey(cqid))
						{
							selected.replace(cqid, answers);
						}
						else {
							selected.put(cqid, answers);
						}
					}
				}
				else
				{
					boolean[] answers=new boolean[5];
					for(int i=0;i<5;i++)
					{
						answers[i]=false;
					}
					if(COption_1.isSelected())
					{
						answers[1]=true;
					}
					if(COption_2.isSelected())
					{
						answers[2]=true;
					}
					if(COption_3.isSelected())
					{
						answers[3]=true;
					}
					if(COption_4.isSelected())
					{
						answers[4]=true;
					}
					if(selected.containsKey(cqid))
					{
						selected.replace(cqid, answers);
					}
					else {
						selected.put(cqid, answers);
					}
				}
					
			}
		});
		
		btnPrev = new JButton("");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qid--;
				insertQuestion();
			}
		});
		btnPrev.setBackground(new Color(0,0,51));
		btnPrev.setBorder(null);
		btnPrev.setIcon(icon3);
		panel_8.add(btnPrev);
		btnSave.setBackground(new Color(0,0,51));
		btnSave.setBorderPainted(false);
		btnSave.setBorder(null);
		
		panel_8.add(btnSave);
		btnNext.setBorderPainted(false);
		btnNext.setBackground(new Color(0,0,51));
		btnNext.setBorder(null);
		btnNext.setIcon(icon1);
		panel_8.add(btnNext);
		
		Image img4 = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		img4=img4.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon icon4 = new ImageIcon(img4);
		btnSave.setIcon(icon4);
		
		
		
		//set time here
		final Integer finalTimeAlloted = time_alloted;
		timer=new Timer(1000,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(sec==60)
				{
					sec=0;
					min++;
					if(min==finalTimeAlloted)
					{
						timer.stop();
						submit();
					}
				}
				lblTime.setText(min+":"+sec);
				sec++;
				
			}});
		timer.start();
		
		insertQuestion();
	
		
	}

	private void getQuestionsAndAnswers() 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
			//select * from selected_questions where exam_id = ?"
			PreparedStatement preparedStatement = con.prepareStatement("select * from selected_questions where exam_id = ?");
			preparedStatement.setLong(1, exid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				long curr_qid = rs.getLong("question_id");
				PreparedStatement preparedStatement2 = con.prepareStatement("select * from questions where question_id = ?");
				preparedStatement2.setLong(1, curr_qid);
				ResultSet rs2 = preparedStatement2.executeQuery();
				while(rs2.next()) {
					int currQueType;
					String title, description,option_1,option_2,option_3,option_4;
					currQueType = rs2.getInt("type");
					title = rs2.getString("title");
					description = rs2.getString("description");
					option_1 = rs2.getString("option_1");
					option_2 = rs2.getString("option_2");
					option_3 = rs2.getString("option_3");
					option_4 = rs2.getString("option_4");
					PreparedStatement preparedStatement3 = con.prepareStatement("select * from question_answers where question_id = ?");
					preparedStatement3.setLong(1, curr_qid);
					boolean[] answers = new boolean[5];
					for(int i=0;i<5;i++) {
						answers[i]=false;
					}
					ResultSet rs3 = preparedStatement3.executeQuery();
					while(rs3.next()) {
						answers[rs3.getInt(3)]=true;		
					}
					questionsList.add(new Questions(curr_qid, currQueType, title, description, option_1, option_2, option_3, option_4, answers));				
					
				}
			}
			
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		
	}



	private void insertQuestion() {
		
		
		Questions Question=this.questionsList.get(qid);
		cqid=Question.question_id;
		
		if(qid==0)
		{
			btnPrev.setVisible(false);
		}
		else if(qid==this.questionsList.size()-1)
		{
			btnPrev.setVisible(true);
			btnNext.setVisible(false);
		}
		else
		{
			btnPrev.setVisible(true);
			btnNext.setVisible(true);
		}
		
		if(Question.type==1)
		{
			card.show(panel_5, "RADIO_PANEL");
			this.ROption_1.setText(Question.option_1);
			this.ROption_2.setText(Question.option_2);
			this.ROption_3.setText(Question.option_3);
			this.ROption_4.setText(Question.option_4);
			this.txtrQuestionDescription.setText(Question.description);
			this.txtQuestionTitle.setText(Question.title);
			
			if(selected.containsKey(this.cqid))
			{
				boolean[] value=selected.get(this.cqid);
				
				for(int i=1;i<5;i++)
				{
					if(value[i])
					{
				
						switch(i)
						{
						case 1:ROption_1.setSelected(true);break; 
						case 2:ROption_2.setSelected(true);break;
						case 3:ROption_3.setSelected(true);break;
						case 4:ROption_4.setSelected(true);break;
						}
					}
				}
				
			}
			else
			{
				bg.clearSelection();
			}
			
		}
		else
		{
			card.show(panel_5, "CHECKBOX_PANEL");
			this.COption_1.setText(Question.option_1);
			this.COption_2.setText(Question.option_2);
			this.COption_3.setText(Question.option_3);
			this.COption_4.setText(Question.option_4);
			this.txtrQuestionDescription.setText(Question.description);
			this.txtQuestionTitle.setText(Question.title);
			this.COption_1.setSelected(false);
			this.COption_2.setSelected(false);
			this.COption_3.setSelected(false);
			this.COption_4.setSelected(false);
		
			if(selected.containsKey(this.cqid))
			{
				boolean[] value=selected.get(this.cqid);
				
				for(int i=1;i<5;i++)
				{
					if(value[i])
					{
						switch(i)
						{
						case 1:COption_1.setSelected(true);break; 
						case 2:COption_2.setSelected(true);break;
						case 3:COption_3.setSelected(true);break;
						case 4:COption_4.setSelected(true);break;
						}
					}
				}
			}
			else
			{
				this.COption_1.setSelected(false);
				this.COption_2.setSelected(false);
				this.COption_3.setSelected(false);
				this.COption_4.setSelected(false);
			}
		}
		
		
		
	}



	protected void submit() 
	{
				
		int marks_got = 0,total_marks=this.questionsList.size();
		
		for(Questions q:this.questionsList)
		{
			long id;
			boolean[] ans;
			id=q.question_id;
			ans=q.answer;
			
			
			boolean flag=false;
			if(this.selected.containsKey(id))
			{
				boolean[] myans=this.selected.get(id);
				
				for(int i=0;i<5;i++)
				{
					if(myans[i]==ans[i])
					{
						flag=true;
					}
					else
					{
						flag=false;
						break;
					}
				}
				
			}
			
			if(flag)
			{
				marks_got++;
			}
		}
		
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_exam_system", username, password);
			PreparedStatement preparedStatement = con.prepareStatement("insert into appears(student_id, exam_id, marks_obtained) values (?,?,?)");
			preparedStatement.setLong(1, sid);
			preparedStatement.setLong(2, exid);
			preparedStatement.setInt(3, marks_got);
			preparedStatement.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "successfully submited. Marks scored :" + marks_got + "/" + questionsList.size());
		this.dispose();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource() instanceof JRadioButton)
		{
			((JRadioButton)arg0.getComponent()).setBackground(Color.ORANGE);
		}
		else {
			((JCheckBox)arg0.getComponent()).setBackground(Color.ORANGE);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JRadioButton)
		{
			((JRadioButton)arg0.getComponent()).setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		}
		else {
			((JCheckBox)arg0.getComponent()).setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
