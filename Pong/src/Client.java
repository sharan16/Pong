import java.io.BufferedReader;
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
	public static void main (String[] args) throws IOException, InterruptedException {
		// Creating the variable for the hostname
		String serverHostName = "localhost";
		System.out.println ("Attempting to connect to host " + serverHostName + " on port");

		// Creating the socket and input and outputs
		Socket serverSocket = null;
		PrintWriter output = null;
		BufferedReader input = null;

		try {
			serverSocket = new Socket(serverHostName, 18000);
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
		boolean keepGoing = true;
		
		while (keepGoing == true) {
			while (true) {
				output.println ("hi");
				Thread.sleep (1000);
				System.out.println (input.readLine ());
			}
		}

		// Closing
		output.close();
		input.close();
		console.close();
		serverSocket.close();
	}
}
