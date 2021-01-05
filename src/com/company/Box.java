package com.company;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class Box {
    ArrayList<Integer> origin = new ArrayList<Integer>();
    Box(ArrayList<Integer> origin)
    {
        this.origin = origin;
    }
    HashMap<InetAddress, ArrayList<Integer>> map = new HashMap<InetAddress, ArrayList<Integer>>();
    boolean write(InetAddress address, int port)
    {
        if(map.containsKey(address))
        {
            map.get(address).add(port);
            if(map.get(address).size()==origin.size())
            {
                for(int a = 0; a<origin.size(); a++)
                {
                    if(!map.get(address).get(a).equals(origin.get(a)))
                    {
                        map.put(address,new ArrayList<Integer>());
                        return false;
                    }
                }
                map.put(address,new ArrayList<Integer>());
                return true;
            }
            else{
                return false;
            }
        }
        else{
            map.put(address,new ArrayList<Integer>());
            map.get(address).add(port);
        return false;
        }
    }
}
