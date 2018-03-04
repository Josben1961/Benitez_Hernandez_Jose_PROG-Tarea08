/*
 * 
 */
package alquilerVehiculos.mvc.modelo;

import java.util.List;

import alquilerVehiculos.mvc.modelo.dominio.Alquiler;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

public interface IModeloAlquilerVehiculos {

	void anadirCliente(Cliente cliente);

	void borrarCliente(String dni);

	Cliente buscarCliente(String dni);

	List<Cliente> obtenerClientes();

	void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo);

	void borrarVehiculo(String matricula);

	Vehiculo buscarVehiculo(String matricula);

	List<Vehiculo> obtenerVehiculos();

	void abrirAlquiler(Cliente cliente, Vehiculo vehiculo);

	void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo);

	List<Alquiler> obtenerAlquileres();
	
	void leerClientes();

	void escribirClientes();

	void leerVehiculos();
	
	void escribirVehiculos();

	void escribirAlquileres();

	void leerAlquileres();
	
	List<Alquiler> obtenerAlquileresAbiertos();
	 
	List<Alquiler> obtenerAlquileresCliente(String dni);
	
}