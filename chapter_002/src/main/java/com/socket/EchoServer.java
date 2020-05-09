package com.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    List<String> lines = new ArrayList<>();
                    String str = "";
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        lines.add(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    if (lines.size() > 0 && lines.get(0).contains("?msg=Bye")) {
                        break;
                    }
                }
            }
        }
    }
}
