package com.company;

public class Main {

    public static final Object Lock1 = new Object();
    public static final Object Lock2 = new Object();

    public static void main(String[] args) {

        DeadLockOne lockOne = new DeadLockOne();
        DeadLockTwo lockTwo = new DeadLockTwo();

        lockOne.start();
        lockTwo.start();
    }

    private static class DeadLockOne extends Thread {

        public void run() {
            synchronized (Lock1) {
                System.out.println("DeadLockOne is holding LOCK 1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DeadLockOne is waiting for Lock 2...");
                synchronized (Lock2) {
                    System.out.println("DeadLockOne  is holding Lock 1 and Lock 2...");
                }
            }
        }
    }

    private static class DeadLockTwo extends Thread {

        public void run() {
            synchronized (Lock2) {
                System.out.println("DeadLockTwo is holding LOCK 2...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DeadLockOne is waiting for Lock 1...");
                synchronized (Lock1) {
                    System.out.println("DeadLockOne  is holding Lock 1 and Lock 2...");
                }
            }
        }
    }
}
