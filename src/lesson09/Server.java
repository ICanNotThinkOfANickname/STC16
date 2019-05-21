package lesson09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private List<Connection> connections =
            Collections.synchronizedList(new ArrayList<Connection>());
    private ServerSocket server;


    public Server() {
        try {
            server = new ServerSocket(Settings.Port);
            while (true) {
                Socket socket = server.accept();
                Connection con = new Connection(socket);
                connections.add(con);
                con.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }


    private void closeAll() {
        try {
            server.close();
            synchronized (connections) {
                Iterator<Connection> iter = connections.iterator();
                while (iter.hasNext()) {
                    ((Connection) iter.next()).close();
                }
            }
        } catch (Exception e) {
            System.err.println();
        }
    }

    private class Connection extends Thread {
        private final Socket socket;
        Scanner scanner = new Scanner(System.in);
        BufferedReader input;
        PrintWriter output;
        private String name = "";

        public Connection(Socket socket) {
            this.socket = socket;
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
                close();
            }
        }

        public void run() {
            try {
                name = input.readLine();
                synchronized (connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while (iter.hasNext()) {
                        ((Connection) iter.next()).output.println(name + " enters the chat");
                    }
                }

                String string = "";
                while (true) {
                    string = input.readLine();
                    if (string.equals("exit")) break;
                    synchronized (connections) {
                        Iterator<Connection> iter = connections.iterator();
                        while (iter.hasNext()) {
                            ((Connection) iter.next()).output.println(name + ": " + string);
                        }
                    }
                }

                synchronized (connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while (iter.hasNext()) {
                        ((Connection) iter.next()).output.println(name + " has left");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        public void close() {
            try {
                input.close();
                output.close();
                socket.close();
                connections.remove(this);
                if (connections.size() == 0) {
                    Server.this.closeAll();
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println();
            }
        }
    }
}
