
package alquilerVehiculos.mvc.modelo.dominio.vehiculo;

// TODO: Auto-generated Javadoc
/**
 * The Class Turismo.
 */
public class Turismo extends Vehiculo {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The factor turismo. */
	private double factorTurismo = 0.0;

	/**
	 * Instantiates a new turismo.
	 *
	 * @param matricula the matricula
	 * @param marca the marca
	 * @param modelo the modelo
	 * @param datosTecnicos the datos tecnicos
	 */
	public Turismo(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
		super(matricula, marca, modelo, datosTecnicos);
	}

	/**
	 * Instantiates a new turismo.
	 *
	 * @param turismo the turismo
	 */
	public Turismo(Turismo turismo) {
		super(turismo);
	}

	/* (sin Javadoc)
	 * @see alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo#getTipoVehiculo()
	 */
	public TipoVehiculo getTipoVehiculo() {

		return TipoVehiculo.TURISMO;
	}

	/* (sin Javadoc)
	 * @see alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo#precioEspecifico()
	 */
	public double precioEspecifico() {
		factorTurismo= datosTecnicos.getCilindrada()/50;
		return factorTurismo;
	}
}