import java.rmi.*;
import java.rmi.server.*;
public class Remoteregister 
{
	public static void main(String args[])
	{
		try
		{
		Hello_interface x=new Hello_impl();
		Naming.rebind("rmi://localhost:1099/niv",x);
		System.out.println("Registered successfully");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}