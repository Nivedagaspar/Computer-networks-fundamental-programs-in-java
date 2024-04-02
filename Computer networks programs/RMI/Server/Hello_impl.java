import java.rmi.*;
import java.rmi.server.*;
class Hello_impl extends UnicastRemoteObject implements Hello_interface
{
	Hello_impl() throws RemoteException
	{
		super();
	}
	public String sayHello() throws RemoteException
	{
		return "Hellooooo";
	}
	public int sum(int a,int b) throws RemoteException
	{
		return a+b;
	}
}