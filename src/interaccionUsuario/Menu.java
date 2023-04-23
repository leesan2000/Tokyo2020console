package interaccionUsuario;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

import conexiones.servidor.Servidor;
import data.AtletaEnDisciplina;
import data.Deportista;
import data.Volatil;
import utiles.Utiles;

public class Menu {

	public void menu(Scanner in, Servidor servidor, Volatil volatil, Utiles util) {
		util.delay(700);
		System.out.println("" + " _____      _                  ____   ___ ____   ___  \r\n"
				+ "/__   \\___ | | ___   _  ___   |___ \\ / _ \\___ \\ / _ \\ \r\n"
				+ "  / /\\/ _ \\| |/ / | | |/ _ \\    __) | | | |__) | | | |\r\n"
				+ " / / | (_) |   <| |_| | (_) |  / __/| |_| / __/| |_| |\r\n"
				+ " \\/   \\___/|_|\\_\\\\__, |\\___/  |_____|\\___/_____|\\___/ \r\n"
				+ "                 |___/                                \r\n"
				+ " __        __ _                                       \r\n"
				+ "/ _\\ ___  / _| |___      ____ _ _ __ ___              \r\n"
				+ "\\ \\ / _ \\| |_| __\\ \\ /\\ / / _` | '__/ _ \\             \r\n"
				+ "_\\ \\ (_) |  _| |_ \\ V  V / (_| | | |  __/             \r\n"
				+ "\\__/\\___/|_|  \\__| \\_/\\_/ \\__,_|_|  \\___|             \r\n"		
				
				+"												\r\n"
				+"												\r\n"
				+"   # # #         # # #         # # #   \r\n"
				+ " #       #     #       #     #       # \r\n"
				+ "#         # # #         # # #         #\r\n"
				+ "#       # #   # #     # #   # #       #\r\n"
				+ " #     # #     # #   # #     # #     # \r\n"
				+ "   # # #         # # #         # # #   \r\n"
				+ "        #       #     #       #        \r\n"
				+ "          # # #         # # #                            \r\n "
				+"                                                       ");
		System.out.println("    @ by Lisandro Vicens, Tomas E. Schattmann");
		System.out.println("    @ version 0.0.1");
		Boolean interruptor = true;
		String tmp = null;
		while (interruptor) {
			util.delay(700);
			System.out.println("-----------------------------------------------------------------");
			System.out.println("");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Choisissez une option:");
			System.out.println("   - 1. Chargement des athlètes.");
			System.out.println("   - 2. Assigner des disciplines aux athlètes.");
			System.out.println("   - 3. Montrer la liste des disciplines.");
			System.out.println("   - 4. Montrer les athlètes chargés de cette exécution.");
			System.out.println("   - 5. Montrer les participants des disciplines.");
			System.out.println("   - 0. Fermer.");
			System.out.println("");
			System.out.print("  > ");
			tmp = in.nextLine();
			System.out.println();
			switch (tmp) {
			case "1": {
				try {
					servidor.ingresarDeportistas(volatil);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			case "2": {
				servidor.asociarADisciplina(volatil.getListaDeportistasEjecucion());
				break;
			}
			case "3": {
				System.out.println("Disciplines: ");
				System.out.println("IDs ID   Nom");
				Utiles.mostrarLista(Volatil.getListaDisciplinas());
				break;
			}
			case "4": {
				System.out.println("   Athlètes: ");
				LinkedList<Deportista> tmpCase4 = volatil.getListaDeportistasEjecucion();
				if (tmpCase4 != null) {
					System.out.println("IDs ID  Prénom  Nom  Email  Telephone");	
				}
				Utiles.mostrarLista(tmpCase4);
				break;
			}
			case "5": {
				System.out.println("Participants et disciplines");
				LinkedList<AtletaEnDisciplina> tmpCase5 = Volatil.getListaAsociacion();
				if (tmpCase5 != null) {
					System.out.println("  | ID Athlète | ID Discipline");
				}
				Utiles.mostrarLista(tmpCase5);
				break;
			}
			case "0": {
				System.out.println("Fermeture...");
				interruptor = false;
				break;
			}
			default:
				System.out.println("Erreur dans le chiffre entré.");
				break;
			}
		}
	}
}
