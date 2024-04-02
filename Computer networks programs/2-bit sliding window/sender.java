import java.net.*;
import java.io.*;
import java.util.*;
class sender
{
  public static void main(String ar[])
  {
	try
	{
		ServerSocket ss=new ServerSocket(3000);
		Socket s=ss.accept();
		System.out.println("Connection established");
		BufferedReader bf_s=new BufferedReader(new InputStreamReader(s.getInputStream()));
		//BufferedReader bf_cmd=new BufferedReader(new InputStreamReader(System.in));
		PrintStream p=new PrintStream(s.getOutputStream());
		int N=4;
		int base=0;
		int counter=4;
		int completed=0;
		int expectedAck=1;
		int receivedAck_int;
		String receivedAck;
		int curpkt=0;
		String arr[]={"Packet0","Packet1","Packet2","Packet3","Packet4","Packet5","Packet6","Packet7"};
		while(completed!=1)
		{
			//while (counter>0 && curpkt<=7)
			for (int i=curpkt;i<base+counter;i++)
			{
				p.println(arr[i]);
				p.flush();
				System.out.println("Sending packet..."+String.valueOf(i));
				counter--;
				curpkt++;
			}
			receivedAck=bf_s.readLine();
			if (receivedAck.contains("8"))
			{
				completed=1;
				break;
			}
			if (receivedAck.contains("ACK"))
			{
				System.out.println("Received "+receivedAck);
				receivedAck=receivedAck.replace("ACK","");
				receivedAck_int=Integer.parseInt(receivedAck);
				counter+=receivedAck_int-base;
				base=receivedAck_int;
			}
			else if (receivedAck.contains("NAK"))		
			{
				System.out.println("Received "+receivedAck+"!!");
				receivedAck=receivedAck.replace("NAK","");
				receivedAck_int=Integer.parseInt(receivedAck);
				p.println(arr[receivedAck_int]);
				p.flush();
				curpkt=receivedAck_int+1;
				System.out.println("Retransmitting..."+receivedAck);
				counter+=receivedAck_int-base;
				base=receivedAck_int;
			}
		}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
  }
}