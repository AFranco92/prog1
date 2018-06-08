public class SimularBanco {
		
	public static void main(String[] args) {
		Banco b1 = new Banco();
		Cliente francoa = new Cliente();
		francoa.altaCliente("Franco Acosta", 37014788, true);
		b1.agregarCliente(francoa);
		b1.asignarCuenta(37014788);
		b1.depositar(37014788, 1, 15000);
		b1.retirar(37014788, 1, 10000);
		b1.asignarCuenta(37014788);
		Cliente juanp = new Cliente();
		juanp.altaCliente("Juan Perez", 12345678, true);
		b1.agregarCliente(juanp);
		b1.asignarCuenta(12345678);
		b1.transferir(37014788, 1, 3, 4200);
		b1.retirar(12345678, 3, 4000);
		b1.listarDatoscliente(37014788);
		b1.listarDatoscliente(12345678);
	}
}