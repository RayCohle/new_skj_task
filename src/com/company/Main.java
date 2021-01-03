package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
           ArrayList<Integer> list = new ArrayList<Integer>();
           list.add(1678);
            list.add(1678);
           list.add(2798);
           list.add(6784);

           new UdpServer(list).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
