import java.rmi.*;
interface Hello_interface extends Remote
{
	String sayHello() throws RemoteException;
	int sum(int a,int b) throws RemoteException;
}