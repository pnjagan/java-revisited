package javabook;

import java.util.ArrayList;

import static javabook.Utils.*;


public class Ch11Threads {
    public static void main(String a[]) throws InterruptedException {
//        var myTh = Thread.currentThread();
//        log("About to sleep" + getTime());
//        Thread.sleep(2000);
//
//        Thread.
//        log("Woke up" +getTime());

        SharedData sd = new SharedData();

        Thread t1 = new Thread(()->{
            Integer num = sd.popItem();
            while(true) {
                if(num !=null) {
                    for (int i = 0; i < num; i++) log("Printing for :" + num + " seq :" + i);
                    num = sd.popItem();
                }
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0 ; i< 3; i++) {
              sd.pushItem( Double.valueOf(Math.random()*10).intValue());
                try{
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2.start();
        t1.start();



    }
}

class SharedData {
    ArrayList<Integer> list = new ArrayList<Integer>();

    synchronized public Integer popItem() {
        Integer ret = null;

        if(list.size()  == 0 ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ret = list.get(list.size()-1);
        list.remove(list.size()-1);
        return ret;
    }

    synchronized public void pushItem(Integer i) {
      int pos = list.size();
      list.add(pos, i);
      notify();
    }
}

