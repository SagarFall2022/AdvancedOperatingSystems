import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote{
	
	public void Register(String peer_id,String File_Name,String Port_No,String Src_Dir) throws RemoteException;
	public ArrayList<FileDetails> Search(String File_Name,int TTL,int ID) throws RemoteException, MalformedURLException, NotBoundException;
	public ArrayList<FileDetails> SearchLinear(String File_Name,int TTL,int ID) throws RemoteException,MalformedURLException, NotBoundException;
	public void DeRegister(String peer_id,String File_Name,String Port_No,String Src_Dir) throws RemoteException;

}
