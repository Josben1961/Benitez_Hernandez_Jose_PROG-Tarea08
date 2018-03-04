
package alquilerVehiculos.mvc.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * Clase Clientes para gestión de clientes.
 */
public class Clientes {

	/** The clientes. */
	private Map<String, Cliente> clientes;

	/** The fichero clientes. */
	private final String FICHERO_CLIENTES = "datos/clientes.dat";

	/**
	 * Instantiates a new clientes.
	 */
	public Clientes() {
		clientes = new HashMap<String, Cliente>();
	}

	/**
	 * Gets the clientes.
	 *
	 * @return the clientes
	 */
	public List<Cliente> getClientes() {
		List<Cliente> clientesOrdenados = new Vector<Cliente>(clientes.values());
		Collections.sort(clientesOrdenados, new Comparator<Cliente>() {
			public int compare(Cliente uno, Cliente otro) {
				return uno.getNombre().compareTo(otro.getNombre());
			}
		});
		return clientesOrdenados;
	}

	/**
	 * Leer clientes.
	 */
	public void leerClientes() {
		File fichero = new File(FICHERO_CLIENTES);
		ObjectInputStream entrada;
		try {
			entrada = new ObjectInputStream(new FileInputStream(fichero));
			try {
				while (true) {
					Cliente cliente = (Cliente) entrada.readObject();
					clientes.put(cliente.getDni(), cliente);
				}
			} catch (EOFException eo) {
				entrada.close();
				System.out.println("Fichero clientes leído satisfactoriamente.");
				Cliente.aumentarUltimoIdentificador(calcularUltimoIdentificador());

			} catch (ClassNotFoundException e) {
				System.out.println("No puedo encontrar la clase que tengo que leer.");
			} catch (IOException e) {
				System.out.println("Error inesperado de Entrada/Salida.");
			}
		} catch (IOException e) {
			System.out.println("Imposible abrir el fihero de clientes.");
		}
	}

	/**
	 * Calcular ultimo identificador.
	 *
	 * @return the int
	 */
	private int calcularUltimoIdentificador() {
		int ultimoIdentificador = 0;
		for (Cliente cliente : clientes.values())
			if (cliente.getIdentificador() > ultimoIdentificador)
				ultimoIdentificador = cliente.getIdentificador();
		return ultimoIdentificador;
	}

	/**
	 * Escribir clientes.
	 */
	public void escribirClientes() {
		File fichero = new File(FICHERO_CLIENTES);
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			for (Cliente cliente : clientes.values())
				salida.writeObject(cliente);
			salida.close();
			System.out.println("Fichero clientes escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("Imposible crear el fichero de clientes");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}

	/**
	 * Anadir cliente.
	 *
	 * @param cliente
	 *            the cliente
	 */
	public void anadirCliente(Cliente cliente) {
		if (clientes.containsKey(cliente.getDni()))
			throw new ExcepcionAlquilerVehiculos("Ya existe un cliente con dicho DNI");
		else
			clientes.put(cliente.getDni(), cliente);
	}

	/**
	 * Borrar cliente.
	 *
	 * @param dni
	 *            the dni
	 */
	public void borrarCliente(String dni) {
		if (clientes.containsKey(dni))
			clientes.remove(dni);
		else
			throw new ExcepcionAlquilerVehiculos("El cliente a borrar no existe");
	}

	/**
	 * Buscar cliente.
	 *
	 * @param dni
	 *            the dni
	 * @return the cliente
	 */
	public Cliente buscarCliente(String dni) {
		if (clientes.containsKey(dni))
			return new Cliente(clientes.get(dni));
		else
			return null;
	}
}
