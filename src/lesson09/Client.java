package lesson09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner scanner = new Scanner(System.in);
    BufferedReader input;
    PrintWriter output;

    public Client() {


        try {
            Socket socket = new Socket("127.0.0.1", Settings.Port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            System.out.print("Enter your nickname : ");
            output.println(scanner.nextLine());

            getAndDisplay getAndDisplay = new getAndDisplay();
            getAndDisplay.start();

            String string = "";
            while (!string.equals("quit")) {
                string = scanner.nextLine();
                output.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Получаем сообщения от сервера и выводим в консоль*/
    private class getAndDisplay extends Thread {
        private boolean stop;

        public void setStop() {
            stop = true;
        }

        public void run() {
            try {
                while (!stop) {
                    String string = input.readLine();
                    System.out.println(string);
                }
            } catch (IOException e) {
                System.out.println("Error getting message;");
                e.printStackTrace();
            }
        }
    }
}
