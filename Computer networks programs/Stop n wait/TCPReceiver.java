import java.net.*;
import java.io.*;
import java.util.*;
class TCPReceiver
{
	public static void main(String args[])
	{
		try
		{
			Socket s=new Socket("localhost",2000);
			BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintStream out=new PrintStream(s.getOutputStream());
			int i;
			String msg="";
			for(i=1;i<=5;i++)
			{
				msg=br.readLine();
				int len=msg.length();
				StringBuffer sb=new StringBuffer(msg);
				char acknum=sb.charAt(len-1);
				String response="ack"+acknum;
				System.out.println("Currently sending: "+response);
				out.println(response);
				out.flush();
				Thread.sleep(1000);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}