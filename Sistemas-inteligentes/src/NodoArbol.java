
public class NodoArbol implements Comparable<NodoArbol>{

	int id;
	NodoArbol padre;
	String estado;
	String accion;
	double h;
	double f;
	double d;
	double coste;
	
	public NodoArbol(int id, NodoArbol padre, String estado, String accion, double h, double f, double d, double coste) {
		this.id = id;
		this.padre = padre;
		this.estado = estado;
		this.accion = accion;
		this.h = h;
		this.f = f;
		this.d = d;
		this.coste = coste;
	}

	public double geth() {
		return h;
	}
	
	public void seth(double h) {
		this.h = h;
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
	
	public double getf() {
		return f;
	}
	
	public double getfd() {
		return f;
	}
	
	public void setf(double f) {
		this.f = f;
	}

	public double getd() {
		return d;
	}
	
	public void setd(double d) {
		this.d = d;
	}
	
	public int getid() {
		return id;
	}
	
	public void setid(int id) {
		this.id = id;
	}

	public void setcoste(double coste) {
		this.coste = coste;
	}
	
	public double getcoste() {
		return coste;
	}
	
	@Override
	public int compareTo(NodoArbol n) {
		if(this.getf() == n.getf()) {
			if(this.getid()<n.getid()) {
				return -1;
			}else {
				return 1;
			}
		}else if(this.getf()<n.getf()) {
			return -1;
		}else {
			return 1;
		}

	}



}
