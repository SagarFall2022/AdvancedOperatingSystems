import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.stream.Collector;


@SuppressWarnings("serial")
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    //Array List to store the list of files registered to the server
	ArrayList<FileDetails>Files_Registered;
	
	public ServerImpl() throws RemoteException {
		super();
		Files_Registered= new ArrayList<FileDetails>();
	}

	//Method where Peer can Register a file to server
	@Override
	public synchronized void Register(String peer_id, String File_Name, String Port_No, String Src_Dir) throws RemoteException {
		
		FileDetails data= new FileDetails(peer_id,File_Name,Port_No,Src_Dir);
		
		
		this.Files_Registered.add(data);//Adding the new file with peerID and other info to the Array list 
		
		System.out.println("The File " + data.fileName +" is registered with PeerID "+data.peerId+" with port number "+data.portNo+" and the source directory is  "+data.sourceDir);
		
	}
	
	// Method to Search for the peers a particular file
	@Override
	public ArrayList<FileDetails> Search(String File_Name,int TTL,int ID) throws RemoteException, MalformedURLException, NotBoundException {
		
		ArrayList<FileDetails> Files_Matched= new ArrayList<FileDetails>();// Declaring new Array List to store the info of Peers having the particular file
		
		int n= this.Files_Registered.size();//Getting number of files registered
		int i;
		
		for(i=0;i<n;i++) {
			//Comparing the filename given by the with the list of files registered to the server 
			if( File_Name.equalsIgnoreCase(Files_Registered.get(i).fileName)) {
				
				System.out.println("File is found with Super PEER "+ID);
				Files_Matched.add(Files_Registered.get(i));// Adding the Details of the Peer having file to the Arraylist
			    
			}
			
				
			
			}
		    if(Files_Matched.isEmpty()) { 
		    	//System.out.println("Searching for file among other Super Peers");
			for(int z=1;z<=4;z++) {
				
				if(TTL>0) {
				ServerInterface si = (ServerInterface) Naming.lookup("rmi://localhost:603"+z+"/SP"+z); // Searching for the file among all the super peers
				TTL--;
				//Files_Matched.addAll(si.Search(File_Name,TTL,z));// Searching for a file among all super peers
				Files_Matched.addAll(si.Search(File_Name,TTL,z));
				}
				else {
					break;
				}
			}
		  }
		    
		  
		//Returning the Array list of Peers having the required file
		return Files_Matched;
	}
    //Method to remove registered  file from the peer
	@Override
	public void DeRegister(String peer_id, String File_Name, String Port_No, String Src_Dir) throws RemoteException {
		FileDetails Remove = new FileDetails(peer_id, File_Name,Port_No, Src_Dir);// A object containing the all details to delete a record of registered file from the server
		int m= this.Files_Registered.size()-1;
		//System.out.println("Print m= "+m);
		
		boolean a=false;
		//System.out.println("PEER_ID: "+Remove.peerId);
		//System.out.println("File Name: "+ Remove.fileName);
		for(int i=0;i<=m;i++) {
			
			if( Remove.fileName.equalsIgnoreCase(this.Files_Registered.get(i).fileName)) {
				
				if(Remove.peerId.equalsIgnoreCase(this.Files_Registered.get(i).peerId)){
					
					this.Files_Registered.remove(i);//REmoving the record of the file of particular peer who want to delete-registry a file
					System.out.println("The File "+Remove.fileName+ " has been removed from the Indexing Server with peer_id: "+Remove.peerId );
					a=true;
					break;
				}
				
				
			}
			
		}
		
		if (a==false) {
			System.out.println("Mentioned file is not registered to the Indexing Server");
		}
		
			
		
	}

	@Override
	public ArrayList<FileDetails> SearchLinear(String File_Name, int TTL,int ID)
			throws RemoteException, MalformedURLException, NotBoundException {
ArrayList<FileDetails> Files_Matched= new ArrayList<FileDetails>();// Declaring new Array List to store the info of Peers having the particular file
		
		int n= this.Files_Registered.size();//Getting number of files registered
		int i;
		
		for(i=0;i<n;i++) {
			//Comparing the filename given by the with the list of files registered to the super peer 
			if( File_Name.equalsIgnoreCase(Files_Registered.get(i).fileName)) {
				System.out.println("File is found with Super PEER "+ID);
				Files_Matched.add(Files_Registered.get(i));// Adding the Details of the Peer having file to the Arraylist
			}
			
				
			
			}
		    if(Files_Matched.isEmpty()) { 
		    	System.out.println("Searching for file among other Super Peers");
		    	if(ID>=1 && TTL>0) // Checking if the Super Peer ID is greater than or equal to 1 and TTL 
		    	{
		    		int z= ID-1;
		    		if(z>0) {
		    		TTL--; //Decreasing the TTL
		    		ServerInterface si = (ServerInterface) Naming.lookup("rmi://localhost:603"+z+"/SP"+z);// Connecting to the neighbor Super Peer
		    		Files_Matched.addAll(si.SearchLinear(File_Name, TTL, z)); // Searching for file in neighbor super peer
		    		}
		    		else if(Files_Matched.isEmpty() && ID<4) {
		    			int y=ID+1;
		    			if(y<4 && TTL>0) {
		    				TTL--;
				    		ServerInterface sy = (ServerInterface) Naming.lookup("rmi://localhost:603"+y+"/SP"+y);// Connecting to the neighbor Super Peer
				    		Files_Matched=sy.SearchLinear(File_Name, TTL, y);
		    			}
		    			
		    		}
		    	}
		    }
		//Returning the Array list of Peers having the required file
		return Files_Matched;
	}
	
	

}

