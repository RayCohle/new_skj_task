package com.company;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TcpSocket {
    ServerSocket socket;
    int initialize()
    {
        try{this.socket = new ServerSocket((int) (( Math.random() * (3036-1024) ) + 1024));}
        catch (Exception ex)
        {return initialize();}
        return this.socket.getLocalPort();

    }
    void listen()
    {
        try (Socket socket1 = socket.accept()) {
            System.out.println("connected");
         socket1.getOutputStream().write("Zig Heil \0".getBytes());
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}
