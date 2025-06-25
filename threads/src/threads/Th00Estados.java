package threads;

public class Th00Estados {
	public static void main(String[] args) {
		
		Thread th1 = new Thread();
		System.out.println(th1.getState()); //NEW
		
		th1.start();
		System.out.println(th1.getState()); //RUNNABLE
	
		ThreadUtil.sleep(200);
		System.out.println(th1.getState()); //TERMINATED
		
	}
}
