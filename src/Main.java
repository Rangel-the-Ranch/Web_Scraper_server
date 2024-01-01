import server.Server;
public class Main {
    private static final int SERVER_PORT = 8080;
    public static void main(String[] args) {
        Server server = new Server(SERVER_PORT);
        server.start();

        server.stop();
    }
}