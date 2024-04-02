import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class GUI_sender_Files implements ActionListener
{
	JFrame f;
	JButton b1,b2;
	JTextArea ta;
	GUI_sender_Files()
	{
		f=new JFrame("TCP_sender");
		f.setSize(700,700);
		FlowLayout fl=new FlowLayout();
		f.setLayout(fl);
		ta=new JTextArea(50,50);
		b1=new JButton("OK");
		b1.addActionListener(this);
		b2=new JButton("Getmsg");
		b2.addActionListener(this);
		f.add(ta);
		f.add(b1);
		f.add(b2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		if (ae.getActionCommand().equals("OK"))
		{
			ServerSocket ss=new ServerSocket(2000);
			System.out.println("server is waiting to provide service...");
			Socket s=ss.accept();
			OutputStream sout= s.getOutputStream();
			String data=ta.getText()+'\n';
			sout.write(data.getBytes());
			System.out.println("Message sent:"+data);
		}
		else if(ae.getActionCommand().equals("Getmsg"))
		{
			Socket s=new Socket("192.168.29.219",3000);
			DataInputStream sin= new DataInputStream(s.getInputStream());
			String dataread="";
			Scanner scnr=new Scanner(sin);
			System.out.println("bfr GOT it");
			while(scnr.hasNextLine())
			{
				//System.out.println("While");
				dataread+=sin.readLine();
			}
			ta.setText(dataread);
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
class TCP_Sender_Files
{
	public static void main(String args[])
	{
		GUI_sender_Files g=new GUI_sender_Files();
	}
}