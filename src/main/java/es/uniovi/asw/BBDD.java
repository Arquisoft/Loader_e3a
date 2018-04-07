package es.uniovi.asw;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hsqldb.jdbc.JDBCDriver;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import dao.Agente;

public class BBDD {
	private String user;
	private String pass;

	public BBDD(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	/**
	 * Metodo que establece conexión con la base de datos local
	 * 
	 * @return objeto conexion
	 */
	public DB crearConexion() {
		Connection conexion = null;
		DB db = null;
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient();
			db = mongoClient.getDB("agents");
			boolean auth = db.authenticate("admin", "asw2".toCharArray());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return db;
	}

	/**
	 * Añade agentes a la base de datos
	 * 
	 * @param agentes,
	 *            lista de agentes a insertar en la base de datos
	 */
	public void insertarAgente(List<Agente> agentes) {
		DB db = crearConexion();
		DBCollection table = db.getCollection("agente");
		BasicDBObject document = new BasicDBObject();
		for (Agente agen : agentes) {
			document.put("nombre", agen.getNombre());
			document.put("contrasena", agen.getContrasena());
			document.put("kind", agen.getTipo());
			document.put("identificador", agen.getIdentificador());
			document.put("latitud", agen.getLatitud());
			document.put("logitud", agen.getLongitud());
			document.put("email", agen.getEmail());
			table.insert(document);

		}
	}

	/**
	 * Elimina 1 agente cuyo identificador se introduce como parametro
	 * 
	 * @param identificador
	 *            del agente a borrar
	 */
	public void eliminarAgente(String identificador) {
	

	}

	/**
	 * Se actualizan los datos de un usuario. Los nuevos datos se añaden a un
	 * objeto agente que sera el que se use para actualizar los datos (se basa
	 * en el identificador)
	 * 
	 * @param agente
	 *            a actualizar
	 */
	public void updateAgente(Agente agente) {
		
	}

	public Agente obtenerAgente(String identificador) {
		return null;
		
	}

	/**
	 * Metodo que guarda en la base de datos la contraseña asociada al usuario
	 * que se identifica con el identificador
	 */
	public void guardaarPasswordUsuario(String identificador, String password) {

	}

	/**
	 * Elimina todos los agentes
	 */
	public void eliminarAgentes() {
	
	}

}
