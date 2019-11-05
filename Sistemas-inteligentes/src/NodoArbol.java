
public class NodoArbol {

	NodoArbol padre;
	String estado;
	String accion;
	int f;
	int d;
	
	public NodoArbol(NodoArbol padre, String estado, String accion, int f, int d) {
		this.padre = padre;
		this.estado = estado;
		this.accion = accion;
		this.f = f;
		this.d = d;
	}
	
	public NodoArbol(String estado, int f, int d) {
		this.estado = estado;
		this.f = f;
		this.d = d;
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

	public int getd() {
		return d;
	}
	
	public void setd(int d) {
		this.d = d;
	}
}
