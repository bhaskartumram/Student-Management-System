package operations;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CurdOperation extends JFrame
{
	private JButton readButton;
	private JButton writeButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JTable jt1;
	private JLabel l;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					CurdOperation frame = new CurdOperation();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public CurdOperation()
	{

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 150, 1100, 750);
		setResizable(false);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel CurdOperation = new JLabel("CURD Oprerations");
		CurdOperation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CurdOperation.setBounds(410, 10, 250, 50);
		contentPane.add(CurdOperation);


		// ----------------read	
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(95, 150, 99,43);//x axis, y axis, width, height 
		contentPane.add(lblName);

		JTextField name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		name.setBounds(150, 160, 150, 25);
		contentPane.add(name);
		name.setColumns(10);			

		JLabel CurdOperation2 = new JLabel("1. Read record by Name");
		CurdOperation2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		CurdOperation2.setBounds(150, 120, 150, 25);
		contentPane.add(CurdOperation2);


		readButton = new JButton("Read Operation");
		readButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String name3 = name.getText();
				try
				{					

					Class.forName("oracle.jdbc.driver.OracleDriver");	//ClassNotFoundException
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@BHASKAR:1521:xe","bhaskar","tumram");	//SQLException

					PreparedStatement ps=connection.prepareStatement("select * from student where name=?");
					ps.setString(1,name3);
					JOptionPane.showMessageDialog(readButton, "Here the records of "+name3);
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
					}

					connection.close();
				}
				catch (Exception exception) 
				{
					exception.printStackTrace();
				}
			}

		});

		readButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readButton.setBounds(150, 200, 150, 20);
		contentPane.add(readButton);	

		//------------------delete

		JLabel deleteLabel = new JLabel("Name");
		deleteLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deleteLabel.setBounds(95, 320, 150, 25);//x axis, y axis, width, height 
		contentPane.add(deleteLabel);

		JTextField deletetext = new JTextField();
		deletetext.setFont(new Font("Tahoma", Font.PLAIN, 12));
		deletetext.setBounds(150, 320, 150, 25);
		contentPane.add(deletetext);
		deletetext.setColumns(10);			


		JLabel CurdOperation1 = new JLabel("2. Delete record by Name");
		CurdOperation1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		CurdOperation1.setBounds(150, 280, 160, 25);
		contentPane.add(CurdOperation1);

		deleteButton = new JButton("Delete Operation");
		deleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String name2 = deletetext.getText();
				try
				{					

					Class.forName("oracle.jdbc.driver.OracleDriver");	//ClassNotFoundException
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@BHASKAR:1521:xe","bhaskar","tumram");	//SQLException

					PreparedStatement ps=connection.prepareStatement("delete from student where name=?");
					ps.setString(1,name2);
					int k=ps.executeUpdate();
					if(k>0)
					{
						JOptionPane.showMessageDialog(deleteButton, "record deleted");
					}
					else
					{
						JOptionPane.showMessageDialog(deleteButton, "Invalid Name");
					}

					connection.close();
				}
				catch (Exception exception) 
				{
					exception.printStackTrace();
				}
			}

		});

		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		deleteButton.setBounds(150, 360, 150, 25);
		contentPane.add(deleteButton);	


		//----------------------------update

		//---------------------fetch		


		JLabel CurdOperation22 = new JLabel("3. Show All records");
		CurdOperation22.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		CurdOperation22.setBounds(560, 90,160,43);
		contentPane.add(CurdOperation22);	


		readButton = new JButton("Show records");
		readButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{					

					Class.forName("oracle.jdbc.driver.OracleDriver");	//ClassNotFoundException
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@BHASKAR:1521:xe","bhaskar","tumram");	//SQLException
					Statement stm=connection.createStatement();
					ResultSet rs=stm.executeQuery("select * from student");
					while(rs.next())
					{
						System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
					}
					connection.close();
				}
				catch (Exception exception) 
				{
					exception.printStackTrace();
				}
			}

		});

		readButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readButton.setBounds(720, 100, 150, 25);
		contentPane.add(readButton);	

		//----------------

		JLabel CurdOperation223 = new JLabel("4. Update");
		CurdOperation223.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		CurdOperation223.setBounds(670, 150,160,43);
		contentPane.add(CurdOperation223);

		//--------------update by name		

		JLabel oldLabel = new JLabel("Student Name");
		oldLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		oldLabel.setBounds(560, 220, 99,43);//x axis, y axis, width, height 
		contentPane.add(oldLabel);

		JTextField oldText1 = new JTextField();
		oldText1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oldText1.setBounds(670, 230, 150, 25);
		contentPane.add(oldText1);
		oldText1.setColumns(10);


		JLabel newLabel = new JLabel("New Name");
		newLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newLabel.setBounds(560, 260, 99,43);//x axis, y axis, width, height 
		contentPane.add(newLabel);

		JTextField newText1 = new JTextField();
		newText1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		newText1.setBounds(670, 270, 150, 25);
		contentPane.add(newText1);
		newText1.setColumns(10);



		JLabel CurdOperation222 = new JLabel("Enter new Name");
		CurdOperation222.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		CurdOperation222.setBounds(665, 200, 150, 25);
		contentPane.add(CurdOperation222);	


		readButton = new JButton("Name update");
		readButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String oldName = oldText1.getText();
				String newName = newText1.getText();
				try
				{					

					Class.forName("oracle.jdbc.driver.OracleDriver");	//ClassNotFoundException
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@BHASKAR:1521:xe","bhaskar","tumram");	//SQLException

					PreparedStatement ps=connection.prepareStatement("update student set name=? where name=?");
					ps.setString(1,newName);
					ps.setString(2,oldName);
					int k=ps.executeUpdate();
					{
						if(k>0)
						{
							JOptionPane.showMessageDialog(readButton, "Name successfully updated");
						}
						else
						{
							JOptionPane.showMessageDialog(readButton, "Invalid Name");
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

		readButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readButton.setBounds(860, 250, 150, 25);
		contentPane.add(readButton);

		//---------------------------branch

		JLabel snameLabel1 = new JLabel("Student Name");
		snameLabel1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		snameLabel1.setBounds(560, 350, 99,43);//x axis, y axis, width, height 
		contentPane.add(snameLabel1);

		JTextField sNText1 = new JTextField();
		sNText1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sNText1.setBounds(670, 360, 150, 25);
		contentPane.add(sNText1);
		sNText1.setColumns(10);


		JLabel newBranch = new JLabel("New Branch");
		newBranch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newBranch.setBounds(560, 390, 99,43);//x axis, y axis, width, height 
		contentPane.add(newBranch);

		JTextField newBText1 = new JTextField();
		newBText1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		newBText1.setBounds(670, 400, 150, 25);
		contentPane.add(newBText1);
		newBText1.setColumns(10);



		JLabel CurdOperation2222 = new JLabel("Enter new Branch");
		CurdOperation2222.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		CurdOperation2222.setBounds(665, 330, 150, 25);
		contentPane.add(CurdOperation2222);	


		readButton = new JButton("Branch update");
		readButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String sNText = sNText1.getText();
				String newBName = newBText1.getText();
				try
				{					

					Class.forName("oracle.jdbc.driver.OracleDriver");	//ClassNotFoundException
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@BHASKAR:1521:xe","bhaskar","tumram");	//SQLException

					PreparedStatement ps=connection.prepareStatement("update student set Branch=? where Name=?");
					ps.setString(1,newBName);
					ps.setString(2,sNText);
					int k=ps.executeUpdate();
					{
						if(k>0)
						{
							JOptionPane.showMessageDialog(readButton, "Branch successfully updated");
						}
						else
						{
							JOptionPane.showMessageDialog(readButton, "Invalid branch name");
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

		readButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readButton.setBounds(860, 380, 150, 25);
		contentPane.add(readButton);

		//-----------------------------Mobile	


		JLabel snameLabel11 = new JLabel("Student Name");
		snameLabel11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		snameLabel11.setBounds(560, 480, 99,43);//x axis, y axis, width, height 
		contentPane.add(snameLabel11);

		JTextField sNText11 = new JTextField();
		sNText11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sNText11.setBounds(670, 490, 150, 25);
		contentPane.add(sNText11);
		sNText11.setColumns(10);


		JLabel newmob1 = new JLabel("New Mob No.");
		newmob1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newmob1.setBounds(560, 520, 99,43);//x axis, y axis, width, height 
		contentPane.add(newmob1);

		JTextField newMobText1 = new JTextField();
		newMobText1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		newMobText1.setBounds(670, 530, 150, 25);
		contentPane.add(newMobText1);
		newMobText1.setColumns(10);



		JLabel CurdOperation22222 = new JLabel("Enter new Mob Number");
		CurdOperation22222.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		CurdOperation22222.setBounds(665, 460, 150, 25);
		contentPane.add(CurdOperation22222);	


		readButton = new JButton("Mobile Number update");
		readButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String sNText = sNText11.getText();
				String newMobText = newMobText1.getText();
				try
				{					

					Class.forName("oracle.jdbc.driver.OracleDriver");	//ClassNotFoundException
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@BHASKAR:1521:xe","bhaskar","tumram");	//SQLException

					PreparedStatement ps=connection.prepareStatement("update student set mobilenumber=? where Name=?");
					ps.setString(1,newMobText);
					ps.setString(2,sNText);
					int k=ps.executeUpdate();
					{
						if(k>0)
						{
							JOptionPane.showMessageDialog(readButton, "Mobile Number successfully updated");
						}
						else
						{
							JOptionPane.showMessageDialog(readButton, "Invalid Mobile Number");
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

		readButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readButton.setBounds(860, 510, 170, 25);
		contentPane.add(readButton);



		//----------------------gender update


		JLabel snameLabel111 = new JLabel("Student Name");
		snameLabel111.setFont(new Font("Tahoma", Font.PLAIN, 15));
		snameLabel111.setBounds(560, 610, 99,43);//x axis, y axis, width, height 
		contentPane.add(snameLabel111);

		JTextField sNText111 = new JTextField();
		sNText111.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sNText111.setBounds(670, 620, 150, 25);
		contentPane.add(sNText111);
		sNText111.setColumns(10);


		JLabel newg1 = new JLabel("New Gender");
		newg1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newg1.setBounds(560, 650, 99,43);//x axis, y axis, width, height 
		contentPane.add(newg1);

		JTextField newgText1 = new JTextField();
		newgText1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		newgText1.setBounds(670, 660, 150, 25);
		contentPane.add(newgText1);
		newgText1.setColumns(10);



		JLabel CurdOperation222222 = new JLabel("Enter new Gender");
		CurdOperation222222.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		CurdOperation222222.setBounds(665, 590, 150, 25);
		contentPane.add(CurdOperation222222);	


		readButton = new JButton("Gender update");
		readButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String sName1 = sNText111.getText();
				String newgender = newgText1.getText();
				try
				{					

					Class.forName("oracle.jdbc.driver.OracleDriver");	//ClassNotFoundException
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@BHASKAR:1521:xe","bhaskar","tumram");	//SQLException

					PreparedStatement ps=connection.prepareStatement("update student set Gender=? where Name=?");
					ps.setString(1,newgender);
					ps.setString(2,sName1);
					int k=ps.executeUpdate();
					{
						if(k>0)
						{
							JOptionPane.showMessageDialog(readButton, "Gender successfully updated");
						}
						else
						{
							JOptionPane.showMessageDialog(readButton, "Invalid Gender");
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

		readButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readButton.setBounds(860, 640, 170, 25);
		contentPane.add(readButton);
	}
}


