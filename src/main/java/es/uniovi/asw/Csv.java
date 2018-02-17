package es.uniovi.asw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.Agente;

public class Csv implements Formatos {

	@Override
	public ArrayList<Agente> leerAgentes(ArrayList<Agente> Agentes, String ruta) {
		try {
			FileInputStream is = new FileInputStream(ruta);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader buffReader = new BufferedReader(isr);

			String str = "";

			while ((str = buffReader.readLine()) != null) {
				String[] trozos = str.split(";");
				if (trozos[0].equals("Nombre"))
					continue;
				Agente agente = new Agente(trozos[0], trozos[1], trozos[2], trozos[3], Integer.valueOf(trozos[4]));
				Agentes.add(agente);
			}

			buffReader.close();

		} catch (Exception e) {
			System.out.println("Error leyendo el fichero csv");
		}
		return Agentes;
	}
}
