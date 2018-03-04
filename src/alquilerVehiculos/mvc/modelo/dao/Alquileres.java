
package alquilerVehiculos.mvc.modelo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;
import alquilerVehiculos.mvc.modelo.dominio.Alquiler;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

// TODO: Auto-generated Javadoc
/**
 * The Class Alquileres.
 */
public class Alquileres {

	/** The alquileres. */
	private List<Alquiler> alquileres;

	/** The fichero alquileres. */
	private final String FICHERO_ALQUILERES = "datos/alquileres.dat";

	/**
	 * Instantiates a new alquileres.
	 */
	public Alquileres() {
		alquileres = new Vector<Alquiler>();
	}

	/**
	 * Gets the alquileres.
	 *
	 * @return the alquileres
	 */
	public List<Alquiler> getAlquileres() {
		return new Vector<Alquiler>(alquileres);
	}

	/**
	 * Leer alquileres.
	 */
	public void leerAlquileres() {
		File fichero = new File(FICHERO_ALQUILERES);
		ObjectInputStream entrada;
		try {
			entrada = new ObjectInputStream(new FileInputStream(fichero));
			try {
				while (true) {
					Alquiler alquiler = (Alquiler) entrada.readObject();
					alquileres.add(alquiler);
				}

			} catch (EOFException eo) {
				entrada.close();
				System.out.println("Fichero trabajos leído satisfactoriamente.");
			} catch (ClassNotFoundException e) {
				System.out.println("No puedo encontrar la clase que tengo que leer.");
			} catch (IOException e) {
				System.out.println("Error inesperado de Entrada/Salida.");
			}
		} catch (IOException e) {
			System.out.println("Imposible abrir el fihero de alquileres.");
		}
	}

	/**
	 * Escribir alquileres.
	 */
	public void escribirAlquileres() {
		File fichero = new File(FICHERO_ALQUILERES);
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			for (Alquiler alquiler : alquileres)
				salida.writeObject(alquiler);
			salida.close();
			System.out.println("Fichero alquileres escrito correctamente ");
		} catch (FileNotFoundException e) {
			System.out.println("Imposible crear el fichero de alquileres");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}

	/**
	 * Abrir alquiler.
	 *
	 * @param cliente
	 *            the cliente
	 * @param vehiculo
	 *            the vehiculo
	 */
	public void abrirAlquiler(Cliente cliente, Vehiculo vehiculo) {
		compruebaExistenciaOtroAbierto(cliente, vehiculo);
		alquileres.add(new Alquiler(cliente, vehiculo));
	}

	/**
	 * Comprueba existencia otro abierto.
	 *
	 * @param cliente
	 *            the cliente
	 * @param vehiculo
	 *            the vehiculo
	 */
	private void compruebaExistenciaOtroAbierto(Cliente cliente, Vehiculo vehiculo) {
		int indice = 0;
		while (indiceNoSuperaTamano(indice)) {
			if (alquileres.get(indice).getVehiculo().getMatricula().equals(vehiculo.getMatricula())
					&& alquileres.get(indice).getDias() == 0)
				throw new ExcepcionAlquilerVehiculos("Ya existe un trabajo abierto para este vehículo");
			else
				indice++;
		}
	}

	/**
	 * Indice no supera tamano.
	 *
	 * @param indice
	 *            the indice
	 * @return true, if successful
	 */
	private boolean indiceNoSuperaTamano(int indice) {
		return indice < alquileres.size();
	}

	/**
	 * Cerrar alquiler.
	 *
	 * @param cliente
	 *            the cliente
	 * @param vehiculo
	 *            the vehiculo
	 */
	public void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo) {
		int indice = buscarAlquilerAbierto(cliente, vehiculo);
		if (indiceNoSuperaTamano(indice)) {
			alquileres.get(indice).cerrar();
			vehiculo.setDisponible(true);
		} else {
			throw new ExcepcionAlquilerVehiculos("No hay ningún alquiler abierto para ese vehículo");
		}
	}

	/**
	 * Buscar alquiler abierto.
	 *
	 * @param cliente
	 *            the cliente
	 * @param vehiculo
	 *            the vehiculo
	 * @return the int
	 */
	private int buscarAlquilerAbierto(Cliente cliente, Vehiculo vehiculo) {
		int indice = 0;
		boolean alquilerEncontrado = false;
		while (indiceNoSuperaTamano(indice) && !alquilerEncontrado) {
			if (alquileres.get(indice).getVehiculo().getMatricula().equals(vehiculo.getMatricula())
					&& alquileres.get(indice).getDias() == 0)
				alquilerEncontrado = true;
			else
				indice++;
		}
		return alquilerEncontrado ? indice : alquileres.size();
	}

	/**
	 * Obtener alquileres abiertos.
	 *
	 * @return the list
	 */
	public List<Alquiler> obtenerAlquileresAbiertos() {
		int posicion = 0;
		List<Alquiler> alquileresAbiertos = new Vector<Alquiler>();

		while (posicion < alquileres.size()) {
			if (alquileres.get(posicion).getDias() == 0) {
				alquileresAbiertos.add(alquileres.get(posicion));
			}
			posicion++;
		}
		return alquileresAbiertos;

	}

	/**
	 * Obtener alquileres cliente.
	 *
	 * @param dni
	 *            the dni
	 * @return the list
	 */
	public List<Alquiler> obtenerAlquileresCliente(String dni) {
		int posicion = 0;
		List<Alquiler> alquileresCliente = new Vector<Alquiler>();

		while (posicion < alquileres.size()) {
			if (alquileres.get(posicion).getCliente().getDni().equals(dni)) {
				alquileresCliente.add(alquileres.get(posicion));
			}
			posicion++;
		}
		return alquileresCliente;

	}

	/**
	 * Obtener alquileres vehiculo.
	 *
	 * @param matricula
	 *            the matricula
	 * @return the list
	 */
	public List<Alquiler> obtenerAlquileresVehiculo(String matricula) {
		int posicion = 0;
		List<Alquiler> alquileresVehiculo = new Vector<Alquiler>();

		while (posicion < alquileres.size()) {
			if (alquileres.get(posicion).getVehiculo().getMatricula().equals(matricula)) {
				alquileresVehiculo.add(alquileres.get(posicion));
			}
			posicion++;
		}
		return alquileresVehiculo;

	}

}
