
/**
 create table student
 (Name varchar2(250),
  Branch varchar2(250),
  MobileNumber number(10) primary key,
  Gender varchar2(250)); 
 */


package profile;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StudentProfile extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField branch;
	private JTextField mob;
	private JTextField gender;
	private JButton btnNewButton;
	int k;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					StudentProfile frame = new StudentProfile();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}


	public StudentProfile() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 550, 500);	//x axis, y axis, width, height 
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewStudentRegister = new JLabel("New Student Register");
		lblNewStudentRegister.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblNewStudentRegister.setBounds(110, 52, 325, 50);//x axis, y axis, width, height 
		contentPane.add(lblNewStudentRegister);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(58, 150, 99,43);//x axis, y axis, width, height 
		contentPane.add(lblName);

		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		name.setBounds(150, 160, 150, 25);
		contentPane.add(name);
		name.setColumns(10);

		JLabel lblNewLabel = new JLabel("Branch");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(58, 202, 110, 29);
		contentPane.add(lblNewLabel);

		branch = new JTextField();
		branch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		branch.setBounds(150, 205, 150, 25);
		contentPane.add(branch);
		branch.setColumns(10);

		JLabel lblEmailAddress = new JLabel("Mobile\r\n No");
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmailAddress.setBounds(58, 250, 124, 36);
		contentPane.add(lblEmailAddress);

		mob = new JTextField();
		mob.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mob.setBounds(150, 255, 150, 25);
		contentPane.add(mob);
		mob.setColumns(10);

		JLabel lblUsername = new JLabel("Gender");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(58, 305, 110, 29);
		contentPane.add(lblUsername);

		gender = new JTextField();
		gender.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gender.setBounds(150, 305, 150, 25);
		contentPane.add(gender);
		gender.setColumns(10);





		btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
					

					String name1 = name.getText();
					String branch1 = branch.getText();
					String mob1 = mob.getText();
					String gender1 = gender.getText();

					int len = mob1.length();

					String msg = "" + name1;
					msg += " \n";
					if (len != 10||len==0) 
					{
						JOptionPane.showMessageDialog(btnNewButton, "Enter a valid mobile number");
					}

					try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");	//ClassNotFoundException
						Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@BHASKAR:1521:xe","bhaskar","tumram");	//SQLException

						//					String query = "insert into student values('" + name1 + "','" + branch1 + "','" + mob1 + "','" +gender1 + "')";

						PreparedStatement ps=connection.prepareStatement("insert into student values(?,?,?,?)");
						ps.setString(1,name1);
						ps.setString(2,branch1);
						ps.setString(3,mob1);
						ps.setString(4,gender1);
						
						if(name1.length()==0||branch1.length()==0||gender1.length()==0)
						{
							JOptionPane.showMessageDialog(btnNewButton, "Invalid records");
						}
						else
						{
							k=ps.executeUpdate();

							if(k==0)
							{
								JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
							} 
							else
							{
								JOptionPane.showMessageDialog(btnNewButton,	"Welcome, " + msg + "Your account is sucessfully created");
							}
						}
						connection.close();
					} 
					catch (Exception exception) 
					{
						exception.printStackTrace();
					}
				}
			
		});

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(170, 360, 90, 25);
		contentPane.add(btnNewButton);
	}
}





