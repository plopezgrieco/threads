package threads.olds;
public class T02SinHilos {

	private String mensaje;
	
	public T02SinHilos(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void run() {
		while(true)
			System.out.println(this.mensaje);
	}
	
	public static void main(String[] args) {
		T02SinHilos h1 = new T02SinHilos("Yo soy el primero");
		h1.run();
		
		T02SinHilos h2 = new T02SinHilos("El 2");
		h2.run();
		
		T02SinHilos h3 = new T02SinHilos("Tercero");
		h3.run();
	}
}
