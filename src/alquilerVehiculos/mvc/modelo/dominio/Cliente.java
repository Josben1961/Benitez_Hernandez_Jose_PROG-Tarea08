
package alquilerVehiculos.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * The Class Cliente.
 */
public class Cliente implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The nombre. */
	private String nombre;
	
	/** The dni. */
	private String dni;
	
	/** The direccion postal. */
	private DireccionPostal direccionPostal;
	
	/** The identificador. */
	private int identificador;
	
	/** The ultimo identificador. */
	private static int ultimoIdentificador = 0;

	/**
	 * Instantiates a new cliente.
	 *
	 * @param nombre the nombre
	 * @param dni the dni
	 * @param direccionPostal the direccion postal
	 */
	// Constructor con los 5 par�metros
	public Cliente(String nombre, String dni, DireccionPostal direccionPostal) {
		setNombre(nombre);
		setDni(dni);
		setDireccionPostal(direccionPostal);
		asignarNuevoIdentificador();

	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	private void setNombre(String nombre) {
		if (nombre != null && !nombre.equals(""))
			this.nombre = nombre;
		else
			throw new ExcepcionAlquilerVehiculos("Nombre no válido");
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni the new dni
	 */
	private void setDni(String dni) {
		if (compruebaDni(dni))
			this.dni = dni;
		else
			throw new ExcepcionAlquilerVehiculos("DNI no válido");
	}

	/**
	 * Sets the direccion postal.
	 *
	 * @param direccionPostal the new direccion postal
	 */
	public void setDireccionPostal(DireccionPostal direccionPostal) {
		this.direccionPostal = new DireccionPostal(direccionPostal);
	}

	/**
	 * Instantiates a new cliente.
	 *
	 * @param cliente the cliente
	 */
	public Cliente(Cliente cliente) {
		nombre = cliente.getNombre();
		dni = cliente.getDni();
		direccionPostal = cliente.getDireccionPostal();
		identificador = cliente.getIdentificador();

	}

	/**
	 * Asignar nuevo identificador.
	 */
	private void asignarNuevoIdentificador() {
		ultimoIdentificador++;
		identificador = ultimoIdentificador;
	}

	/**
	 * Aumentar ultimo identificador.
	 *
	 * @param cantidad the cantidad
	 */
	public static void aumentarUltimoIdentificador(int cantidad) {
		if (cantidad > 0)
			ultimoIdentificador += cantidad;
		else
			throw new ExcepcionAlquilerVehiculos("Sólo puedo aumentar el último identificador");
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Gets the direccion postal.
	 *
	 * @return the direccion postal
	 */
	public DireccionPostal getDireccionPostal() {
		return new DireccionPostal(direccionPostal);
	}

	/**
	 * Gets the identificador.
	 *
	 * @return the identificador
	 */
	public int getIdentificador() {
		return identificador;
	}

	/**
	 * Comprueba dni.
	 *
	 * @param dni the dni
	 * @return true, if successful
	 * @throws ExcepcionAlquilerVehiculos the excepcion alquiler vehiculos
	 */
	// Método para comprobar el DNI introducido
	private boolean compruebaDni(String dni) throws ExcepcionAlquilerVehiculos {
		Pattern patron = Pattern.compile("[0-9]{8}[A-Z]");
		Matcher emparejador;

		emparejador = patron.matcher(dni);
		return emparejador.matches();

	}
	// Método toString que representa al cliente

	/* (sin Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Identificador: %d Nombre: %s DNI: %s %s %n", identificador, nombre, dni, direccionPostal);
	}
	
	@Override
	public boolean equals(Object otro) {
		if (otro == null || !(otro instanceof Cliente)) 
			return false;
	    if (otro == this) 
	    	return true;
	    return (dni.equals(((Cliente) otro).getDni()));
	}
	
	@Override
    public int hashCode() {
		return dni.hashCode();
	}
}
