package Loader.es.uniovi.asw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Csv {

	public static HashMap<Integer, String> csvmaestro = new HashMap<Integer, String>();

	public static HashMap<Integer, String> getHashMAp() {
		return csvmaestro;
	}

	/**
	 * Método que lee del fichero de tipos
	 * 
	 * @param ruta
	 */
	public static void leerFicheroMaestro(String ruta) {
		try {
			FileInputStream is = new FileInputStream(ruta);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader buffReader = new BufferedReader(isr);

			String str = "";

			while ((str = buffReader.readLine()) != null) {

				String[] trozos = str.split(",");
				int clave = Integer.valueOf(trozos[0]);
				csvmaestro.put(clave, trozos[1].toString());

			}

			buffReader.close();

		} catch (Exception e) {
			System.out.println("Error leyendo el fichero csv");
		}
	}
}
