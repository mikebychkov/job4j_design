package com.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer2 {
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
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (lines.size() == 0) {
                        continue;
                    }
                    String[] sarg = lines.get(0).split(" ");
                    String arg = "";
                    for (String sa : sarg) {
                        if (sa.contains("/?msg=")) {
                            arg = sa.replace("/?msg=", "");
                            break;
                        }
                    }
                    if (arg.equals("Hello")) {
                        out.write("Hello dear friend!\r\n\r\n".getBytes());
                        continue;
                    }
                    if (arg.equals("Exit")) {
                        break;
                    }
                    if (!arg.isEmpty()) {
                        String msg = arg + "\r\n\r\n";
                        out.write(msg.getBytes());
                    }
                }
            }
        }
    }
}
