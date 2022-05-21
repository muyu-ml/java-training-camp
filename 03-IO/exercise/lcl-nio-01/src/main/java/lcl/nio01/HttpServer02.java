package lcl.nio01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer02 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true){
            try{
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    service(socket);
                }).start();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private static void service(Socket socket) {
        String body = "hello lcl-nio-8081";
        try{
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.flush();
            printWriter.close();
            socket.close();
        }catch (Exception e){

        }
    }


}
