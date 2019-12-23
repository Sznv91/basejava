package ru.topjava.basejava;

//https://ru.stackoverflow.com/a/660193

public class DeadLock {
    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();

    public static void main(String... args) {
        ThreadDemo1 T1 = new ThreadDemo1(LOCK_1, LOCK_2);
        ThreadDemo2 T2 = new ThreadDemo2(LOCK_1, LOCK_2);
        T1.start();
        T2.start();
    }

    private static class ThreadDemo1 extends Thread {
        private final Object lock1;
        private final Object lock2;

        ThreadDemo1(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        public void run() {
            String threadName = Thread.currentThread().getName();
            synchronized (lock1) {
                System.out.println(threadName + " Holding lock 1...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println(threadName + " Waiting for lock 2...");

                synchronized (lock2) {
                    System.out.println(threadName + " Holding lock 1 & 2...");
                }
            }
        }
    }

    private static class ThreadDemo2 extends Thread {
        private final Object lock1;
        private final Object lock2;

        ThreadDemo2(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        public void run() {
            String threadName = Thread.currentThread().getName();
            synchronized (lock2) {
                System.out.println(threadName + " Holding lock 2...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println(threadName + " Waiting for lock 1...");

                synchronized (lock1) {
                    System.out.println(threadName + " Holding lock 1 & 2...");
                }
            }
        }
    }
}
