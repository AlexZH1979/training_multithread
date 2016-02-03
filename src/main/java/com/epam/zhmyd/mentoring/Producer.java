package com.epam.zhmyd.mentoring;

import java.util.Random;

public class Producer implements Runnable{

    private final int id;
    private final MessageBus messageBus;

    public Producer(int id, MessageBus messageBus){
        this.id = id;
        this.messageBus = messageBus;
    }

    public void run() {
        Random random = new Random();
        while (true){
            Integer message = random.nextInt(100);
            messageBus.set(message.toString());
            System.out.println("Producer id="+id+" set message="+message);
        }
    }
}
