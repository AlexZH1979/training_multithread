package com.epam.zhmyd.mentoring;

import java.util.LinkedList;
import java.util.Queue;

class MessageBus {

    private Queue<String> storage = new LinkedList<String>();
    private int maxSize =10;

    public MessageBus(int maxSize){
        this.maxSize = maxSize;
    }
    public synchronized void set(String message){
        while (storage.size()==maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(message);
        notifyAll();
    }

    public synchronized String get(){
        while (storage.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String result = storage.poll();
        notifyAll();
        return result;
    }
}
