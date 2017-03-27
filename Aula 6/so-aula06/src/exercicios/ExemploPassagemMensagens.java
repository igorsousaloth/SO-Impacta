package exercicios;

import java.util.concurrent.*;
import static java.lang.System.out;

public class ExemploPassagemMensagens {
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> mensagens;
		mensagens = new ArrayBlockingQueue<>(3);
		Produtor p = new Produtor(mensagens);
		Consumidor c = new Consumidor(mensagens);
		p.start();
		c.start();
	}
}
class Produtor extends Thread {
	private ArrayBlockingQueue<Integer> msgs;
	Produtor(ArrayBlockingQueue<Integer> m){
		this.msgs = m;
	}
	public void run() {
		try{
			for (int i=0; i<6; i++) {
				Espera.esperar(0.2);
				out.println("produtor: antes");
				msgs.put(i);
				out.println("produtor: após");
			}
		} catch(InterruptedException e) {}
	}
}
class Consumidor extends Thread {
	private ArrayBlockingQueue<Integer> msgs;
	Consumidor(ArrayBlockingQueue<Integer> m){
		this.msgs = m;
	}
	public void run() {
		try{
			for (int i=0; i<6; i++) {
				out.println("consumidor: antes");
				int valor = msgs.take();
				out.println("consumidor: valor=" + valor);
				Espera.esperar(1);
			}
		} catch(InterruptedException e) {}
	}
}

