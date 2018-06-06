public class Movimiento {
	
	private double monto = 0;
	private int tipo = 0;
	private String operacion = " ";
	
	Movimiento(double m, int t, String o) {
		this.monto = m;
		this.tipo = t;
		this.operacion = o;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		if(this.tipo >= 1 && this.tipo <= 5)
			this.tipo = tipo;
		else
			System.out.println("El tipo de movimiento es incorrecto. Intente nuevamente eligiendo entre 1 y 5");
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		switch(this.operacion) {
		case "deposito" : case "ingreso por transferencia" : 
			case "iibb" : case "egreso por transferencia" : 
				case "retiro" : this.operacion = operacion; break;
				default: System.out.println("La operación no existe. Intente nuevamente."); break;
		}
	}
	
	public void listarDatos() {
		System.out.println("Monto: $"+this.monto);
		System.out.println("Tipo: "+this.tipo);
		System.out.println("Operación: "+this.operacion);
	}
}
