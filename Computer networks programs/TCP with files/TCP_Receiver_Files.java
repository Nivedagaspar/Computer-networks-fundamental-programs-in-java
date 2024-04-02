import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class GUI_receiver_Files implements ActionListener
{
	JFrame f;
	JButton b1;
	JTextArea ta;
	GUI_receiver_Files()
	{
		f=new JFrame("TCP_Receiver");
		f.setSize(700,700);
		FlowLayout fl=new FlowLayout();
		f.setLayout(fl);
		ta=new JTextArea(50,50);
		b1=new JButton("Getmsg");
		b1.addActionListener(this);
		f.add(ta);
		f.add(b1);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals("Getmsg"))
		{
			try
			{
			Socket s=new Socket("192.168.29.219",2000);
			InputStream sin=s.getInputStream();
			Scanner scnr=new Scanner(sin);
			String fname=scnr.nextLine();
			ta.setText(fname);
			FileInputStream fin=new FileInputStream(fname);
			byte b;
			String dataread="";
			while((b=(byte)fin.read())!=-1)
			{
				dataread+=(char)b;
			}
			//sout.write(dataread.getBytes());
			dataread+='\n';

			ServerSocket ss=new ServerSocket(3000);
			System.out.println("receiver is waiting to provide service...");
			Socket s1=ss.accept();
			OutputStream netout=s1.getOutputStream();
			//PrintStream netout=new PrintStream(s.getOutputStream());
			netout.write(dataread.getBytes());
			System.out.println(dataread);
			System.out.println("Data sent:");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
class TCP_Receiver_Files
{
	public static void main(String args[])
	{
		GUI_receiver_Files g=new GUI_receiver_Files();
	}
}