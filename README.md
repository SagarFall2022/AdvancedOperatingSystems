
# 1. Napster-style Peer-to-Peer File Sharing System

This project implements a Napster-style peer-to-peer (P2P) file sharing system using Java Remote Method Invocation (RMI). The system is designed to facilitate efficient file sharing among peers in a decentralized manner. The main ideas and functionalities of the project are as follows:

1. **Peer Registration and File Indexing:**
   - Peers register themselves with a central indexing server upon joining the network.
   - During registration, peers also provide information about the files they possess and make them available for sharing.
   
2. **File Searching and Peer Selection:**
   - Peers can search for a specific file in the network by querying the central indexing server.
   - The server returns a list of peers that have the requested file.
   - The peer selects one of the peers from the list as the source for downloading the file.

3. **File Downloading and Peer Registration:**
   - Once a peer is selected as the source, the file is downloaded from that peer.
   - After successful download, the downloading peer registers itself with the central indexing server as a peer that possesses the downloaded file.
   
4. **File Deregistration:**
   - If a user deletes a file from their directory, the corresponding registration entry is removed from the central indexing server.
   
5. **Concurrent Peer Operation:**
   - The system supports concurrent operation of multiple peers.
   - Multiple peers can request the server for a list of available peers and download the file from the same peer concurrently.
   
Please refer to the project documentation and code for detailed information on the implementation, setup, and usage instructions.

## Prerequisites
- Java Development Kit (JDK) installed
- Understanding of Java RMI and networking concepts

## How to Run the System
1. Start the central indexing server by running the server program.
2. Start multiple peer instances by running the peer program on different machines or as separate processes on the same machine.
3. Register the peers to the server, index the files, and make them available for sharing.
4. Search for files and initiate downloads from selected peers.
5. Track downloaded files and register as a source for other peers.
6. Optionally, delete files from the directory to deregister them from the server.

# 2. Gneutella Based P2P File Sharing System

This project implements a peer-to-peer (P2P) file sharing system based on the Gneutella protocol using Java Remote Method Invocation (RMI). The main components and functionalities of the system are as follows:

1. Registering Peers and Files:
   - Peers register themselves to a super peer and provide information about the files they are willing to share.
   - The super peer maintains a registry of all registered peers and their shared files.

2. File Search:
   - Users can search for a specific file within the network of super peers.
   - The search query is propagated through the network, and each super peer checks its registry for matching files.
   - A list of peers having the desired file is returned to the user.

3. File Download:
   - After receiving the list of peers having the desired file, the user selects one of them for download.
   - The file is then downloaded from the selected peer.
   - Upon successful download, the peer who downloaded the file is registered with the super peer.

4. File Deregistration:
   - When a user deletes a file from their local directory, the corresponding file entry is removed from the super peer's registry.
   - This ensures that the file is no longer available for search and download by other users.

5. Concurrent Peer Operation:
   - The system supports the concurrent operation of multiple peers.
   - Peers can request the super peer for the list of available peers and download files concurrently from the same peer.



