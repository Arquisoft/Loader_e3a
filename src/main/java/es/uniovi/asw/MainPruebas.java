package es.uniovi.asw;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Agente;

public class MainPruebas {

	public static void main(String[] args) {
		System.out.print("Inserte ruta:");
		Scanner scanner = new Scanner(System.in);
		String ruta = scanner.nextLine();
		ruta = ruta.replace("\"", "");
		scanner.close();
		ArrayList<Agente> agentes = new ArrayList<Agente>();
		Leer.Agentes(agentes, ruta);
		for (Agente Agente : agentes) {
			System.out.println("imprimiendo desde el main " + Agente);
		}

		BBDD.eliminarAgentes();
		BBDD.insertarAgente(agentes);
		Agente otro = BBDD.obtenerAgente(agentes.get(0).getIdentificador());
		System.out.println(otro);
	}

}
