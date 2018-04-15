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
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import dao.Agente;

public class BBDD {

	public BBDD() {

	}

	/**
	 * Metodo que establece conexión con la base de datos local
	 * 
	 * @return objeto conexion
	 */
	public DB crearConexion() {

		DB db = null;
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("ds221339.mlab.com", 21339);
			db = mongoClient.getDB("agentsdb");
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
		DBCollection table = db.getCollection("agents");
		BasicDBObject document = new BasicDBObject();
		for (Agente agen : agentes) {
			document.put("nombre", agen.getNombre());
			document.put("contrasena", agen.getContrasena());
			document.put("kind", agen.getTipo());
			document.put("identificador", agen.getIdentificador());
			document.put("localizacion", agen.getLocalizacion());
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
		DB db = crearConexion();
		DBCollection collection = db.getCollection("agents");
		BasicDBObject document = new BasicDBObject();
		document.put("identificador", identificador);
		collection.findAndRemove(document);
	}

	/**
	 * Se actualizan los datos de un usuario. Los nuevos datos se añaden a un
	 * objeto agente que sera el que se use para actualizar los datos (se basa
	 * en el identificador)
	 * 
	 * @param agente
	 *            a actualizar
	 */
	public void updateAgente(Agente agen) {
		DB db = crearConexion();
		DBCollection collection = db.getCollection("agents");
		BasicDBObject searchObject = new BasicDBObject();

		searchObject.put("identificador", agen.getIdentificador());

		DBObject modifiedObject = new BasicDBObject();
		modifiedObject.put("nombre", agen.getNombre());
		modifiedObject.put("contrasena", agen.getContrasena());
		modifiedObject.put("kind", agen.getTipo());
		modifiedObject.put("identificador", agen.getIdentificador());
		modifiedObject.put("latitud", agen.getLocalizacion());
		modifiedObject.put("email", agen.getEmail());
		collection.update(searchObject, modifiedObject, true, false);

	}

	public DBObject obtenerAgente(String identificador) {

		DB db = crearConexion();
		DBCollection collection = db.getCollection("agents");
		BasicDBObject searchObject = new BasicDBObject();
		searchObject.put("identificador", identificador);
		DBObject search = collection.findOne(searchObject);
		return search;
	}

	/**
	 * Elimina todos los agentes
	 */
	public void eliminarAgentes() {
		DB db = crearConexion();
		DBCollection collection = db.getCollection("agents");
		collection.drop();
	}

}
