package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Scanner;

public class TcpSocket {
    ServerSocket socket;
    int initialize()
    {
        try{this.socket = new ServerSocket((int) (( Math.random() * (3036-1024) ) + 1024));}
        catch (Exception ex)
        {return initialize();}
        return this.socket.getLocalPort();
    }
    volatile boolean received = false;
    void listen()
    {
        try (Socket socket1 = socket.accept()) {
            System.out.println("connected");
            new Thread(new Runnable() {
                @Override
                public void run() {
                   while(true)
                    if(!socket1.isClosed() && received) {
                        try {
                            socket1.getOutputStream().write((Calendar.getInstance().getTime().toString() + "\n").getBytes());
                        } catch (Exception e) {

                        }finally {
                            received = false;
                        }
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(!socket1.isClosed()) {
                           try (Scanner scanner = new Scanner(socket1.getInputStream())) {
                             while(true)
                                 if(scanner.hasNext()){
                                     System.out.println(scanner.nextLine());
                                     received = true;
                                 }
                           }
                         catch (Exception e) {
                        }
                    }
                }
            }).start();
            Thread.currentThread().sleep(60000);
            System.out.println("user "+ socket1.getInetAddress() + " is disconnected" );
            try {
                socket1.getOutputStream().write(" My(TCP server) death rattle is to put me 15".getBytes());
            } catch (IOException e) {
            }
            socket1.close();
            socket.close();
            System.out.println(socket1.getLocalPort() + " is closed");
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}
