package com.epam.zhmyd.mentoring;

import java.util.Random;

public class Consumer implements Runnable {
    private final int id;
    private final MessageBus messageBus;

    public Consumer(int id, MessageBus messageBus){
        this.id = id;
        this.messageBus = messageBus;
    }

    public void run() {
        Random random =new Random();
        while (true){
            try {
                Thread.sleep(random.nextInt(150)+50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String message = messageBus.get();
            System.out.println("Consumer id="+id+" get message="+message);
        }
    }
}
