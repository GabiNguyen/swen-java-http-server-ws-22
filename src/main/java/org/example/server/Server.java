package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /*RequestBuilder requestBuilder = new RequestBuilder();
    public String handle(String request) {
        request1 = requestBuilder.build(request);
        return "";
    }*/
    public static final int PORT = 10001;
    private ServerSocket server;
    private final Application application;

    public void start() throws IOException {
        System.out.println("Sever started...");
        server = new ServerSocket(PORT, 5);
        System.out.println("Server running at: http://localhost:" + PORT);
    }

    public Server(Application application) {
        this.application = application;
    }

    public String handle(String requestString) {
        Request request = RequestBuilder.build(requestString);
        Response response = this.application.handle(request);
        return response.toString();
    }

    private void run() {
        while (true) {
            try {
                Socket socket = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                StringBuilder builder = new StringBuilder();

                String inputLine;
                // read until we reach an empty line
                while ((inputLine = in.readLine()) != null && !inputLine.equals("")) {
                    builder.append(inputLine).append(System.lineSeparator());
                }

                String methodPathProtocolAndHeaders = builder.toString();

                int contentLength = methodPathProtocolAndHeaders.length();
                char[] content = new char[contentLength];
                //int read = in.read(content, 0, content.length);

                String content = new String(content);

                //out.write(response.toString());
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
                //throw new RuntimeException(e);
            }
        }
    }
}
