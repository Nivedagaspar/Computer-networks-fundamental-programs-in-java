import java.net.*;
import java.util.*;
class Multicast_sender
{
	public static void main(String are[])
	{
		try
		{
			DatagramSocket socket=new DatagramSocket();
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter msg to send:");
			String msg=sc.nextLine();
			DatagramPacket dp=new DatagramPacket(msg.getBytes(),msg.length(),InetAddress.getByName("230.1.1.1"),4321);
			socket.send(dp);
			socket.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}