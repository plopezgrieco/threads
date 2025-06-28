package threads;

public class Th02Estados {

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        // Hilo que entra en estado WAITING (espera indefinida)
        Thread primero = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(); // Espera indefinida, hasta que otro hilo llame a notify()
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Primer hilo");

        // Hilo que entra en estado TIMED_WAITING (espera con tiempo)
        Thread segundo = new Thread(() -> {
            try {
                Thread.sleep(5000); // Espera 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Segundo hilo");

        // Iniciar los hilos
        primero.start();
        segundo.start();

        // Dar tiempo para que entren en espera
        Thread.sleep(100); // Pequeña pausa para asegurarnos que los hilos están esperando

        // Mostrar estado de los hilos
        System.out.println(primero.getName() + ": " + primero.getState());
        System.out.println(segundo.getName() + ": " + segundo.getState());
        
        
    }
}

