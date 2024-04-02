import java.net.*;
import java.io.*;
import java.util.*;
class TCPSender
{
	public static void main(String args[])
	{
		try
		{
			ServerSocket ss =new ServerSocket(2000);
			Socket s=ss.accept();
			System.out.println("Conneected");
			BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedReader user=new BufferedReader(new InputStreamReader(System.in));
			PrintStream out=new PrintStream(s.getOutputStream());
			System.out.println("Sending 5 packets....");
			String expected="";
			String response="";
			int i;
			for(i=1;i<=5;i++)
			{
				String data="data"+i;
				System.out.println("Sending: "+data);
				out.println(data);
				out.flush();
				Thread.sleep(1000);
				expected="ack"+i;
				response=br.readLine();
				if(expected.compareTo(response)!=0)
				{
					System.out.println("Should Retransmit!!..Ack lost!!");
					break;
				}
				System.out.println("Received Response: "+response);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}