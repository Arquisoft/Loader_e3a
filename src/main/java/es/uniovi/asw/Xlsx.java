package es.uniovi.asw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.Agente;

public class Xlsx {

	/**
	 * Metodo que lee los agentes del excel
	 * 
	 * @param agentes
	 *            lista donde se van a añadir los agentes
	 * @param ruta
	 *            ruta del archivo de excel
	 * @return la lista donde se añadieron los agentes
	 */
	public static ArrayList<Agente> leerAgentes(ArrayList<Agente> agentes, String ruta) {
		try {
			FileInputStream file = new FileInputStream(new File(ruta));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				ArrayList<Object> aux = new ArrayList<Object>();
				for (int i = 0; i < 5; i++) {
					aux.add(row.getCell(i) != null ? row.getCell(i).toString() : "");
				}

				int tipo = (int) Double.parseDouble(aux.get(4).toString());
				String localizacion = "";
				Agente agente;
				if (!aux.get(1).equals("")) {
					localizacion = aux.get(1).toString();
					String[] s = localizacion.split(";");
					agente = new Agente(aux.get(0).toString(), s[0], s[1], aux.get(2).toString(), aux.get(3).toString(),
							Csv.getHashMAp().get(tipo));
				} else {

					agente = new Agente(aux.get(0).toString(), aux.get(2).toString(), aux.get(3).toString(),
							Csv.getHashMAp().get(tipo));
				}

				agentes.add(agente);

			}

			file.close();
			workbook.close();
		} catch (Exception e) {
			String rutaa = "./errores" + e.getMessage() + ".txt";
			File archivo = new File(rutaa);
			BufferedWriter bw;

			try {
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write("Error al leer del excel xlsx Mensaje: " + e.getMessage());
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.err.println("Error al crear fichero de errores en XLSX " + e1.getMessage());
			}

			System.err.println("Error al leer del excel xlsx Mensaje: " + e.getMessage());
		}
		return agentes;
	}

}