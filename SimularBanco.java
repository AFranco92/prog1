public class SimularBanco {
		
	public static void main(String[] args) {
		Banco b1 = new Banco();
		Cliente francoa = new Cliente();
		francoa.altaCliente("Franco Acosta", 37014788, true);
		b1.agregarCliente(francoa);
		b1.asignarCuenta(37014788);
		b1.depositar(37014788, 1, 15000);
		b1.depositar(37014788, 1, 40000);
		b1.retirar(37014788, 1, 10000);
		b1.listarDatoscliente(37014788);
	}
}