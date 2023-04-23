package utiles;

import java.util.LinkedList;  
import java.util.ListIterator;

public class Utiles {

	/* Imprime lista de deportistas.
	 * 
	 * */
	public static <T> void mostrarLista(LinkedList<T> lista) {
		if ((lista == null) || lista.isEmpty()) {
			System.out.println("Lista vacia.");
		}
		else {
			ListIterator<T> iterador = lista.listIterator();
			Object tmp = null;
			while(iterador.hasNext()) {
				tmp = iterador.next();
				System.out.println((lista.indexOf(tmp)) + " | " + tmp.toString());
			}
		}
	}
	
	public void delay(int a) {
		try {
			Thread.sleep(a);
		} catch (InterruptedException e) {
			System.out.println("Error en Thread.");
			e.printStackTrace();
		}
	}

}
