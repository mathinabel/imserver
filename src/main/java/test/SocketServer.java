package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;

public class SocketServer {
    ServerSocket ss;

    {
        try {

            ss = new ServerSocket(8019);

            ss.accept();

            System.out.println("有新连接接入"+ss.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static void main(String[] args){
        new SocketServer();
    }
}
