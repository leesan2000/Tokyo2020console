package data;

public class Disciplina {
	private String nombre;
	private String id;
		
	public Disciplina(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
		
	public String getNombre() {
		return nombre;
	}
	
	public String getID() {
		return id;
	}
	
	
	public void setNombre (String n) {
		this.nombre = n;
	}
	
	@Override
	public String toString() {
		return(this.getID() + "   " + this.getNombre());
	}
	
}
	
		


	

