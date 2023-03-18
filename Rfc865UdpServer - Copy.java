import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.DatagramPacket;
import java.io.IOException;
import java.net.InetAddress;

public class Rfc865UdpServer {

    static DatagramSocket socket;

    public static void main(String[] argv) {
        //
        // 1. Open UDP socket at well-known port
        //
        
        try {
            String hostname = "";
            InetAddress myAddress = InetAddress.getByName(hostname);
            socket = new DatagramSocket(17, myAddress);
        } 
        catch (SocketException e) {}
        
        while (true) {
            try {
                //
                // 2. Listen for UDP request from client
                //
                byte[] buffer = new byte[256];

                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                
                //
                // 3. Send UDP reply to client
                //
                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();
                String quote = "With great power, comes great responsibility";
                buffer = quote.getBytes();

                DatagramPacket reply = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                System.out.printf("Sending reply to {%s} {%s}", clientAddress, clientPort);
                socket.send(reply);
            } catch (IOException e) {}
        }
    }
}