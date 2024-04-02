import java.net.*;
class Multicast_receiver
{
	public static void main(String are[])
	{
		try
		{
			MulticastSocket socket=new MulticastSocket(4321);
			InetAddress group=InetAddress.getByName("230.1.1.1");			
			socket.joinGroup(group);

			byte[] buffer=new byte[1024];
			DatagramPacket dp=new DatagramPacket(buffer,buffer.length);
			socket.receive(dp);
			String msg=new String(dp.getData());
			System.out.println("\n Received msg :"+msg);
			socket.leaveGroup(group);
			socket.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}