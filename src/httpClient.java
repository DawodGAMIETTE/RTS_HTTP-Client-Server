import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class httpClient {
    String protocol, host, filename;
    int port;

    public static void main(String[] args) throws IOException, InterruptedException {
        httpClient myClient = new httpClient();
        myClient.readURL(args[0]);
        myClient.getURL();
    }

    public void readURL(String in) throws MalformedURLException {
        URL url = new URL(in);
        protocol = url.getProtocol();
        host = url.getHost();
        filename = "/rfc.txt";
        port = 80;
    }

    public void getURL() throws IOException, InterruptedException {
        Socket socket = new Socket(host, port);
        System.out.println("Connected to " + host + " on port " + port);
        if (socket.isConnected() && !socket.isClosed()) {
            Thread.sleep(5000);
            System.out.println("Waiting for the client request");

            // Send the HTTP GET command to the web server
            PrintWriter to = new PrintWriter(socket.getOutputStream(), true);
            to.println("GET " + filename + " HTTP/1.1");
            to.println("Host: " + host);
            to.println("User-Agent: @DawodGAMIETTE");

            // Create the output file locally
            OutputStream fileOutputStream = new FileOutputStream(filename);

            // Read the server response from the socket and write it to the file
            InputStream from = socket.getInputStream();
            byte[] buf = new byte[4096];
            int bytes_read;
            while ((bytes_read = from.read(buf)) != -1) {
                fileOutputStream.write(buf, 0, bytes_read);
            }

            from.close();
            fileOutputStream.close();
            to.close();
            socket.close();
        } else {
            System.out.println("Socket is not open.");
        }
    }
}