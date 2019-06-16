package lesson09.Client;

public class Client {

    public static String IPAdress = "localhost";
    public static int PORT = 8080;

    public static void main(String[] args) {
        new ClientSomething(IPAdress, PORT);
    }
}