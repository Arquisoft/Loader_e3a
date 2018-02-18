package es.uniovi.asw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dao.Agente;

public class MainPruebas {

	public static void main(String[] args) {
		BBDD.eliminarAgentes();
		Scanner scanner = new Scanner(System.in);
		// Probar lectura del csv
		System.out.println("Vamos a cargar los tipos de agentes con el fichero maestro csv");
		System.out.print("Inserte ruta csv:");
		String ruta2 = scanner.nextLine();
		ruta2 = ruta2.replace("\"", "");
		Csv csv = new Csv();
		csv.leerFicheroMaestro(ruta2);
		System.out.println("Fichero cargado correctamente");
		// Probar lectura del xlsx
		System.out.println("Vamos a cargar los agentes");
		System.out.print("Inserte ruta xlsx:");

		String ruta = scanner.nextLine();
		ruta = ruta.replace("\"", "");
		scanner.close();
		ArrayList<Agente> agentes = new ArrayList<Agente>();
		Leer.leerAgentesdelExcel(agentes, ruta);
		BBDD.insertarAgente(agentes);
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
