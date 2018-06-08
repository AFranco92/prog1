public class Cliente {
	
	private String nombreyapellido = " ";
	private int Dni = 0;
	private boolean esmonotributista = false;

	public String getNombreyapellido() {
		return nombreyapellido;
	}

	public void setNombreyapellido(String nombreyapellido) {
		this.nombreyapellido = nombreyapellido;
	}

	public int getDni() {
		return Dni;
	}

	public void setDni(int dni) {
		this.Dni = dni;
	}

	public boolean esMonotributista() {
		return esmonotributista;
	}

	public void setEsmonotributista(boolean esmonotributista) {
		this.esmonotributista = esmonotributista;
	}

	public void altaCliente(String nombreyapellido, int Dni, boolean esmonotributista) {
		this.nombreyapellido = nombreyapellido;
		this.Dni = Dni;
		this.esmonotributista = esmonotributista;
	}
	
	public void listarDatos() {
		System.out.println("__________________________"+"\n"+"__________________________");
		System.out.println("Nombre y apellido: "+this.nombreyapellido);
		System.out.println("Dni: "+this.Dni);
		if(this.esmonotributista)
			System.out.println("Monotributista: Sí");
		else
			System.out.println("Monotributista: No");
	}
}
