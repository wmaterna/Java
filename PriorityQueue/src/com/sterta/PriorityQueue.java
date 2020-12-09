package com.sterta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class PriorityQueue<T> {

    private ArrayList<QueueElement<T>> items;
    private Map<Integer,Integer> map;


    public PriorityQueue() {
        //List< Structure <T, Integer>> = new ArrayList<>();
        items = new ArrayList<QueueElement<T>>();
        map = new HashMap<>();

    }

    private void siftUp() {
        int k = items.size() - 1;
        while (k > 0) {
            int p = (k-1)/2;
            QueueElement<T> item = items.get(k);
            QueueElement<T> parent = items.get(p);
            if (item.compareTo(parent) > 0) {

                items.set(k, parent);
                items.set(p, item);
                k = p;
            } else {
                break;
            }
        }
    }

    public void add(T item, int priority) { //void add(T t, int priority)
        Integer p = map.get(priority);
        if(p == null){
            map.put(priority,1);
            items.add(new QueueElement<>(item,priority,1));
            siftUp();
        }else{
            map.put(priority,p + 1);
            items.add(new QueueElement<>(item,priority,p+1));
            siftUp();
        }

    }

    private void siftDown() {
        int k = 0;
        int l = 2*k+1;
        while (l < items.size()) {
            int max=l, r=l+1;
            if (r < items.size()) { // there is a right child
                if (items.get(r).compareTo(items.get(l)) > 0) {
                    max++;
                }
            }
            if (items.get(k).compareTo(items.get(max)) < 0) {
                // switch
                QueueElement<T> temp = items.get(k);
                items.set(k, items.get(max));
                items.set(max, temp);
                k = max;
                l = 2*k+1;
            } else {
                break;
            }
        }
    }

    public T get()
            throws NoSuchElementException {
        if (items.size() == 0) {
            throw new NoSuchElementException();
        }
        if (items.size() == 1) {
            return items.remove(0).getValue();
        }
        T hold = items.get(0).getValue();
        items.set(0, items.remove(items.size()-1));
        siftDown();
        return hold;
    }

    public int size() {
        return items.size();
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "items=" + items +
                '}';
    }
}
