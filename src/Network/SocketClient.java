package Network;

import Model.Location;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {


    public static void send(Location location) throws Exception {
        Socket socket1;
        int portNumber = 1777;


        socket1 = new Socket(InetAddress.getLocalHost(), portNumber);

        ObjectOutputStream os = new ObjectOutputStream(socket1.getOutputStream());

        os.writeObject(location);


        os.close();
        socket1.close();
    }

}