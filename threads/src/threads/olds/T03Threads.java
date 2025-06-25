package threads.olds;
public class T03Threads extends Thread {

	private String mensaje;
	
	public T03Threads(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void run() {
		while(true)
			System.out.println(this.mensaje);
	}
	
	public static void main(String[] args) {
		T03Threads h1 = new T03Threads("Yo soy el primero");
		h1.start();
		
		T03Threads h2 = new T03Threads("El 2");
		h2.start();
		
		T03Threads h3 = new T03Threads("Tercero");
		h3.start();
		
		while(true)
			System.out.println("yooooooo soyyyyyy el PRINCIPAL!!!!!!");
	}
}
