
package alquilerVehiculos.mvc.controlador;

import java.util.List;

import alquilerVehiculos.mvc.modelo.dominio.Alquiler;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

public interface IControladorAlquilerVehiculos {

	void comenzar();

	void salir();

	void anadirCliente(Cliente cliente);

	void borrarCliente(String dni);

	Cliente buscarCliente(String dni);

	List<Cliente> obtenerClientes();

	void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo);

	void borrarVehiculo(String matricula);

	Vehiculo buscarVehiculo(String matricula);

	List<Vehiculo> obtenerVehiculos();

	void abrirAlquiler( Cliente cliente, Vehiculo vehiculo);

	void cerrarAlquiler( Cliente cliente, Vehiculo vehiculo);

	Alquiler[] obtenerAlquileres();


}