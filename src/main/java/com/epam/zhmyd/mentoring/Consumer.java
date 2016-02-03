package com.epam.zhmyd.mentoring;

import java.util.Random;

/**
 * Created by Aliaksandr_Zhmaidzia on 2/3/2016.
 */
public class Consumer implements Runnable {
    private final int id;
    private final MessageBus messageBus;

    public Consumer(int id, MessageBus messageBus){
        this.id = id;
        this.messageBus = messageBus;
    }

    public void run() {
        while (true){
            String message = messageBus.get();
            System.out.println("Consumer id="+id+" get message="+message);
        }
    }
}
