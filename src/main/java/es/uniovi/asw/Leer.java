package es.uniovi.asw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dao.Agente;

public class Leer implements ReadList {

	/**
	 * Método que lee agentes del excel
	 */
	public ArrayList<Agente> leerAgentesdelExcel(ArrayList<Agente> agentes, String ruta) {
		ArrayList<Agente> aux = new ArrayList<Agente>();

		try {
			aux = Xlsx.leerAgentes(agentes, ruta);
		} catch (Exception e) {
			File archivo = new File("./errores");
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("Extensión no soportada");
		} catch (Error e) {
			try {
				File archivo = new File("./errores");
				BufferedWriter bw;
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Extensión no soportada");
		}
		return aux;
	}

}
