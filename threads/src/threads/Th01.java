package threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Th01 {
	public static void main(String[] args) {

		// m1() hilos desincronizados
//		Thread th1 = new Thread(()-> {
//			while(true) {
//				ThreadUtil.sleep();
//				m1();
//			}
//		});
//		Thread th2 = new Thread(()-> {
//			while(true) {
//				ThreadUtil.sleep();
//				m1();
//			}
//		});

		// m2() método synchronized
//		Thread th1 = new Thread(()-> {
//			while(true) {
//				ThreadUtil.sleep();
//				m2();
//			}
//		});
//		Thread th2 = new Thread(()-> {
//			while(true) {
//				ThreadUtil.sleep();
//				m2();
//			}
//		});

		// m3() block synchronized con object como monitor(candado)
//		Object lock = new Object(); 
//		Thread th1 = new Thread(()-> {
//			while(true) {
//				ThreadUtil.sleep();
//				m3(lock);
//			}
//		});
//		Thread th2 = new Thread(()-> {
//			while(true) {
//				ThreadUtil.sleep();
//				m3(lock);
//			}
//		});
		
		Lock lock = new ReentrantLock(true); // si quitamos el true, cualquier puede continuar, así mantiene una cola
		Thread th1 = new Thread(()-> {
			while(true) {
				ThreadUtil.sleep();
				m4(lock);
			}
		});
		Thread th2 = new Thread(()-> {
			while(true) {
				ThreadUtil.sleep();
				m4(lock);
			}
		});
		

		th1.start();
		th2.start();

		System.out.println(Thread.currentThread().getName());

	}

	public static void m1() {
		System.out.println(Thread.currentThread().getName() + " entrando");
		ThreadUtil.sleep();
		System.out.println(Thread.currentThread().getName() + " salidendo");
		System.out.println("-------------");
	}

	public static synchronized void m2() {
		System.out.println(Thread.currentThread().getName() + " entrando");
		ThreadUtil.sleep();
		System.out.println(Thread.currentThread().getName() + " salidendo");
		System.out.println("-------------");
	}

	public static void m3(Object lock) {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + " entrando");
			ThreadUtil.sleep();
			System.out.println(Thread.currentThread().getName() + " salidendo");
			System.out.println("-------------");
		}
	}

	public static void m4(Lock lock) {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " entrando");
		ThreadUtil.sleep();
		System.out.println(Thread.currentThread().getName() + " salidendo");
		System.out.println("-------------");
		lock.unlock();
	}

}
