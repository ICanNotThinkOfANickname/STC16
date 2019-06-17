package lesson09.Client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

class ClientSomething {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String ipAdress;
    private int port;
    private String nickname;
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;

    public ClientSomething(String ipAdress, int port) {
        this.ipAdress = ipAdress;
        this.port = port;
        try {
            this.socket = new Socket(ipAdress, port);
        } catch (IOException e) {
            System.err.println("Ошибка");
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname();
            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            ClientSomething.this.downService();
        }
    }

    private void pressNickname() {
        System.out.print("Введите имя: ");
        try {
            nickname = inputUser.readLine();
            out.write("Добро пожаловать " + nickname + "\n");
            out.flush();
        } catch (IOException ignored) {
        }

    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        }
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("quit")) {
                        ClientSomething.this.downService();
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {
                ClientSomething.this.downService();
            }
        }
    }

    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    time = new Date();
                    dt1 = new SimpleDateFormat("HH:mm:ss");
                    dtime = dt1.format(time);
                    userWord = inputUser.readLine();
                    if (userWord.equals("quit")) {
                        out.write("quit" + "\n");
                        ClientSomething.this.downService();
                        break;
                    } else {
                        out.write("(" + dtime + ") " + nickname + ": " + userWord + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    ClientSomething.this.downService();
                }
            }
        }
    }
}