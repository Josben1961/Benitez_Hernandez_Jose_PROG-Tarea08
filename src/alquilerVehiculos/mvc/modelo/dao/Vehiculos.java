
package alquilerVehiculos.mvc.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import alquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

public class Vehiculos {

	private Map<String, Vehiculo> vehiculos;
	private final String FICHERO_VEHICULOS = "datos/vehiculos.dat";
	private Vehiculo vehiculo;
	private TipoVehiculo tipoVehiculo;

	public Vehiculos() {
		vehiculos = new HashMap<String, Vehiculo>();
	}

	public List<Vehiculo> getVehiculos() {
		return new Vector<Vehiculo>(vehiculos.values());
	}

	public void leerVehiculos() {
		File fichero = new File(FICHERO_VEHICULOS);
		ObjectInputStream entrada;
		try {
			entrada = new ObjectInputStream(new FileInputStream(fichero));
			try {
				while (true) {
					Vehiculo vehiculo = (Vehiculo) entrada.readObject();
					vehiculos.put(vehiculo.getMatricula(), vehiculo);
				}
				
			} catch (EOFException eo) {
				entrada.close();
				System.out.println("Fichero vehículos leído satisfactoriamente.");	
			} catch (ClassNotFoundException e) {
				System.out.println("Imposible encontrar el archivo que tengo que leer.");
			} catch (IOException e) {
				System.out.println("Error inesperado de Entrada/Salida.");
			}
		} catch (IOException e) {
			System.out.println("Imposible abrir el fihero de vehículos.");
		}
	}

	public void escribirVehiculos() {
		File fichero = new File(FICHERO_VEHICULOS);
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			for (Vehiculo vehiculo : vehiculos.values())
				salida.writeObject(vehiculo);
			salida.close();
			System.out.println("Fichero vehiculos escrito correctamente");
		} catch (FileNotFoundException e) {
			System.out.println("Imposible crear el fichero de vehículos");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}

	public void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
		if (vehiculos.containsKey(vehiculo.getMatricula()))
			throw new ExcepcionAlquilerVehiculos("Ya existe un vehículo con esa matríucula");
		else
			vehiculos.put(vehiculo.getMatricula(), vehiculo);
			vehiculo.setDisponible(true);
	}

	public void borrarVehiculo(String matricula) {
		if (vehiculos.containsKey(matricula)) 
			vehiculos.remove(matricula);
		else 
			throw new ExcepcionAlquilerVehiculos("El vehículo a borrar no existe");
	}

	public Vehiculo buscarVehiculo(String matricula) {
		if (vehiculos.containsKey(matricula)) 
			return tipoVehiculo.getInstancia(vehiculo.getMatricula(), vehiculo.getMarca(),
					vehiculo.getModelo(), vehiculo.getDatosTecnicos());
		else 
			throw new ExcepcionAlquilerVehiculos("El vehículo a borrar no existe");
	}
}
