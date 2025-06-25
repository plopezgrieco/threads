package threads.olds;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class T01 extends JFrame {

	public T01() {
		this.setBounds(15, 15, 800, 600);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("ADIOOOOOSSSSS");
				System.exit(0);
			}
		});
	}
	
	
	public static void main(String[] args) {
		new T01();
		int cont = 0;
		while (true) {
			for (int i = 0; i < 1_000_000; i++); //demora
			System.out.println(cont++);
		}
		
	}
}
