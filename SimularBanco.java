import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimularBanco {
		
	public static void main(String[] args) {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Ingrese string para iniciar primera parte: ");
			String opcion = entrada.readLine();
			//INICIO PRIMERA PARTE
			Banco banco_un = new Banco();
			Cliente c1 = new Cliente();
			Cliente c2 = new Cliente();
			Cliente c3 = new Cliente();
			c1.altaCliente("Luis Caballero", 22, true);
			c2.altaCliente("José Perez", 33, false);
			c3.altaCliente("Carlos Lorenzo", 44, true);
			banco_un.agregarCliente(c1);
			banco_un.agregarCliente(c2);
			banco_un.agregarCliente(c3);
			banco_un.asignarCuenta(22);
			banco_un.asignarCuenta(22);
			banco_un.asignarCuenta(44);
			banco_un.asignarCuenta(33);
			banco_un.listarDatoscliente(22);
			banco_un.listarDatoscliente(33);
			banco_un.listarDatoscliente(44);
			//FIN PRIMERA PARTE
			System.out.println("Ingrese string para iniciar segunda parte: ");
			opcion = entrada.readLine();
			//INICIO SEGUNDA PARTE
			banco_un.depositar(22, 1, 100);
			banco_un.depositar(33, 1, 100);
			banco_un.retirar(22, 1, 50);
			banco_un.listarDatoscliente(22);
			banco_un.listarDatoscliente(33);
			banco_un.listarDatoscliente(44);
			//FIN SEGUNDA PARTE
			System.out.println("Ingrese string para iniciar tercera parte: ");
			opcion = entrada.readLine();
			//INICIO TERCERA PARTE
			banco_un.transferir(22, 1, 2, 50);
			banco_un.transferir(22, 1, 3, 10);
			banco_un.listarDatoscliente(22);
			banco_un.listarDatoscliente(33);
			banco_un.listarDatoscliente(44);
			//FIN TERCERA PARTE
			System.out.println("Ingrese string para iniciar cuarta parte: ");
			opcion = entrada.readLine();
			//INICIO CUARTA PARTE
			banco_un.transferir(33, 1, 2, 50);
			banco_un.transferir(22, 1, 2, 100);
			banco_un.listarDatoscliente(22);
			banco_un.listarDatoscliente(33);
			banco_un.listarDatoscliente(44);
			//FIN CUARTA PARTE
			System.out.println("Ingrese string para iniciar quinta parte: ");
			opcion = entrada.readLine();
			//INICIO QUINTA PARTE
			banco_un.cerrarCuenta(22, 1);
			banco_un.transferir(44, 3, 4, 4);
			banco_un.listarDatoscliente(22);
			banco_un.listarDatoscliente(33);
			banco_un.listarDatoscliente(44);
			//FIN QUINTA PARTE
			System.out.println("Ingrese string para iniciar sexta parte: ");
			opcion = entrada.readLine();
			//INICIO SEXTA PARTE
			banco_un.cerrarCuenta(22, 2);
			banco_un.cerrarCuenta(33, 4);
			banco_un.cerrarCuenta(44, 3);
			banco_un.listarDatoscliente(22);
			banco_un.listarDatoscliente(33);
			banco_un.listarDatoscliente(44);
			//FIN SEXTA PARTE
		}
		catch(Exception exc) {
			System.out.println(exc);
		}
	}
}