package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.equals("GET /?msg=Hello HTTP/1.1")) {
                            out.write("\r\nHello".getBytes());
                            break;
                        }
                        else if (str.endsWith("GET /?msg=Exit HTTP/1.1")) {
                            server.close();
                        }
                        else {
                            out.write("\r\nWhat".getBytes());
                            break;
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}