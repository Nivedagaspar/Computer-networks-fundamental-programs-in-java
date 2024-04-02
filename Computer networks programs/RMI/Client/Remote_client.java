import java.rmi.*;
import java.util.*;
class Remote_client
{
	public static void main(String args[])
	{
		try
		{
		Hello_interface y=(Hello_interface)Naming.lookup("rmi://localhost:1099/niv");
		System.out.println(y.sayHello());
		Scanner scnr=new Scanner(System.in);
		System.out.println("Enter a:");
		int a=scnr.nextInt();
		System.out.println("Enter b:");
		int b=scnr.nextInt();
		System.out.println(y.sum(a,b));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}