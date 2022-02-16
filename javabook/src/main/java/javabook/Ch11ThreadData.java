package javabook;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.LogManager;
import static javabook.LogUtil.log;

public class Ch11ThreadData{


public static void main(String[]args) throws IOException {

        Data data=new Data();
        Thread sender=new Thread(new Sender(data));
        Thread receiver=new Thread(new Receiver(data));

        sender.start();
        receiver.start();
        }
 }

class Data {
    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    public synchronized void send(String packet) {

        log("send data called on Data: "+packet );
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                log("Thread interrupted" + e);
            }
        }
        transfer = false;

        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        log("Receive called on Data- data :"+ packet);
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                log("Thread interrupted" + e);
            }
        }
        transfer = true;

        notifyAll();
        log("Received data and called Notify but still HOLDING the lock" );

        long currentTime = System.currentTimeMillis();

        //sit  in a tight loop for 5 secs
        while (System.currentTimeMillis() < currentTime + 5000) {
            //do nothing
        }

        log("Received data and called Notify and released the lock");

        return packet;
    }
}

class Sender implements Runnable {

    private Data data;

    // standard constructors

    public Sender(Data data) {
        this.data = data;
    }

    public void run() {
        String packets[] = {
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "End"
        };

        for (String packet : packets) {
            log("Calling send data :" + packet);
            data.send(packet);

            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                log("Thread interrupted"+ e);
            }
        }
    }
}

class Receiver implements Runnable {
    private Data load;

    // standard constructors

    public Receiver (Data data) {
        this.load = data;
    }

    public void run() {
        for(String receivedMessage = load.receive();
            !"End".equals(receivedMessage);
            receivedMessage = load.receive()) {

            log("received message:" + receivedMessage);

            // ...
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log("Thread interrupted"+ e);
            }
        }
    }
}