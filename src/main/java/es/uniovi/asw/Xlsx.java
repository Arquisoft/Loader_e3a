package es.uniovi.asw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
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

		FileInputStream file;
		XSSFWorkbook workbook;
		try {
			file = new FileInputStream(ruta);
			try {
				workbook = new XSSFWorkbook(file);
				XSSFSheet sheet = workbook.getSheetAt(0);
				List<XSSFCell> user;
				XSSFCell cell;
				int i = 0;
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					user = new ArrayList<XSSFCell>();
					Row row = rowIterator.next();
					Iterator<Cell> cells = row.cellIterator();

					if (i > 0) {
						while (cells.hasNext()) {
							cell = (XSSFCell) cells.next();
							user.add(cell);
						}

						String localizacion = "";
						Agente agente;
						if (user.size() != 4) {
							int tipo = (int) Double.parseDouble(user.get(3).getRawValue());
							localizacion = user.get(1).toString();
							String[] s = localizacion.split(";");
							agente = new Agente(user.get(0).toString(), s[0], s[1], user.get(2).toString(),
									user.get(3).toString(), Csv.getHashMAp().get(tipo));
						} else {
							int tipo = (int) Double.parseDouble(user.get(4).getRawValue());
							agente = new Agente(user.get(0).toString(), user.get(2).toString(), user.get(3).toString(),
									Csv.getHashMAp().get(tipo));
						}
						agentes.add(agente);
					}

					i++;

				}
				file.close();
				workbook.close();

				return agentes;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
return agentes;
	}
}