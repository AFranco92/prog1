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
	
	private int obtenerPosvaciacliente() {
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
	
	private int obtenerPosvaciacuenta() {
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
	
	private int obtenerPoscliente(int dni) {
		int poscliente = 0;
		int i = 0;
		while(i < MAX && this.arrClientes[i] != null) {
			if(this.arrClientes[i].getDni() == dni) {
				poscliente = i;
				return poscliente;
			}
		i++;
		}
		
		return poscliente;
	}
	
	private boolean esdniUnico(int dni) {
		for(int i = 0; i < MAX; i++) {
			if(this.arrClientes[i] != null && this.arrClientes[i].getDni() == dni) {
				return false;
			}
		}
		return true;
	}
		
	public void agregarCliente(Cliente nuevocliente) {
		int posvacia = obtenerPosvaciacliente();
		if(esdniUnico(nuevocliente.getDni())) {
			this.arrClientes[posvacia] = nuevocliente;
		}
		else {
			System.out.println("Ya existe un cliente con ese DNI.");
		}
	}
	
	public void asignarCuenta(int dni) {
		int poscliente = obtenerPoscliente(dni);
		int posvaciacuenta = obtenerPosvaciacuenta();
		this.arrCuentas[posvaciacuenta].setCliente(arrClientes[poscliente]);
	}
	
	private void limpiarCuenta(int poscuenta, int nrodec) {
		if(this.arrCuentas[poscuenta] != null && this.arrCuentas[poscuenta].getNrodecuenta() == nrodec) {
			this.arrCuentas[poscuenta].setCliente(null);
			this.arrCuentas[poscuenta].setMonto(0);
			this.arrCuentas[poscuenta].setArrMovimientos(new Movimiento[Cuenta.MAXMOVIMIENTOS]);
		}
	}
	
	public void cerrarCuenta(int dni, int nrodec) {
		int poscuenta = nrodec-1;
		if(this.arrCuentas[poscuenta] != null && this.arrCuentas[poscuenta].getCliente().getDni() == dni) {
			if(this.arrCuentas[poscuenta] != null && this.arrCuentas[poscuenta].getNrodecuenta() == nrodec) {
				limpiarCuenta(poscuenta, nrodec);
				System.out.println("Se cerr� la cuenta "+nrodec);
			}				
			else
				System.out.println("Cierre de cuenta fallido: No existe esa cuenta");
		}
		else
			System.out.println("Cierre de cuenta fallido: No existe ese cliente.");
	}
	
	private void listarCuentas(int dni) {
		int i = 0;
		while(i < MAX) {
			if(this.arrCuentas[i].getCliente() != null && this.arrCuentas[i].getCliente().getDni() == dni) {
				this.arrCuentas[i].listarDatos();
			}
			i++;
		}
	}
	
	private boolean cuentaEsdelcliente(int poscuenta, int nrodec, int dni) {
		if(this.arrCuentas[poscuenta].getCliente() != null && this.arrCuentas[poscuenta].getCliente().getDni() == dni && 
				this.arrCuentas[poscuenta].getNrodecuenta() == nrodec) {
			return true;
		}
		return false;
	}
	
	private boolean existeCliente(int poscliente, int dni) {
		if(this.arrClientes[poscliente] != null && this.arrClientes[poscliente].getDni() == dni) {
			return true;
		}
		return false;
	}

	public void listarDatoscliente(int dni) {
		int poscliente = obtenerPoscliente(dni);
		this.arrClientes[poscliente].listarDatos();
		listarCuentas(dni);
	}

	public void depositar(int dni, int nrodec, double monto) {
		Movimiento deposito = new Movimiento(monto, 1, "Dep�sito");
		Movimiento IIBB = new Movimiento((2*monto/100), 3, "IIBB");
		int poscuenta = nrodec-1;
		int poscliente = obtenerPoscliente(dni);
		if(existeCliente(poscliente, dni)) {
			if(cuentaEsdelcliente(poscuenta, nrodec, dni) && monto > 0) {
				this.arrCuentas[poscuenta].setMonto(monto);
				this.arrCuentas[poscuenta].correrMovimientosaderecha(poscuenta);
				this.arrCuentas[poscuenta].setMovimiento(0, deposito);
				if(this.arrCuentas[poscuenta].getCliente() != null && this.arrCuentas[poscuenta].getCliente().esMonotributista()) {
					this.arrCuentas[poscuenta].setMonto(-(2*monto/100));
					this.arrCuentas[poscuenta].correrMovimientosaderecha(poscuenta);
					this.arrCuentas[poscuenta].setMovimiento(0, IIBB);
				}
			}
			else
				System.out.println("Dep�sito fallido: La cuenta no pertenece al cliente o ha ingresado un n�mero negativo.");
		}
		else 
			System.out.println("Dep�sito fallido: El DNI ingresado no pertenece a un cliente del banco.");
	}
	
	public void retirar(int dni, int nrodec, double monto) {
		Movimiento retiro = new Movimiento(monto, 5, "Retiro");
		int poscuenta = nrodec-1;
		int poscliente = obtenerPoscliente(dni);
		if(cuentaEsdelcliente(poscuenta, nrodec, dni)) {
			if(existeCliente(poscliente, dni)) {
				if(this.arrCuentas[poscuenta].getMonto() >= monto && monto > 0) {
					this.arrCuentas[poscuenta].setMonto(-monto);
					this.arrCuentas[poscuenta].correrMovimientosaderecha(poscuenta);
					this.arrCuentas[poscuenta].setMovimiento(0, retiro);
				}
				else
					System.out.println("Retiro fallido: No posee esa cantidad de dinero.");
			}
			else
				System.out.println("Retiro fallido: El DNI ingresado no pertenece a un cliente del banco.");
		}
		else
			System.out.println("Retiro fallido: La cuenta no pertenece al cliente.");
	}
	
	private void modificarCuentaorigenportrans(int poscuentaorigen, int nrodeco, double monto, Movimiento egresoportransferencia) {
		this.arrCuentas[poscuentaorigen].setMonto(-monto);
		this.arrCuentas[poscuentaorigen].correrMovimientosaderecha(poscuentaorigen);
		this.arrCuentas[poscuentaorigen].setMovimiento(0, egresoportransferencia);
	}
	
	private void modificarCuentadestinoportrans(int poscuentadestino, int nrodecd, double monto, Movimiento ingresoportransferencia) {
		this.arrCuentas[poscuentadestino].setMonto(monto);
		this.arrCuentas[poscuentadestino].correrMovimientosaderecha(poscuentadestino);
		this.arrCuentas[poscuentadestino].setMovimiento(0, ingresoportransferencia);
	}
	
	private void modificarCuentaMTdestinoportrans(int poscuentadestino, double monto, Movimiento IIBB) {
		this.arrCuentas[poscuentadestino].setMonto(-(2*monto/100));
		this.arrCuentas[poscuentadestino].correrMovimientosaderecha(poscuentadestino);
		this.arrCuentas[poscuentadestino].setMovimiento(0, IIBB);
	}
	
	public void transferir(int dni, int nrodeco, int nrodecd, double monto) {
		Movimiento ingresoportransferencia = new Movimiento(monto, 2, "Ingreso por transferencia");
		Movimiento egresoportransferencia = new Movimiento(monto, 4, "Egreso por transferencia");
		Movimiento IIBB = new Movimiento((2*monto/100), 3, "IIBB");
		int poscuentaorigen = nrodeco-1;
		int poscuentadestino = nrodecd-1;
		int poscliente = obtenerPoscliente(dni);
		if(existeCliente(poscliente, dni)) {
			if(cuentaEsdelcliente(poscuentaorigen, nrodeco, dni)) {
				if(monto <= this.arrCuentas[poscuentaorigen].getMonto() && monto > 0) {
					if(this.arrCuentas[poscuentadestino].getCliente() != null) {
						modificarCuentaorigenportrans(poscuentaorigen, nrodeco, monto, egresoportransferencia);
						modificarCuentadestinoportrans(poscuentadestino, nrodecd, monto, ingresoportransferencia);
						if(this.arrCuentas[poscuentadestino].getCliente().esMonotributista() && 
								this.arrCuentas[poscuentaorigen].getCliente() != this.arrCuentas[poscuentadestino].getCliente()) {
							modificarCuentaMTdestinoportrans(poscuentadestino, monto, IIBB);
						}
					}
					else
						System.out.println("Transferencia fallida: No existe cliente con esa cuenta.");
				}
				else
					System.out.println("Transferencia fallida: No posee esa cantidad de dinero.");
			}
			else
				System.out.println("Transferencia fallida: La cuenta no pertenece al cliente.");
		}
		else
			System.out.println("Transferencia fallida: No existe un cliente asociado a ese DNI.");
	}
}