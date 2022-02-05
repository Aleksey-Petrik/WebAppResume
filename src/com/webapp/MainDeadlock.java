package com.webapp;

public class MainDeadlock {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        createThread(a, b);
        createThread(b, a);
    }

    private static void createThread(AB obj1, AB obj2) {
        new Thread(() -> {
            synchronized (obj1) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printName(obj1);
                synchronized (obj2) {
                    printName(obj2);
                }
            }
        }).start();
    }

    private static void printName(AB obj) {
        System.out.println(Thread.currentThread().getName() + " " + obj.getName());
    }

    interface AB {
        String getName();
    }

    static class A implements AB {
        public String getName() {
            return "Class A";
        }
    }

    static class B implements AB {
        public String getName() {
            return "Class B";
        }
    }
}
