import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class httpClient {
    String protocol, host, filename;
    int port;
    public static void main(String[] args) throws IOException {

        String firstElement = args[0];
        httpClient myClient = new httpClient();
        myClient.readURL(firstElement);
        myClient.getURL();
    }

    public void readURL(String in) throws MalformedURLException {
        URL url = new URL(in);
        protocol = url.getProtocol();
        host = url.getHost();

        filename = "/rfc.txt";
        port = 80;
    }

    public void getURL() throws IOException {
        Socket socket = new Socket(host, port);
        while(true) {
            System.out.println("Waiting for the client request");
            InputStream inputStream = socket.getInputStream();
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
        }
    }
}
