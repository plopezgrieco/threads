package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Th0XPC {

//	private static final Queue<Integer> buffer = new LinkedList<>();
	private static final int CAPACIDAD = 5;
	private static final LinkedBlockingQueue<Integer> buffer = new LinkedBlockingQueue<Integer>(CAPACIDAD);
	private static final int CANT_PROD = 3;
	private static final int CANT_CONS = 7;
//	private static int valor = 0;
	private static AtomicInteger valor = new AtomicInteger(0);
//	private static final Object LOCK = new Object();

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(8);

		Runnable productor = () -> {
			while (true) {
				ThreadUtil.sleep(4000, true);
				int v = valor.getAndIncrement();
				buffer.add(v);
				System.out.println(Thread.currentThread().getName() + " produjo " + v);
				ThreadUtil.sleep();
			}
		};

		Runnable consumidor = () -> {
			while (true) {
				ThreadUtil.sleep(4000, true);
				Integer v = null;
				try {
					v = buffer.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " consumió " + v);
				ThreadUtil.sleep();
			}
		};

		for (int i = 1; i <= CANT_PROD; i++) {
			executor.submit(productor);
		}
		for (int i = 1; i <= CANT_CONS; i++) {
			executor.submit(consumidor);
		}
	}
}
