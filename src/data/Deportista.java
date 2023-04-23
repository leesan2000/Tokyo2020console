package data;

public class Deportista {
	private int id = -1;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	
	public int getID() {
		return id;
	}
		public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setID(int iden) {
		this.id = iden;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		if(this.getID() == -1) {
			return (" - " + "    " + this.getNombre() + "    " + this.getApellido() + "    " + this.getEmail() + "    " + this.getTelefono());
		}else {
			return (this.getID() + "   " + this.getNombre() + "       " + this.getApellido() + "      " + this.getEmail() + "      " + this.getTelefono());
		}
	}
	
	
		
}
