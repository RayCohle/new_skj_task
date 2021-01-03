package com.company;

import java.net.ServerSocket;
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
}
