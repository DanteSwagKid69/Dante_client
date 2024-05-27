import java.net.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception{
        // Default port number we are going to use
        int portnumber = 50000;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        // Create a MultitaskSocket
        MulticastSocket chatMultitaskSocket = new MulticastSocket(portnumber);

        // Determine the ip adress of a host, given the host name
        InetAddress group = InetAddress.getByName("225.4.5.6");

        // Joins a multitask group
        chatMultitaskSocket.joinGroup(group);

        // Prompt a user to enter a message
        String msg = "";
        System.out.println("Type a message for the server:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        msg = br.readLine();

        // Send the message to multitask adress
        DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, portnumber);
        chatMultitaskSocket.send(data);

        // Close the socket
        chatMultitaskSocket.close();
    }
}