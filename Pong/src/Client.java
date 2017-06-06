import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Bikramjit Saini
 * Date: 6/6/2017
 * Desc: Client class
 */
public class Client {
	public static void main (String[] args) throws IOException {
		// Creating the variable for the hostname
		String serverHostName = "localhost";

		System.out.println ("Attempting to connect to host " + serverHostName + " on port 8080");

		// Creating the socket and input and outputs
		Socket serverSocket = null;
		PrintWriter output = null;
		BufferedReader input = null;

		try {
			serverSocket = new Socket(serverHostName, 8080);
			output = new PrintWriter (serverSocket.getOutputStream (), true);
			input = new BufferedReader (new InputStreamReader (serverSocket.getInputStream ()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverHostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + serverHostName);
			System.exit(1);
		}

		// Creating the BufferedReader for the console and a String for the userInput
		BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
		String userInput;

		System.out.println ("Input: ");
		while ((userInput = console.readLine ()) != null) {
			output.println (userInput);
			System.out.println ("Server: " + input.readLine ());
			System.out.println ("Input: ");
		}

		// Closing
		output.close();
		input.close();
		console.close();
		serverSocket.close();
	}

}
