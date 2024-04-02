import java.net.*;
import java.io.*;
import java.util.*;
class receiver
{
  public static void main(String ar[])
  {
	try
	{
		Socket s=new Socket("localhost",3000);
		BufferedReader bf_s=new BufferedReader(new InputStreamReader(s.getInputStream()));
		//BufferedReader bf_cmd=new BufferedReader(new InputStreamReader(System.in));
		PrintStream p=new PrintStream(s.getOutputStream());
		int expectedpkt=0;
		int completed=0;
		while(completed!=1)
		{
			String receivedpkt=bf_s.readLine();
			System.out.println("\nRecieved..."+receivedpkt);
			receivedpkt=receivedpkt.replace("Packet","");
			int rcv_int=Integer.parseInt(receivedpkt);
			if (rcv_int==expectedpkt)
			{
				expectedpkt++;
				String msg="ACK"+String.valueOf(expectedpkt);
				p.println(msg);
				p.flush();
				System.out.println("Recieved expected sent"+msg);
				System.out.println("Delivered..."+receivedpkt);
				Thread.sleep(1000);

			}
			else if (rcv_int>expectedpkt)
			{
				Thread.sleep(1000);
				String msg="NAK"+String.valueOf(expectedpkt);
				p.println(msg);
				p.flush();
				System.out.println("Not recieved expected!! sent"+msg);
				Thread.sleep(1000);

			}
		}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
  }
}