package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UdpServer {
    ArrayList<Integer> combination = new ArrayList<Integer>();
    Box box;
    UdpServer(ArrayList<Integer> combination)
    {
        box = new Box(combination);
        this.combination = combination.stream().distinct().collect(Collectors.toCollection(ArrayList::new));

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
                        System.out.println(packet.getAddress());
                        InetAddress ar = packet.getAddress();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(socket.getLocalPort());
                                System.out.println(ar);
                                if(box.write(ar,socket.getLocalPort())){
                                    System.out.println("TCP starts");
                                    TcpSocket tsocket = new TcpSocket();
                                    int r = tsocket.initialize();
                                    try{
                                        System.out.println(r);
                                    socket.send(new DatagramPacket(String.valueOf(r).getBytes(),256,packet.getSocketAddress()));
                                    tsocket.listen();
                                    }
                                    catch (Exception ex){
                                        System.err.println(ex.getMessage());
                                    }
                                }
                            }
                        }).start();
                    }

                }catch (Exception ex){
                    System.exit(0);
                }
             }
         }).start();
        }
    }
}
