package org.home.hone.thread;

import static java.lang.System.out;

public class Deadlock {
    static class ResourceA {

        private ResourceB friend;

        public ResourceA(ResourceB buddy) {
            this.friend = buddy;
        }

        public synchronized void acquire() {
            out.printf(
                "Thread %s acquired Resource A, trying to get Resource B...\n",
                Thread.currentThread().getName()
            );
            try {
                // slow down to make competitor get the other resources
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.friend.hold();
        }

        public synchronized void hold() {
            out.printf(
                "Thread %s hold Resource A\n",
                Thread.currentThread().getName()
            );
        }

    }

    static class ResourceB {
        private ResourceA friend;

        public synchronized void acquire() {
            out.printf(
                "Thread %s acquired Resource B, trying to get Resource A...\n",
                Thread.currentThread().getName()
            );
            this.friend.hold();
        }

        public synchronized void hold() {
            out.printf(
                "Thread %s hold Resource B\n",
                Thread.currentThread().getName()
            );
        }

        public void setFriend(ResourceA friend) {
            this.friend = friend;
        }
    }

    public static void main(String[] args) {

        ResourceB resourceB = new ResourceB();
        ResourceA resourceA = new ResourceA(resourceB);
        resourceB.setFriend(resourceA);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                resourceA.acquire();
                out.printf(
                    "Thread %s completes execution!\n",
                    Thread.currentThread().getName()
                );
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                resourceB.acquire();
                out.printf(
                    "Thread %s completes execution!\n",
                    Thread.currentThread().getName()
                );
            }
        });
        thread1.start();
//        try {
//            Thread.sleep(1000);
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        thread2.start();
    }
}
