public class Cuenta {
	
	final static int MAXMOVIMIENTOS = 10;
	private int nrodecuenta = 0;
	private Cliente cliente;
	private double monto = 0;
	private Movimiento arrMovimientos[] = new Movimiento[MAXMOVIMIENTOS];
	
	Cuenta(int n) {
		this.nrodecuenta = n;
	}

	public int getNrodecuenta() {
		return nrodecuenta;
	}

	public void setNrodecuenta(int nrodecuenta) {
		this.nrodecuenta = nrodecuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto += monto;
	}

	public Movimiento[] getArrMovimientos() {
		return arrMovimientos;
	}
	
	public void setMovimiento(int posmovimiento, Movimiento movimiento) {
		this.arrMovimientos[posmovimiento] = movimiento;
	}
	
	public void setArrMovimientos(Movimiento[] arrMovimientos) {
		this.arrMovimientos = arrMovimientos;
	}
	
	public void listarDatos() {
		System.out.println("Número de cuenta: "+this.nrodecuenta);
		System.out.println("Saldo: $"+this.monto);
		System.out.println("Movimientos: ");
		for(int i = 0; i < MAXMOVIMIENTOS; i++) {
			if(arrMovimientos[i] != null) {
				this.arrMovimientos[i].listarDatos();
			}
		}
	}
	
	public void correrMovimientosaderecha(int poscuenta) {
		for(int i = MAXMOVIMIENTOS-1; i > 0; i--) {
			arrMovimientos[i] = arrMovimientos[i-1];
		}
	}
	
}