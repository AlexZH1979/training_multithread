package com.epam.zhmyd.mentoring;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageBus {

    private List<String> storage = new LinkedList<String>();
    private int maxSize = 10;

    private Condition read;
    private Condition write;
    private Lock lock;

    public MessageBus(int maxSize) {
        this.maxSize = maxSize;
        this.lock = new ReentrantLock();
        this.read = lock.newCondition();
        this.write = lock.newCondition();
    }

    public void set(String message) {
        lock.lock();
        try {
            while (storage.size() == maxSize) {
                read.await();
            }
            storage.add(message);
            write.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String result = null;
        lock.lock();
        try {
            while (storage.size() == 0) {
               write.await();
            }

            result = storage.remove(0);
            read.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }
}
