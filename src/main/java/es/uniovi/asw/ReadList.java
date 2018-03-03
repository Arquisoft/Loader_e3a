package es.uniovi.asw;

import java.util.ArrayList;

import dao.Agente;

public interface ReadList {
	public ArrayList<Agente> leerAgentesdelExcel(ArrayList<Agente> agentes, String ruta);
}
