package Network;



import Model.Location;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer {
    public static Location location;

    private static ArrayList<NetworkInterface> listeners = new ArrayList<>();

    public void add(NetworkInterface listener) {
        listeners.add(listener);
    }

    public void remove(NetworkInterface listener) {
        listeners.remove(listener);
    }

    private static void notifyListeners() {
        for (NetworkInterface listener : listeners) {
            listener.received(location);
        }
    }

    public static void receive() throws Exception {
         int cTosPortNumber = 1777;

         ServerSocket servSocket = new ServerSocket(cTosPortNumber);
         System.out.println("Waiting for a connection on " + cTosPortNumber);

         Socket fromClientSocket = servSocket.accept();

         ObjectInputStream is = new ObjectInputStream(fromClientSocket.getInputStream());
         location = (Location) is.readObject();
         notifyListeners();

         is.close();
        fromClientSocket.close();
    }
}
