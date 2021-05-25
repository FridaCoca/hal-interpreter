package io.nayra.halinterpreter;

public class Buffer {
    private boolean available = false;
    private float data;

    public synchronized void put(int x) {
        while(available) {
            try {
                wait();
            }
            catch(InterruptedException e) {}
        }
        data = x;
        available = true;
        notifyAll();
    }

    public synchronized float get() {
        while(!available) {
            try {
                wait();
            }
            catch(InterruptedException e) {}
        }
        available = false;
        notifyAll();
        return data;
    }
}
