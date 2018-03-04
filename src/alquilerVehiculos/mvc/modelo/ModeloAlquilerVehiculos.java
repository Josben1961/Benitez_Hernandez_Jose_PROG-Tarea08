
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

public class ModeloAlquilerVehiculos implements IModeloAlquilerVehiculos {

	private Clientes clientes;

	private Vehiculos vehiculos;

	private Alquileres alquileres;

	public ModeloAlquilerVehiculos() {
		clientes = new Clientes();
		vehiculos = new Vehiculos();
		alquileres = new Alquileres();
	}

	public void anadirCliente(Cliente cliente) {
		clientes.anadirCliente(cliente);
	}

	public void borrarCliente(String dni) {
		clientes.borrarCliente(dni);
	}

	public Cliente buscarCliente(String dni) {
		return clientes.buscarCliente(dni);
	}
	
	@Override
	public List<Cliente> obtenerClientes() {
		return clientes.getClientes();
	}

	public void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
		vehiculos.anadirVehiculo(vehiculo, tipoVehiculo);
	}

	public void borrarVehiculo(String matricula) {
		vehiculos.borrarVehiculo(matricula);
	}

	public Vehiculo buscarVehiculo(String matricula) {
		return vehiculos.buscarVehiculo(matricula);
	}

	@Override
	public List<Vehiculo> obtenerVehiculos() {
		return vehiculos.getVehiculos();
	}

	public void abrirAlquiler(Cliente cliente, Vehiculo vehiculo) {
		comprobarExistenciaVehiculo(vehiculo);
		alquileres.abrirAlquiler(cliente, vehiculo);
	}
	
	private void comprobarExistenciaVehiculo(Vehiculo vehiculo) {
		if (vehiculos.buscarVehiculo(vehiculo.getMatricula()) == null)
			throw new ExcepcionAlquilerVehiculos("El vehículo no existe");
	}

	public void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo) {
		comprobarExistenciaVehiculo(vehiculo);
		alquileres.cerrarAlquiler(cliente, vehiculo);
	}

	@Override
	public List<Alquiler> obtenerAlquileres() {
		return alquileres.getAlquileres();
	}

	@Override
	public void leerClientes() {
		clientes.leerClientes();
	}

	@Override
	public void escribirClientes() {
		clientes.escribirClientes();
	}

	@Override
	public void leerVehiculos() {
		vehiculos.leerVehiculos();
	}

	@Override
	public void escribirVehiculos() {
		vehiculos.escribirVehiculos();
	}

	@Override
	public void leerAlquileres() {
		alquileres.leerAlquileres();
	}

	@Override
	public void escribirAlquileres() {
		alquileres.escribirAlquileres();	
	}
	
	 @Override
	    public List<Alquiler> obtenerAlquileresAbiertos() {
	        return alquileres.obtenerAlquileresAbiertos();
	}

}
