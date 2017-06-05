import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Server {
    public static void main(String args[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);


            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            capitalizedSentence=JOptionPane.showInputDialog("Enter a message");
            //capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
            
       //Thread.sleep(10000);
        //welcomeSocket.close();
    }
}
