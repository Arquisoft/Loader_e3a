package es.uniovi.asw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

			workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			List<XSSFCell> user;
			XSSFCell cell;
			Row row;

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				user = new ArrayList<XSSFCell>();
				
				row = rowIterator.next();
				
				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					cell = (XSSFCell) cells.next();
					user.add(cell);
				}

				String localizacion = "";
				Agente agente;
				if (user.get(1).toString().contains(";")) {
					String tip = user.get(4).toString();
					int tipo = (int) Double.parseDouble(tip);
					localizacion = user.get(1).toString();
					String[] s = localizacion.split(";");
					agente = new Agente(user.get(0).toString(), s[0], s[1], user.get(2).toString(),
							user.get(3).toString(), Csv.getHashMAp().get(tipo));
				} else {
					String tip = user.get(4).toString();
					int tipo = (int) Double.parseDouble(tip);
					agente = new Agente(user.get(0).toString(), user.get(2).toString(), user.get(3).toString(),
							Csv.getHashMAp().get(tipo));
				}
				agentes.add(agente);
			}

			file.close();
			workbook.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agentes;
	}
}