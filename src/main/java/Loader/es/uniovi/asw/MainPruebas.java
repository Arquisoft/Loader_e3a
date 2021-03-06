package Loader.es.uniovi.asw;

import java.util.ArrayList;

import Loader.dao.Agente;

public class MainPruebas {

	public static void main(String[] args) {
		String ruta = args[0];
		String ruta2 = args[1];

		BBDD bbdd = new BBDD();
		// bbdd.eliminarAgentes();
		System.out.println("Vamos a cargar los tipos de agentes con el fichero maestro csv");
		Csv.leerFicheroMaestro(ruta);
		System.out.println("Fichero cargado correctamente");
		// Probar lectura del xlsx
		System.out.println("Vamos a cargar los agentes");
		ArrayList<Agente> agentes = new ArrayList<Agente>();
		Leer leer = new Leer();
		leer.leerAgentesdelExcel(agentes, ruta2);
		bbdd.insertarAgente(agentes);
		System.out.println("Listado de Agentes Cargados");
		for (Agente agente : agentes) {
			System.out.println(agente.toString());
		}
		System.out.println("Mandando correos...");
		for (Agente agente : agentes) {
			CrearCorreo.mandarCorreo(agente);
		}
		System.out.println("Correos creados en la carpeta correos");

	}
}
