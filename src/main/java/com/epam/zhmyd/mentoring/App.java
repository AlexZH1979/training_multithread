package com.epam.zhmyd.mentoring;


public class App 
{
    public static void main( String[] args )
    {
        MessageBus messageBus = new MessageBus(20);
        for (int i = 0; i < 5; i++) {
            Producer producer = new Producer(i,messageBus);
            Thread threadProducer = new Thread(producer);
            threadProducer.start();

            Consumer consumer = new Consumer(i,messageBus);
            Thread threadConsumer = new Thread(consumer);
            threadConsumer.start();
        }
    }
}
