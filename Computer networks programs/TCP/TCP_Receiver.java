import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class GUI_receiver implements ActionListener
{
	JFrame f;
	JButton b1;
	JTextArea ta;
	GUI_receiver()
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
			Socket s=new Socket("localhost",2000);
			/*InputStream sin=s.getInputStream();
			Scanner scnr=new Scanner(sin);
			ta.setText(sc.nextLine());*/
			BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			ta.setText(br.readLine());
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
class TCP_Receiver
{
	public static void main(String args[])
	{
		GUI_receiver g=new GUI_receiver();
	}
}