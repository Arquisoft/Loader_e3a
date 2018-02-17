package es.uniovi.asw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dao.Agente;

public class MainPruebas {

	public static void main(String[] args) {
		//Probar lectura del xlsx
		System.out.println("Vamos a cargar los agentes");
		System.out.print("Inserte ruta xlsx:");
		Scanner scanner = new Scanner(System.in);
		String ruta = scanner.nextLine();
		ruta = ruta.replace("\"", "");
		
		ArrayList<Agente> agentes = new ArrayList<Agente>();
		Leer.leerAgentesdelExcel(agentes, ruta);
		for (Agente Agente : agentes) {
			System.out.println("imprimiendo desde el main " + Agente.getIdentificador());
		}

		BBDD.eliminarAgentes();
		BBDD.insertarAgente(agentes);
		Agente otro = BBDD.obtenerAgente(agentes.get(0).getIdentificador());
		System.out.println(otro.getIdentificador());
		
		//Probar lectura del csv
		System.out.println("Vamos a cargar los tipos de agentes con el fichero maestro csv");
		System.out.print("Inserte ruta csv:");
		String ruta2 = scanner.nextLine();
		ruta2 = ruta2.replace("\"", "");
		scanner.close();
		Csv csv= new Csv();
		csv.leerFicheroMaestro( ruta2);
		HashMap hm=csv.getHashMAp();
		
		

	}

}
