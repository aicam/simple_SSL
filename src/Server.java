import java.io.IOException;

public class Server {
    private static network.Server serverNetwork = new network.Server();
    public static void main(String[] args) throws IOException {
        serverNetwork.run();
    }
}
