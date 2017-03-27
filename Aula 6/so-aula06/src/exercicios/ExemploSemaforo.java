package exercicios;

import java.util.concurrent.*;
import static java.lang.System.out;

public class ExemploSemaforo {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(6);
		for (int cont=0; cont<6; cont++) {
			Baixador b;
			b = new Baixador(cont, semaforo);
			Thread t = new Thread(b);
			t.start();
			Espera.esperar(0.5);
		}
	}
}
class Espera {
	public static void esperar(double t) {
		try {
			Thread.sleep((int)(t*1000));
		} catch(InterruptedException e) {}
	}
}
class Baixador implements Runnable {
	private int id;
	private Semaphore semaforo;
	public Baixador(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	public void run() {
		try {
			out.println(id +
					": esperando no semáforo");
			semaforo.acquire();
			out.println(id +
					": liberado no semáforo");
			for (int i=0; i<5; i++) {
				Espera.esperar(0.5);
				out.println(id +
						": baixado " +
						i*100/4 + "%");
			}
			semaforo.release();
		} catch(InterruptedException e) {}
	}
}