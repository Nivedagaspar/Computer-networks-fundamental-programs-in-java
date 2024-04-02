import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class GUI_sender implements ActionListener
{
	JFrame f;
	JButton b1;
	JTextArea ta;
	//JTextField tf;
	GUI_sender()
	{
		f=new JFrame("TCP_sender");
		f.setSize(700,700);
		FlowLayout fl=new FlowLayout();
		f.setLayout(fl);
		
		// (or) f.setLayout(null); b1.setBounds(x,y,width,height);

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
			ServerSocket ss=new ServerSocket(2000);
			System.out.println("server is waiting to provide service...");
			Socket s=ss.accept();
			OutputStream sout= s.getOutputStream();
			String data=ta.getText()+'\n';
			sout.write(data.getBytes());
			System.out.println("Message sent:"+data);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
class TCP_Sender
{
	public static void main(String args[])
	{
		GUI_sender g=new GUI_sender();
	}
}