package conexiones.servidor;

import java.sql.*; 
import java.util.*;

import data.AtletaEnDisciplina;
import data.Deportista;
import data.Disciplina;
import data.Volatil;
import interaccionUsuario.Frontend;

/*	Servidor se encarga de las conexiones al servidor.
 *  
 *  @author Tomas E. Schattmann
 *  @version 0.0.1
 * */

public class Servidor {
	
	Conexion conexion = null;
	Connection cn = null;
	
	public Servidor() { 
		System.out.println("Test de connexion réussi");
		this.conexion = new Conexion();
		cn = conexion.conectar();
	}

	public static void asignarDisciplinas() {
		
	}
	
	/* Este metodo se encarga de comunicarse con la bese de datos para recuperar
	 * una lista de las disciplinas existentes.
	 * 
	 * @return lista de Strings con los nombres de las disciplinas
	 * */
	public LinkedList<Disciplina> obtenerListaDisciplinas() {
		LinkedList<Disciplina> retorno = new LinkedList<Disciplina>();
		Statement stm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM disciplina");

			while (rs.next()) {
				String stringTmp = String.valueOf(rs.getInt(1));
				Disciplina tmp = new Disciplina(stringTmp, rs.getString(2));
				if (tmp != null) retorno.addLast(tmp);
			}
			System.out.println("Le tableau des disciplines est chargé correctement");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	/* Metodo que se llamara desde consola. Este metodo una vez ejecutado brindara
	 * un entorno en consola de comandos para el ingreso de deportistas y se encargara
	 * de que los ingresados sean registrados en la base de datos.
	 * Trabaja con una lista de deportistas generada en el metodo crearListaParticipante()
	 * 
	 * @see Ejecutable.crearListaParticipantes();
	 * */
	public void ingresarDeportistas(Volatil volatil) throws SQLException{ 
		LinkedList<Deportista> envio = Frontend.crearListaParticipantes();
		if (envio != null) {
			ListIterator<Deportista> iterador = envio.listIterator();
			Deportista tmpDep = null;
			PreparedStatement stm = null;
			Integer i = 0 , o = 0;
			while (iterador.hasNext()) {
				tmpDep = iterador.next();
				try {
					cn = conexion.conectar();				
					stm = cn.prepareStatement("insert into deportista values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
					
					stm.setInt(1, 0);
					stm.setString(2, tmpDep.getApellido());
					stm.setString(3, tmpDep.getNombre());
					stm.setString(4, tmpDep.getEmail());
					stm.setString(5, tmpDep.getTelefono());
					int filasAfectadas = stm.executeUpdate();
					i++;
					if(filasAfectadas == 0) {
						throw new SQLException("Fallo");
					}
					try(ResultSet generatedKeys = stm.getGeneratedKeys()){
						if(generatedKeys.next()) {
							tmpDep.setID(generatedKeys.getInt(1));
						}
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
					o++;
				}
			}
			System.out.println(i + " athlètes de " + (i+o) + " ont été chargés avec succès ");
		}
		if (volatil.getListaDeportistasEjecucion() == null) {
			volatil.setListaDeportistasEjecucion(envio);
		}
		else {
			if (!envio.isEmpty()) {
				Iterator<Deportista> iterador = envio.iterator();
				while (iterador.hasNext()) {
					volatil.addDeportistasEjecucion(iterador.next());
				}
			}
		}
	}
	
	/* Metodo que crea la lista con todas las disciplinas de los Juegos Olimpicos. Almacena objetos de tipo Disciplina
	 * Se crea con la informacion recuperada de la base de datos en la tabla "disciplinas"
	 * 
	 * @return Lista enlazada con objetos Disciplina.
	 */
	public LinkedList<Disciplina> crearListaDisciplinas() {
		LinkedList<Disciplina> disciplinas = new LinkedList<Disciplina>();
		Conexion conexion = new Conexion();
		Connection conn = (Connection) conexion.conectar();
		Statement stm;
		try {
		stm = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT * FROM disciplina";
		ResultSet rst = ((java.sql.Statement) stm).executeQuery(sql);
		while(rst.next()) {
			Disciplina disciplina = new Disciplina(rst.getString("id"), rst.getString("nombre"));
			disciplinas.add(disciplina);
		}
		rst.close();
		}catch (Exception e) {
			System.out.println("Erreur");
		}
		return disciplinas;		
	}
	/*Metodo que se encarga de recorrer la lista de deportistas ingresados y asignarle una disciplina a cada uno de ellos. 
	 * Luego de eso, estos datos son cargados a la base de datos en la tabla "atleta_en_disciplina".
	 */
	public void asociarADisciplina(LinkedList<Deportista> listaDep) {
		if (listaDep != null) {
			AtletaEnDisciplina atd = null;
			if (Volatil.getListaAsociacion() == null) {
				Volatil.setListaAsociacion(Frontend.crearListaAsociacion(listaDep));
			} else { 
				Volatil.addListaAsociacion(Frontend.crearListaAsociacion(listaDep));
			}
			ListIterator<AtletaEnDisciplina> iterador = Volatil.getListaAsociacion().listIterator();
			Conexion conexion = new Conexion();
			Integer i = 0, o = 0;
			while(iterador.hasNext()) {
				atd = iterador.next();
				try {
					Connection conn = conexion.conectar();
					PreparedStatement stm = conn.prepareStatement("INSERT INTO atleta_en_disciplina values(?,?)");
					stm.setLong(1, atd.getidDep());
					stm.setLong(2, atd.getidDisc());
					stm.executeUpdate();
					i++;
					
				} catch (SQLException e) {
					e.printStackTrace();
					o++;
				}
			}
			System.out.println(i + " disciplines de" + (i+o) + "ont été associées avec succès");
		} else { 
			System.out.println("Erreur. Aucun athlète n'a été chargé dans cette exécution.");
		}

	}
}
	
