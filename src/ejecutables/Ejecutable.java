package ejecutables;

import java.util.Scanner;

import conexiones.servidor.Servidor;
import data.Volatil;
import interaccionUsuario.Menu;
import utiles.Utiles;

public class Ejecutable {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Servidor servidor = new Servidor();
		Volatil volatil = new Volatil(servidor);
		Utiles util = new Utiles();
		Menu menu = new Menu();
		menu.menu(in, servidor, volatil, util);
	}
}
