
package javafxapplication7;

import java.net.ServerSocket;
import java.net.Socket;


public class Server{

	public static void main(String[] args) {

		try{
                    

			ServerSocket server = new ServerSocket(2000);

			
				while(true){
				System.out.println("Waiting for a client");
				Socket socket = server.accept();
				System.out.println("Client connected");
                                System.out.println("runnable");
				ServerRunnable sr = new ServerRunnable(socket);
				sr.start();
                                }
			
			
		}catch (Exception e) {
				
		}

	}

}

