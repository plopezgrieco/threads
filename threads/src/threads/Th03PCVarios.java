package threads;

import java.util.LinkedList;
import java.util.Queue;

public class Th03PCVarios {

	private static final Queue<Integer> buffer = new LinkedList<>();
	private static final int CAPACIDAD = 5;
	private static final int CANT_PROD = 3;
	private static final int CANT_CONS = 7;
	private static int valor = 0;
	private static final Object LOCK = new Object();

	public static void main(String[] args) {

		Runnable productor = () -> {
			synchronized (LOCK) {
				while (true) {
					while (buffer.size() == CAPACIDAD)
						try {
							LOCK.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					buffer.offer(valor);
					System.out.println(Thread.currentThread().getName() + " produjo " + valor);
					valor++;
					LOCK.notifyAll();

					// control innecesario
					if (buffer.size() > CAPACIDAD) {
						System.err.println("Error: se produjeron " + buffer.size());
						System.exit(1);
					}
					ThreadUtil.sleep(4000);
				}
			}
		};

		Runnable consumidor = () -> {
			try {
				while (true) {
					synchronized (LOCK) {
						while (buffer.size() == 0)
							LOCK.wait();
						Integer v = buffer.poll();
						System.out.println(Thread.currentThread().getName() + " consumió " + v);
						LOCK.notifyAll();

						// control innecesario
						if (v == null) {
							System.err.println("Error: se ha consumido NULL");
							System.exit(1);
						}
						ThreadUtil.sleep(4000);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		for (int i = 1; i <= CANT_PROD; i++) {
			new Thread(productor).start();
		}
		for (int i = 1; i <= CANT_CONS; i++) {
			new Thread(consumidor).start();
		}
	}

}
