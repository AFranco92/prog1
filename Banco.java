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
		while(i < MAX && this.arrCuentas[i].getCliente() != null) {
			i++;
		}
		if(i < MAX && this.arrCuentas[i].getCliente() == null) {
			posvacia = i;
		}
		return posvacia;
	}
	
	public int obtenerPoscliente(int Dni) {
		int poscliente = 0;
		int i = 0;
		while(i < MAX && this.arrClientes[i] != null) {
			if(this.arrClientes[i].getDni() == Dni) {
				poscliente = i;
				return poscliente;
			}
		i++;
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
	
	private void limpiarCuenta(int poscuenta) {
		this.arrCuentas[poscuenta].setCliente(null);
		this.arrCuentas[poscuenta].setMonto(0);
		this.arrCuentas[poscuenta].setArrMovimientos(new Movimiento[Cuenta.MAXMOVIMIENTOS]);
	}
	
	public void cerrarCuenta(int Dni, int nrodec) {
		int poscliente = obtenerPoscliente(Dni);
		int poscuenta = nrodec-1;
		if(this.arrClientes[poscliente].getDni() == Dni) {
			if(this.arrCuentas[poscuenta].getNrodecuenta() == nrodec) {
				limpiarCuenta(poscuenta);
			}
			else
				System.out.println("No existe cuenta o no es de ese cliente.");
		}
		else
			System.out.println("No existe cliente.");
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
	
	private void correrMovimientosaderecha(int poscuenta) {
		for(int i = Cuenta.MAXMOVIMIENTOS-1; i > 0; i--) {
			this.arrCuentas[poscuenta].arrMovimientos[i] = this.arrCuentas[poscuenta].arrMovimientos[i-1];
		}
	}

	public void depositar(int Dni, int nrodec, double monto) {
		Movimiento deposito = new Movimiento(monto, 1, "deposito");
		Movimiento iibb = new Movimiento((2*monto/100), 3, "iibb");
		int poscuenta = nrodec-1;
		int poscliente = obtenerPoscliente(Dni);
		if(this.arrClientes[poscliente].getDni() == Dni) {
			if(this.arrCuentas[poscuenta].getNrodecuenta() == nrodec) {
				this.arrCuentas[poscuenta].setMonto(monto);
				correrMovimientosaderecha(poscuenta);
				this.arrCuentas[poscuenta].arrMovimientos[0] = deposito;
				if(this.arrCuentas[poscuenta].getCliente().esMonotributista()) {
					this.arrCuentas[poscuenta].setMonto(-(2*monto/100));
					correrMovimientosaderecha(poscuenta);
					this.arrCuentas[poscuenta].arrMovimientos[0] = iibb;
				}
			}
			else
				System.out.println("No existe cuenta.");
		}
		else 
			System.out.println("El DNI ingresado no pertenece a un cliente del banco.");
	}
	
	public void retirar(int Dni, int nrodec, double monto) {
		Movimiento retiro = new Movimiento(monto, 5, "retiro");
		int poscuenta = nrodec-1;
		int poscliente = obtenerPoscliente(Dni);
		if(this.arrClientes[poscliente] != null && this.arrCuentas[poscuenta].getNrodecuenta() == nrodec) {
			if(this.arrCuentas[poscuenta].getCliente().getDni() == Dni) {
				if(this.arrCuentas[poscuenta].getMonto() >= monto) {
					this.arrCuentas[poscuenta].setMonto(-monto);
					correrMovimientosaderecha(poscuenta);
					this.arrCuentas[poscuenta].arrMovimientos[0] = retiro;
				}
				else
					System.out.println("No posee esa cantidad de dinero.");
			}
			else
				System.out.println("El DNI ingresado no pertenece a un cliente del banco.");
		}
		else
			System.out.println("La cuenta no pertenece al cliente.");
	}
	
	private void modificarCuentaorigenportrans(int poscuentaorigen, int nrodeco, double monto, Movimiento egresoportransferencia) {
		this.arrCuentas[poscuentaorigen].setMonto(-monto);
		correrMovimientosaderecha(poscuentaorigen);
		this.arrCuentas[poscuentaorigen].arrMovimientos[0] = egresoportransferencia;
	}
	
	private void modificarCuentadestinoportrans(int poscuentadestino, int nrodecd, double monto, Movimiento ingresoportransferencia) {
		this.arrCuentas[poscuentadestino].setMonto(monto);
		correrMovimientosaderecha(poscuentadestino);
		this.arrCuentas[poscuentadestino].arrMovimientos[0] = ingresoportransferencia;
	}
	
	private void modificarCuentaMTdestinoportrans(int poscuentadestino, double monto, Movimiento iibb) {
		this.arrCuentas[poscuentadestino].setMonto(-(2*monto/100));
		correrMovimientosaderecha(poscuentadestino);
		this.arrCuentas[poscuentadestino].arrMovimientos[0] = iibb;
	}
	
	public void transferir(int Dni, int nrodeco, int nrodecd, double monto) {
		Movimiento ingresoportransferencia = new Movimiento(monto, 2, "ingreso por transferencia");
		Movimiento egresoportransferencia = new Movimiento(monto, 4, "egreso por transferencia");
		Movimiento iibb = new Movimiento((2*monto/100), 3, "iibb");
		int poscuentaorigen = nrodeco-1;
		int poscuentadestino = nrodecd-1;
		int poscliente = obtenerPoscliente(Dni);
		if(this.arrClientes[poscliente].getDni() == Dni) {
			if(this.arrCuentas[poscuentaorigen].getCliente().getDni() == this.arrClientes[poscliente].getDni()) {
				if(monto <= this.arrCuentas[poscuentaorigen].getMonto()) {
					if(this.arrCuentas[poscuentadestino].getCliente() != null) {
						modificarCuentaorigenportrans(poscuentaorigen, nrodeco, monto, egresoportransferencia);
						modificarCuentadestinoportrans(poscuentadestino, nrodecd, monto, ingresoportransferencia);
						if(this.arrCuentas[poscuentadestino].getCliente().esMonotributista() && 
								this.arrCuentas[poscuentaorigen].getCliente() != this.arrCuentas[poscuentadestino].getCliente()) {
							modificarCuentaMTdestinoportrans(poscuentadestino, monto, iibb);
						}
					}
					else
						System.out.println("No existe cliente con esa cuenta.");
				}
				else
					System.out.println("No posee esa cantidad de dinero.");
			}
			else
				System.out.println("La cuenta no pertenece al cliente.");
		}
		else
			System.out.println("No existe un cliente asociado a ese DNI.");
	}
}
