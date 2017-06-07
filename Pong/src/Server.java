import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * @author Bikramjit Saini
 * Date: 6/6/2017
 * Desc: Server class
 */
public class Server {
	public static void main (String[] args) throws IOException {
		// Creating the ServerSocket
		ServerSocket serverSocket = null;
		try {
			// Specifying the port
			serverSocket = new ServerSocket (8080);
		}
		catch (IOException e) {
			// Sending an error message
			System.err.println ("Couldn't listen on port: 8080."); 
			System.exit (1); 
		}

		// Creating the client Socket
		Socket clientSocket = null;
		System.out.println ("Waiting for connection...");
		try {
			clientSocket = serverSocket.accept ();
		}
		catch (IOException e) {
			System.err.println("Accept failed."); 
			System.exit(1); 
		}

		// Specifying that server is waiting for input
		System.out.println ("Connection successful");
		System.out.println ("Waiting for input...");

		// Creating inputs and outputs
		PrintWriter output = new PrintWriter (clientSocket.getOutputStream (), true); 
		BufferedReader input = new BufferedReader (new InputStreamReader (clientSocket.getInputStream ())); 
		BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
		String userInput;
		String inputLine;

		while ((inputLine = input.readLine ()) != null) {
			System.out.println ("Client: " + inputLine); 
			System.out.println ("Server Input: ");
			while ((userInput = console.readLine ()) != null) {
				output.println (userInput);
			}
		}

		// Closing
		output.close(); 
		input.close(); 
		clientSocket.close(); 
		serverSocket.close();
	}
}
