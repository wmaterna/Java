package com.sterta;


public class HeapApp {

    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("Ala", 3);
        queue.add("Jan",1);
        queue.add("Adam1",5);
        queue.add("Antoni0", 1);
        queue.add("Antoni1", 1);
        queue.add("Antoni2", 1);
        queue.add("Antoni3", 1);
        queue.add("Antoni4", 1);
        queue.add("Adam2",5);


        while(queue.size()>0)
        {
            System.out.println(queue);
            queue.get();

        }
    }


}