package com.webapp;

public class MaimConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " " + getState());
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
            }
        }).start();

        System.out.println(thread0.getName() + " " + thread0.getState());

        for (int i = 0; i < 10_000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inc();
                }
            }).start();
        }
        Thread.sleep(500);
        System.out.println(counter);
    }

    /* Синхронизация по методу
    private static synchronized void inc() {
        counter++;
    }*/

    private static synchronized void inc() {
        double result = Math.sin(13.);
        synchronized (LOCK) {
            counter++;
        }
    }
}
