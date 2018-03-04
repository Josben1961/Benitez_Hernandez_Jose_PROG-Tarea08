
package alquilerVehiculos.mvc.modelo;

import alquilerVehiculos.mvc.modelo.dao.Clientes;

import java.util.List;

import alquilerVehiculos.mvc.modelo.dao.Alquileres;
import alquilerVehiculos.mvc.modelo.dao.Vehiculos;
import alquilerVehiculos.mvc.modelo.dominio.Alquiler;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

// TODO: Auto-generated Javadoc
/**
 * The Class ModeloAlquilerVehiculos.
 */
public class ModeloAlquilerVehiculos implements IModeloAlquilerVehiculos {

	/** The clientes. */
	private Clientes clientes;

	/** The vehiculos. */
	private Vehiculos vehiculos;

	/** The alquileres. */
	private Alquileres alquileres;

	/**
	 * Instantiates a new modelo alquiler vehiculos.
	 */
	public ModeloAlquilerVehiculos() {
		clientes = new Clientes();
		vehiculos = new Vehiculos();
		alquileres = new Alquileres();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#anadirCliente(
	 * alquilerVehiculos.mvc.modelo.dominio.Cliente)
	 */
	public void anadirCliente(Cliente cliente) {
		clientes.anadirCliente(cliente);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see
	 * alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#borrarCliente(java.lang
	 * .String)
	 */
	public void borrarCliente(String dni) {
		clientes.borrarCliente(dni);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see
	 * alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#buscarCliente(java.lang
	 * .String)
	 */
	public Cliente buscarCliente(String dni) {
		return clientes.buscarCliente(dni);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#obtenerClientes()
	 */
	@Override
	public List<Cliente> obtenerClientes() {
		return clientes.getClientes();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#anadirVehiculo(
	 * alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo,
	 * alquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo)
	 */
	public void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
		vehiculos.anadirVehiculo(vehiculo, tipoVehiculo);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see
	 * alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#borrarVehiculo(java.
	 * lang.String)
	 */
	public void borrarVehiculo(String matricula) {
		vehiculos.borrarVehiculo(matricula);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see
	 * alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#buscarVehiculo(java.
	 * lang.String)
	 */
	public Vehiculo buscarVehiculo(String matricula) {
		return vehiculos.buscarVehiculo(matricula);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#obtenerVehiculos()
	 */
	@Override
	public List<Vehiculo> obtenerVehiculos() {
		return vehiculos.getVehiculos();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#abrirAlquiler(
	 * alquilerVehiculos.mvc.modelo.dominio.Cliente,
	 * alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo)
	 */
	public void abrirAlquiler(Cliente cliente, Vehiculo vehiculo) {
		comprobarExistenciaVehiculo(vehiculo);
		alquileres.abrirAlquiler(cliente, vehiculo);
	}

	/**
	 * Comprobar existencia vehiculo.
	 *
	 * @param vehiculo
	 *            the vehiculo
	 */
	private void comprobarExistenciaVehiculo(Vehiculo vehiculo) {
		if (vehiculos.buscarVehiculo(vehiculo.getMatricula()) == null)
			throw new ExcepcionAlquilerVehiculos("El vehículo no existe");
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#cerrarAlquiler(
	 * alquilerVehiculos.mvc.modelo.dominio.Cliente,
	 * alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo)
	 */
	public void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo) {
		comprobarExistenciaVehiculo(vehiculo);
		alquileres.cerrarAlquiler(cliente, vehiculo);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see
	 * alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#obtenerAlquileres()
	 */
	@Override
	public List<Alquiler> obtenerAlquileres() {
		return alquileres.getAlquileres();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#leerClientes()
	 */
	@Override
	public void leerClientes() {
		clientes.leerClientes();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#escribirClientes()
	 */
	@Override
	public void escribirClientes() {
		clientes.escribirClientes();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#leerVehiculos()
	 */
	@Override
	public void leerVehiculos() {
		vehiculos.leerVehiculos();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see
	 * alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#escribirVehiculos()
	 */
	@Override
	public void escribirVehiculos() {
		vehiculos.escribirVehiculos();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#leerAlquileres()
	 */
	@Override
	public void leerAlquileres() {
		alquileres.leerAlquileres();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see
	 * alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#escribirAlquileres()
	 */
	@Override
	public void escribirAlquileres() {
		alquileres.escribirAlquileres();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#
	 * obtenerAlquileresAbiertos()
	 */
	@Override
	public List<Alquiler> obtenerAlquileresAbiertos() {
		return alquileres.obtenerAlquileresAbiertos();
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#
	 * obtenerAlquileresCliente(java.lang.String)
	 */
	@Override
	public List<Alquiler> obtenerAlquileresCliente(String dni) {
		return alquileres.obtenerAlquileresCliente(dni);
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#
	 * obtenerAlquileresVehiculo(java.lang.String)
	 */
	@Override
	public List<Alquiler> obtenerAlquileresVehiculo(String matricula) {
		return alquileres.obtenerAlquileresVehiculo(matricula);
	}

}
