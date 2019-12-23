package ru.topjava.basejava;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainConcurrency {

    private final static int THREADS_NUMBER = 10000;
    private static int counter;
    private static final Object TARGET_SYNC = new Object();


    public static void main(String... args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", state: " + getState()); // expect RUNNABLE
            }
        };
        System.out.println(thread0.getName() + ", State: " + thread0.getState()); //expect NEW
        thread0.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread1.start();

        System.out.println(thread0.getName() + ", State: " + thread0.getState()); //expect TERMINATED

        MainConcurrency mainConcurrency = new MainConcurrency();
        Date startTime = new Date();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Date stopTime = new Date();
        long timeDifference = stopTime.getTime() - startTime.getTime();
        System.out.println(counter + " value COUNTER| Time RUN: " + timeDifference + " mS");
    }

    private void inc() {
        synchronized (this) {
            counter++;
        }
    }
}