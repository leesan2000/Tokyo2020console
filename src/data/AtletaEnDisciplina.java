package data;

public class AtletaEnDisciplina {
	private int idDisciplina;
	private int idDeportista;

	
	
	public AtletaEnDisciplina(int idDepor, int idDiscip) {
		this.idDeportista = idDepor;
		this.idDisciplina = idDiscip;
	}
	
	public void setidDisc(int idDisc) {
		this.idDisciplina = idDisc;
	}
	
	public void setidDep(int idDep) {
		this.idDeportista = idDep;
	}
	
	public int getidDep() {
		return idDeportista;
	}
		
	public int getidDisc() {
		return idDisciplina;
	}
	
	
	@Override
	public String toString() {
		return(this.getidDep() + "    	      " + this.getidDisc());
	}
	
	
}
