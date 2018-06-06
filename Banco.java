public class Banco {
	
	final static int MAX = 1000;
	private Cliente arrClientes[] = new Cliente[MAX];
	private Cuenta arrCuentas[] = new Cuenta[MAX];
	private int nrodec = 1;
	
	Banco() {
		for(int i = 0; i < MAX; i++) {
			this.arrCuentas[i] = new Cuenta(nrodec);
			this.nrodec++;
		}
	}
	
	public int obtenerPosvacia() {
		int posvacia = 0;
		int i = 0;
		while(i < MAX && this.arrClientes[i] != null) {
			i++;
		}
		if(this.arrClientes[i] == null) {
			posvacia = i;
		}
		return posvacia;
	}
	
	public int obtenerPosvaciacuenta() {
		int posvacia = 0;
		int i = 0;
		while(i < MAX && this.arrCuentas[i] == null) {
			i++;
		}
		if(this.arrCuentas[i] == null) {
			posvacia = i;
		}
		return posvacia;
	}
	
	public int obtenerPoscuenta(int nrodec) {
		int poscuenta = 0;
		int i = 0;
		while(i < MAX && this.arrCuentas[i] == null) {
			i++;
		}
		if(arrCuentas[i].getNrodecuenta() == nrodec) {
			poscuenta = i;
		}
		return poscuenta;
	}
	
	public int obtenerPoscliente(int Dni) {
		int poscliente = 0;
		int i = 0;
		while(i < MAX && this.arrClientes[i] == null) {
			i++;
		}
		if(this.arrClientes[i].getDni() == Dni) {
			poscliente = i;
		}
		return poscliente;
	}
		
	public void agregarCliente(Cliente nuevocliente) {
		int posvacia = obtenerPosvacia();
		this.arrClientes[posvacia] = nuevocliente;
	}
	
	public void asignarCuenta(int Dni) {
		int poscliente = obtenerPoscliente(Dni);
		int posvaciacuenta = obtenerPosvaciacuenta();
		this.arrCuentas[posvaciacuenta].setCliente(arrClientes[poscliente]);
	}
	
	public void listarCuentas(int Dni) {
		int i = 0;
		while(i < MAX && this.arrCuentas[i].getCliente() != null) {
			if(this.arrCuentas[i].getCliente().getDni() == Dni) {
				this.arrCuentas[i].listarDatos();
			}
			i++;
		}
	}

	public void listarDatoscliente(int Dni) {
		int poscliente = obtenerPoscliente(Dni);
		this.arrClientes[poscliente].listarDatos();
		listarCuentas(Dni);
	}
	
	public void correrMovimientosaderecha(int poscuenta) {
		for(int i = Cuenta.MAXMOVIMIENTOS-1; i > 0; i--) {
			this.arrCuentas[poscuenta].arrMovimientos[i] = this.arrCuentas[poscuenta].arrMovimientos[i-1];
		}
	}
	
	public void depositar(int Dni, int nrodec, double monto) {
		Movimiento deposito = new Movimiento(monto, 1, "deposito");
		int poscuenta = obtenerPoscuenta(nrodec);
		int poscliente = obtenerPoscliente(Dni);
		if(this.arrClientes[poscliente].getDni() == Dni) {
			if(this.arrCuentas[poscuenta].getNrodecuenta() == nrodec) {
				this.arrCuentas[poscuenta].setMonto(monto);
				correrMovimientosaderecha(poscuenta);
				this.arrCuentas[poscuenta].arrMovimientos[0] = deposito;
			}
			else
				System.out.println("No existe cuenta.");
		}
		else 
			System.out.println("No existe el cliente.");
	}
	
	public void retirar(int Dni, int nrodec, double monto) {
		
	}
}
