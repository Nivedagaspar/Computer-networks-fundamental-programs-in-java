import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class GUI_sender_UDP implements ActionListener
{
	JFrame f;
	JButton b1;
	JTextArea ta;
	GUI_sender_UDP()
	{
		f=new JFrame("TCP_sender");
		f.setSize(700,700);
		FlowLayout fl=new FlowLayout();
		f.setLayout(fl);
		ta=new JTextArea(50,50);
		b1=new JButton("OK");
		b1.addActionListener(this);
		f.add(ta);
		f.add(b1);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals("OK"))
		{
			try
			{
			 	DatagramSocket ds=new DatagramSocket(3000);
				String data=ta.getText();
				DatagramPacket dp=new DatagramPacket(data.getBytes(),data.length(),InetAddress.getByName("localhost"),4000);
				ds.send(dp);
				System.out.println("Successfully sent...");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
class UDP_Sender
{
	public static void main(String args[])
	{
		GUI_sender_UDP g=new GUI_sender_UDP();
	}
}