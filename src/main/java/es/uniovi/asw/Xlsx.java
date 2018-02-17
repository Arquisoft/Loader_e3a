package es.uniovi.asw;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.Agente;

public class Xlsx implements Formatos {

	@Override
	public ArrayList<Agente> leerAgentes(ArrayList<Agente> agentes, String ruta) {
		try {
			FileInputStream file = new FileInputStream(new File(ruta));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				ArrayList<Object> aux = new ArrayList<Object>();
				for (int i = 0; i < 7; i++) {
					aux.add(row.getCell(i) != null ? row.getCell(i).toString() : null);
				}

				String nombre = row.getCell(0) != null ? row.getCell(0).toString() : null;
				if (nombre != null && nombre.equals("Nombre"))
					continue;


				Agente ciudadano = new Agente(aux.get(0).toString(), aux.get(1).toString(), aux.get(2).toString(),
						aux.get(4).toString(), Integer.valueOf(aux.get(5).toString()));
				
				agentes.add(ciudadano);
			}

			file.close();
			workbook.close();
		} catch (Exception e) {
			System.err.println("Error al leer del excel xlsx");
		}
		return agentes;
	}


}