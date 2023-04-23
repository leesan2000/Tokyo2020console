package data;

import java.util.Iterator;
import java.util.LinkedList;

import conexiones.servidor.Servidor;

public class Volatil {
	
	/* Almacena una lista de todos los deportistas que son cargados a la base de 
	 * datos en una ejecucion del programa, esto es usado para hacer la conexion
	 * entre los deportistas cargados a la base de datos y las disciplinas en las 
	 * cuales participan.
	 * */
	private LinkedList<Deportista> listaDeportistasEjecucion = null;
	
	/* Almacena las disciplinas recuperadas desde la base de datos. Debido a que 
	 * estas no se modifican, lo mas optimo en vez de hacer una peticion a la DB
	 * cada vez que se necesite es almacenar una variable local con su contenido en forma
	 * de lista. El id es representado por su posicion en la lista.
	 * */
	private static LinkedList<Disciplina> listaDisciplinas = null;
	
	/*Almacena objetos AtletaEnDisciplina, en los cuales se guarda el ID del deportista junto al ID de la disciplina
	 * en la que participa.
	 */
	private static LinkedList<AtletaEnDisciplina> listaAsociacion = null;
	
	public Volatil(Servidor servidor) {
		this.setListaDisciplinas(servidor.obtenerListaDisciplinas());
		if (listaDisciplinas == null) {
			System.out.println("Fallo al cargar lista disciplinas.");	
		}
	}
	
	public static LinkedList<AtletaEnDisciplina> getListaAsociacion(){
		return listaAsociacion;
	}
	
	public static void setListaAsociacion(LinkedList<AtletaEnDisciplina> l) {
		listaAsociacion = l;
	}
	public static void addListaAsociacion(LinkedList<AtletaEnDisciplina> l) { 
		if (l != null) {
			Iterator<AtletaEnDisciplina> iterador = l.iterator();
			while (iterador.hasNext()) { 
				listaAsociacion.addLast(iterador.next());
			} 
		} 
	} 
	
	public LinkedList<Deportista> getListaDeportistasEjecucion() {
		return listaDeportistasEjecucion;
	}
	public void setListaDeportistasEjecucion(LinkedList<Deportista> listaDeportistasEjecucion) {
		this.listaDeportistasEjecucion = listaDeportistasEjecucion;
	}
	public void addDeportistasEjecucion(Deportista deportista) {
		this.listaDeportistasEjecucion.addLast(deportista);
	}
	public static LinkedList<Disciplina> getListaDisciplinas() {
		return listaDisciplinas;
	}
	private void setListaDisciplinas(LinkedList<Disciplina> listaDisciplinas) {
		Volatil.listaDisciplinas = listaDisciplinas;
	}
	

	public void mostrarListaDisciplinas() {
		if (Volatil.listaDisciplinas == null) {
			System.out.println("Lista vacia.");
		}
		else {
			
		}
	}	
}
