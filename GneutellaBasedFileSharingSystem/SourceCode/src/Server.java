import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server implements Runnable{
	
	
	@Override
	public void run() {
		try {
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter the port number for the SuperPeer");
			int p=sc.nextInt();
			sc.nextLine();
			Registry reg=LocateRegistry.createRegistry(p);//Creating a registry for Server on a port
			 ServerInterface si = new ServerImpl();
			 //System.setProperty("java.rmi.server.hostname", "192.168.50.73");
			 System.out.println("Enter the name for the SuperPeer");
			 String name= sc.nextLine();
			 Naming.bind("rmi://localhost:"+p+"/"+name, si);// Binding to specific name  and this is used by client to connect to this server
			 
			 
			 
			System.out.println(name+" is setup successfully");
			
			
			
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

	
	
	public static void main(String [] args) {
		
		new Server().run();// New Thread is created
	}

	
}
