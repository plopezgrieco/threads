package threads;

import java.util.Random;

public class ThreadUtil {

	public static void sleep(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void sleep() {
		sleep(new Random().nextLong(2000));
	}
	
	public static void sleep(long milis, boolean random) {
		if (random)
			sleep(new Random().nextLong(milis));
		else
			sleep(milis);
	}
}
