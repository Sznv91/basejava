package ru.topjava.basejava;

//https://ru.stackoverflow.com/a/660193

public class DeadLock {
    private static final String LOCK_1 = "Lock1";
    private static final String LOCK_2 = "Lock2";

    public static void main(String... args) {
        ThreadDemo T1 = new ThreadDemo(LOCK_1, LOCK_2);
        ThreadDemo T2 = new ThreadDemo(LOCK_2, LOCK_1);
        T1.start();
        T2.start();
    }

    private static class ThreadDemo extends Thread {
        private final Object lock1;
        private final Object lock2;

        ThreadDemo(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        public void run() {
            String threadName = Thread.currentThread().getName();
            synchronized (lock1) {
                System.out.println(threadName + " Holding " + lock1);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println(threadName + " Waiting for " + lock2);

                synchronized (lock2) {
                    System.out.println(threadName + " Holding lock 1 & 2...");
                }
            }
        }
    }

}
