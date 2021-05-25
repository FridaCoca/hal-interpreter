package io.nayra.halinterpreter;

public class Buffer {
    private boolean available = false;
    private float data;

    public synchronized void put(float x) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data = x;
        available = true;
        notifyAll();
    }

    public synchronized float get() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        notifyAll();
        return data;
    }
}
