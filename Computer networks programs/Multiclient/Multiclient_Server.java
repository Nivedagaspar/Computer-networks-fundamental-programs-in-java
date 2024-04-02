import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GUI_sender_Multiclient extends WindowAdapter implements ActionListener
{
	JFrame f;
	JButton b1;
	JTextArea ta=new JTextArea(50,50);
	GUI_sender_Multiclient()
	{
		f=new JFrame("Multiclient_Server");
		f.setSize(700,700);
		FlowLayout fl=new FlowLayout();
		f.setLayout(fl);
		//ta=new JTextArea(50,50);
		b1=new JButton("OK");
		b1.addActionListener(this);
		f.add(ta);
		f.add(b1);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		ta.setText("SetText in beginning");
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals("OK"))
		{
			ta.setText("Ok pressed");
			ServerSocket ss=null;
			Socket s=null;
			try
			{
				ta.setText("Inside try");
				ss=new ServerSocket(2000);
				while(true)
				{
					s=ss.accept();
					System.out.println("Connection established...");
					ta.setText("Connected");
					Multi_Serverthread ms=new Multi_Serverthread(s,ta);
					ms.start();
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}

class Multi_Serverthread extends Thread
{
	Socket s=null;
	JTextArea ta=null;
	JFrame f=null;
	Multi_Serverthread(Socket k,JTextArea t,JFrame fr)
	{
		s=k;
		ta=t;
		f=fr;
	}
	public void run()
	{
		try
		{
		//BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		InputStream sin=s.getInputStream();
		Scanner sc=new Scanner(sin);
		PrintStream p=new PrintStream(s.getOutputStream());
		String r_msg=sc.nextLine();
		ta.setText("In run");
		while(!(r_msg.equals("Exit")))
		{
			p.println(r_msg);
			p.flush();
			System.out.println("Msg from client:"+r_msg);
			SwingUtilities.invokeLater(() -> {ta.append("Msg from client: " + r_msg + "\n");});
			r_msg=sc.nextLine();
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
class Multiclient_Server
{
	public static void main(String args[])
	{
		GUI_sender_Multiclient g=new GUI_sender_Multiclient();
	}
}