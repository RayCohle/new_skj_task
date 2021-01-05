package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                InetAddress ar = packet.getAddress();
                                if(box.write(ar,socket.getLocalPort())){
                                    System.out.println("TCP starts");
                                    TcpSocket tcpsocket = new TcpSocket();
                                    int port_number = tcpsocket.initialize();
                                    try{
                                        DatagramPacket portPacket = new DatagramPacket(String.valueOf(port_number).getBytes(),
                                                String.valueOf(port_number).getBytes().length,ar,combination.get(finalA));
                                        socket.send(portPacket);
                                    tcpsocket.listen();
                                    }
                                    catch (Exception ex){
                                        System.err.println(1+ " "  + ex.getMessage());
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
