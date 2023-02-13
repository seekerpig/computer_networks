import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.DatagramPacket;
import java.io.IOException;
import java.net.InetAddress;


public class Rfc865UdpClient {
    static DatagramSocket socket;
    
    

    public static void main(String[] argv) throws UnknownHostException {
        //
        // 1. Open UDP socket
        //
        String hostname = "hwlab1.scse.ntu.edu.sg";
        int port = 17;
        InetAddress address = InetAddress.getByName(hostname);


        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {}
        try {
            //
            // 2. Send UDP request to server
            //
            byte[] buffer = new byte[256];
            String quote = "Soh Zu Wei, A21, ";
            buffer = quote.getBytes();

            DatagramPacket request = new DatagramPacket(buffer, buffer.length , address, port);
            socket.send(request);
            
            //
            // 3. Receive UDP reply from server
            //
            DatagramPacket reply = new DatagramPacket(buffer, 0, request.getLength());
            socket.receive(reply);
            
            System.out.println(new String(buffer, 0, reply.getLength()));
        } catch (IOException e) {}
    }
}