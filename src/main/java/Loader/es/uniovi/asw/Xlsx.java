package Loader.es.uniovi.asw;

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

import Loader.dao.Agente;

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
			List<XSSFCell> agents;
			XSSFCell celda;
			Row row;

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				agents = new ArrayList<XSSFCell>();

				row = rowIterator.next();

				Iterator<Cell> celdas = row.cellIterator();

				while (celdas.hasNext()) {
					celda = (XSSFCell) celdas.next();
					agents.add(celda);
				}

				String localizacion = "";
				Agente agente;
				if (agents.get(1).toString().contains(";")) {
					String tip = agents.get(4).toString();
					int tipo = (int) Double.parseDouble(tip);
					localizacion = agents.get(1).toString();
					String[] s = localizacion.split(";");
					agente = new Agente(agents.get(0).toString(), s[0], s[1], agents.get(2).toString(),
							agents.get(3).toString(), Csv.getHashMAp().get(tipo));
				} else {
					String tip = agents.get(4).toString();
					int tipo = (int) Double.parseDouble(tip);
					agente = new Agente(agents.get(0).toString(), agents.get(2).toString(), agents.get(3).toString(),
							Csv.getHashMAp().get(tipo));
				}
				agentes.add(agente);
			}

			file.close();
			workbook.close();
		} catch (FileNotFoundException e2) {
			try {
				File archivo = new File("./errores");
				BufferedWriter bw;
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write(e2.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (IOException e) {
			try {
				File archivo = new File("./errores");
				BufferedWriter bw;
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return agentes;
	}
}