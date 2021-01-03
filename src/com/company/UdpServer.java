package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class UdpServer {
    ArrayList<Integer> combination = new ArrayList<Integer>();
    Box box;
    UdpServer(ArrayList<Integer> combination)
    {
        box = new Box(combination);
        this.combination = combination;
    }
    void start()
    {
        for(int a = 0; a<combination.size(); a++)
        {
            int finalA = a;
            new Thread(new Runnable() {
             @Override
             public void run() {
                try{
                    DatagramSocket socket = new DatagramSocket(combination.get(finalA));
                    DatagramPacket packet = new DatagramPacket(new byte[256], 256);
                    while(true){
                        socket.receive(packet);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if(box.write(packet.getAddress(),socket.getLocalPort())){
                                    System.out.println(true);
                                }
                            }
                        }).start();
                    }





                }catch (Exception ex){}
             }
         }).start();
        }
    }
}
