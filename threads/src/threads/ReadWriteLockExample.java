package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final Lock readLock = rwLock.readLock();
    private static final Lock writeLock = rwLock.writeLock();

    private static final List<String> data = new ArrayList<>();

    public static void main(String[] args) {
        // Hilo de escritura
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
            	ThreadUtil.sleep();
                writeLock.lock();
                try {
                    String value = "Dato " + i;
                    System.out.println("[Escritor] Agregando: " + value);
                    data.add(value);
                    Thread.sleep(500); // Simula trabajo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            }
        });

        // Hilos de lectura
        Runnable readerTask = () -> {
            for (int i = 0; i < 5; i++) {
                readLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " leyendo: " + data);
                    Thread.sleep(300); // Simula lectura
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }
        };

        Thread reader1 = new Thread(readerTask, "Lector-1");
        Thread reader2 = new Thread(readerTask, "Lector-2");

        writer.start();
        reader1.start();
        reader2.start();
    }
}

