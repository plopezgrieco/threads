package threads.olds;
public class T04Runnable implements Runnable {

	private String mensaje;
	
	public T04Runnable(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void run() {
		while(true)
			System.out.println(this.mensaje);
	}
	
	public static void main(String[] args) {
		T04Runnable h1 = new T04Runnable("Yo soy el primero");
		new Thread(h1).start();
		
		T04Runnable h2 = new T04Runnable("El 2");
		new Thread(h2).start();
		
		T04Runnable h3 = new T04Runnable("Tercero");
		new Thread(h3).start();
		
		while(true)
			System.out.println("yooooooo soyyyyyy el PRINCIPAL!!!!!!");
	}
}
