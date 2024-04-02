import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class GUI_receiver_UDP implements ActionListener
{
	JFrame f;
	JButton b1;
	JTextArea ta;
	GUI_receiver_UDP()
	{
		f=new JFrame("UDP_Receiver");
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
				DatagramSocket ds=new DatagramSocket(4000);
				byte b[]=new byte[100];

				DatagramPacket dp=new DatagramPacket(b,0,100);
				ds.receive(dp);
				ta.setText(new String(dp.getData()));
				System.out.println(new String(dp.getData()));
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
class UDP_Receiver
{
	public static void main(String args[])
	{
		GUI_receiver_UDP g=new GUI_receiver_UDP();
	}
}