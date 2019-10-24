
public class NodoArbol {

	NodoArbol padre;
	String estado;
	String accion;
	int f;
	
	public NodoArbol(NodoArbol padre, String estado, String accion, int f) {
		this.padre = padre;
		this.estado = estado;
		this.accion = accion;
		this.f = f;
	}
	
	public NodoArbol(String estado, int f) {
		this.estado = estado;
		this.f = f;
	}

	public NodoArbol getPadre() {
		return padre;
	}
	
	public void setPadre(NodoArbol padre) {
		this.padre = padre;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getAccion() {
		return accion;
	}
	
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public int getf() {
		return f;
	}
	
	public void setf(int f) {
		this.f = f;
	}
}
