package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.Agente;

public class AplicationTest {
	private BBDD bbdd;

	@Before
	public void before() {
		bbdd = new BBDD("SA", "");
		bbdd.eliminarAgentes();
	}

	@Test
	public void testCargaFicherosCSV() {
		
		assertNotNull(Csv.getHashMAp());
		
		Csv.leerFicheroMaestro("./src/main/java/es/uniovi/asw/tipos.csv");
		assertEquals("Person", Csv.csvmaestro.get(1));
		assertEquals("Entity", Csv.csvmaestro.get(2));
		assertEquals("Sensor", Csv.csvmaestro.get(3));
		

	}

	@Test
	public void testCargaFicherosXLSX() {

		ArrayList<Agente> agentes = new ArrayList<Agente>();
		Leer leer = new Leer();
		leer.leerAgentesdelExcel(agentes, "./src/main/java/es/uniovi/asw/agentes.xlsx");
		assertEquals("78569544S", agentes.get(0).getIdentificador());
		assertEquals("Pedro", agentes.get(0).getNombre());
		assertEquals(1, agentes.get(0).getTipo());
		assertEquals("pedro@hotmail.com", agentes.get(0).getEmail());
		assertEquals("74523699Z", agentes.get(1).getIdentificador());
		assertEquals("#456123", agentes.get(2).getIdentificador());
		assertEquals("#74185", agentes.get(3).getIdentificador());
		assertEquals("B-78458599", agentes.get(4).getIdentificador());
		assertEquals("B-74741255", agentes.get(5).getIdentificador());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void addAgenteTest() {
		List<Agente> agentes = new ArrayList<Agente>();

		Agente c = new Agente("Pepe", "locprueba", "email@prueba", "identifPrueba", 1);
		agentes.add(c);

		bbdd.insertarAgente(agentes);

		Agente cBD = bbdd.obtenerAgente("identifPrueba");
		assertNotNull(cBD);
		assertEquals("Pepe", cBD.getNombre());
		assertEquals("locprueba", cBD.getLocalizacion());
		assertEquals("email@prueba", cBD.getEmail());
		assertEquals("identifPrueba", cBD.getIdentificador());

		c.setEmail("otroemail@.com");

		bbdd.updateAgente(c);
		cBD = bbdd.obtenerAgente("identifPrueba");
		assertNotNull(cBD);
		assertEquals("Pepe", cBD.getNombre());
		assertEquals("otroemail@.com", cBD.getEmail());
		assertEquals("identifPrueba", cBD.getIdentificador());
		c.setNombre("Edu");
		c.setLocalizacion("Australia");

		bbdd.updateAgente(c);
		cBD = bbdd.obtenerAgente("identifPrueba");
		assertEquals("Edu", cBD.getNombre());
		assertEquals("Australia", cBD.getLocalizacion());
		c.setTipo(2);
		
		bbdd.updateAgente(c);
		cBD = bbdd.obtenerAgente("identifPrueba");
		assertNotNull(cBD);
		assertEquals(2, cBD.getTipo());
		assertEquals("Agente:\n\t Nombre: "+c.getNombre()+"\n\t Localizacion: "
		+ c.getLocalizacion()+"\n\t Email: "+ c.getEmail()+"\n\t Identificador: "+ c.getIdentificador()
		+"\n\t Tipo: "+ Csv.getHashMAp().get(c.getTipo())+"\n\t Contraseña: "+ c.getPassword(), cBD.toString());
		
		bbdd.eliminarAgente("identifPrueba");
		cBD = bbdd.obtenerAgente("identifPrueba");
		assertNull(cBD);
		
	}

	@Test
	public void testCargarCSS() {
		// leemos y cargamos el fichero
		ArrayList<Agente> ciudadanos = new ArrayList<Agente>();
		Leer leer = new Leer();

		ciudadanos = leer.leerAgentesdelExcel(ciudadanos, "./src/main/java/es/uniovi/asw/agentes.xlsx");

		// probamos con el primer Agente
		Agente c = ciudadanos.get(0);
		assertEquals("Pedro", c.getNombre());
		assertEquals("pedro@hotmail.com", c.getEmail());
		assertEquals("78569544S", c.getIdentificador());

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCrearCorreo() {
		Agente c = new Agente("Pepe", "locprueba", "email@prueba", "identifPrueba", 1);

		assertNotNull(c.getPassword());

		String rutaFichero = "./correos/" + c.getNombre() + ".txt";
		File fichero = new File(rutaFichero);
		assertFalse(fichero.exists());

		CrearCorreo.mandarCorreo(c);

		fichero = new File(rutaFichero);
		assertTrue(fichero.exists());

		fichero.delete();
		
		
		Agente d = new Agente(null, "prueba", "email@prueba2", "identifPrueba2", 2);

		assertNotNull(d.getPassword());

		String rutaFichero2 = "./correos/" + c.getNombre() + ".txt";
		File fichero2 = new File(rutaFichero2);
		assertFalse(fichero2.exists());

		CrearCorreo.mandarCorreo(d);

		fichero = new File(rutaFichero);
		assertFalse(fichero.exists());
	}

}
