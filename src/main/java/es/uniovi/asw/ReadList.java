package es.uniovi.asw;

import java.util.ArrayList;

import dao.Agente;

public interface ReadList {
	/**
	 * Metodo que lee los agentes del excel
	 * 
	 * @param agentes
	 *            lista donde se van a añadir los agentes
	 * @param ruta
	 *            ruta del archivo de excel
	 * @return la lista donde se añadieron los agentes
	 */
	public ArrayList<Agente> leerAgentesdelExcel(ArrayList<Agente> agentes, String ruta);
}
