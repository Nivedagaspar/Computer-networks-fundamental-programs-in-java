import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Multiclient_Receiver_GUI extends WindowAdapter implements ActionListener 
{
	JFrame f;
	JButton b1;
	JTextArea ta=new JTextArea(50,50);
	Multiclient_Receiver_GUI()
	{
		f=new JFrame("Multiclient_Receiver");
		f.setSize(700,700);
		FlowLayout fl=new FlowLayout();
		f.setLayout(fl);
		//ta=new JTextField(100);
		b1=new JButton("Sendmsg");
		b1.addActionListener(this);
		f.add(ta);
		f.add(b1);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals("Sendmsg"))
		{
			ta.setText("Pressed");
			try
			{
				ta.setText("Inside try");
				Socket s=new Socket("192.168.29.219",2000);
				BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
				BufferedReader bcmd=new BufferedReader(new InputStreamReader(System.in));
				PrintStream p=new PrintStream(s.getOutputStream());
				String r_msg="",s_msg="";
				ta.setText("Connected");
				while(!(r_msg.equals("Exit")))
				{
					System.out.println("Enter msg to be read:");
					r_msg=bcmd.readLine();
					p.println(r_msg);
					p.flush();
					s_msg=br.readLine();
					System.out.println("Message from server:"+s_msg);
					ta.setText("Got the message");
					ta.setText(ta.getText()+'\n'+"Message from server:"+s_msg);
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
class Multiclient_Receiver
{
	public static void main(String args[])
	{
		Multiclient_Receiver_GUI g=new Multiclient_Receiver_GUI();
	}
}