public class SimularBanco {
		
	public static void main(String[] args) {
		Banco b1 = new Banco();
		Cliente francoa = new Cliente();
		francoa.altaCliente("Franco Acosta", 87654321, true);
		b1.agregarCliente(francoa);
		b1.asignarCuenta(87654321);
		b1.depositar(87654321, 1, 15000);
		b1.asignarCuenta(87654321);
		Cliente juanp = new Cliente();
		juanp.altaCliente("Juan Perez", 12345678, true);
		b1.agregarCliente(juanp);
		b1.asignarCuenta(12345678);
		b1.transferir(87654321, 1, 3, 4200);
		b1.listarDatoscliente(87654321);
		b1.listarDatoscliente(12345678);
	}
}