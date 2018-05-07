package es.uniovi.asw;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.hsqldb.jdbc.JDBCDriver;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dao.Agente;

public class BBDD {

	public BBDD() {
		crearConexion();
	}

	/**
	 * Metodo que establece conexión con la base de datos local
	 * 
	 * @return objeto conexion
	 */
	public MongoDatabase crearConexion() {

		MongoClientURI uri = new MongoClientURI("mongodb://adload:adload@ds221339.mlab.com:21339/agentsdb");
		MongoClient client = new MongoClient(uri);
		MongoDatabase database = client.getDatabase("agentsdb");

		return database;
	}

	/**
	 * Añade agentes a la base de datos
	 * 
	 * @param agentes,
	 *            lista de agentes a insertar en la base de datos
	 */
	public void insertarAgente(List<Agente> agentes) {
		MongoDatabase db = crearConexion();
		MongoCollection<Document> table = db.getCollection("agents");

		for (Agente agen : agentes) {
			Document document = new Document();
			document.put("nombre", agen.getNombre());
			document.put("contrasena", agen.getContrasena());
			document.put("kind", agen.getTipo());
			document.put("identificador", agen.getIdentificador());
			document.put("longitud", agen.getLongitud());
			document.put("latitud", agen.getLatitud());
			document.put("email", agen.getEmail());
			table.insertOne(document);

		}
	}

	/**
	 * Elimina 1 agente cuyo identificador se introduce como parametro
	 * 
	 * @param identificador
	 *            del agente a borrar
	 */
	public void eliminarAgente(String identificador) {
		MongoDatabase db = crearConexion();
		MongoCollection<Document> table = db.getCollection("agents");
		Document document = new Document();
		document.put("identificador", identificador);
		table.findOneAndDelete(document);
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
		MongoDatabase db = crearConexion();
		MongoCollection<Document> collection = db.getCollection("agents");

		Document searchObject = obtenerAgente(agen.getIdentificador());

		Bson modifiedObject = new Document("nombre", agen.getNombre());
		Bson update = new Document("$set", modifiedObject);
		collection.updateOne(searchObject, update);
		searchObject = obtenerAgente(agen.getIdentificador());

		modifiedObject = new Document("contrasena", agen.getContrasena());
		update = new Document("$set", modifiedObject);
		collection.updateOne(searchObject, update);
		searchObject = obtenerAgente(agen.getIdentificador());

		modifiedObject = new Document("kind", agen.getTipo());
		update = new Document("$set", modifiedObject);
		collection.updateOne(searchObject, update);
		searchObject = obtenerAgente(agen.getIdentificador());

		modifiedObject = new Document("identificador", agen.getIdentificador());
		update = new Document("$set", modifiedObject);
		collection.updateOne(searchObject, update);
		searchObject = obtenerAgente(agen.getIdentificador());

		modifiedObject = new Document("longitud", agen.getLongitud());
		update = new Document("$set", modifiedObject);
		collection.updateOne(searchObject, update);
		searchObject = obtenerAgente(agen.getIdentificador());

		modifiedObject = new Document("latitud", agen.getLatitud());
		update = new Document("$set", modifiedObject);
		collection.updateOne(searchObject, update);
		searchObject = obtenerAgente(agen.getIdentificador());

		modifiedObject = new Document("email", agen.getEmail());
		update = new Document("$set", modifiedObject);
		collection.updateOne(searchObject, update);

	}

	public Document obtenerAgente(String identificador) {

		MongoDatabase db = crearConexion();
		MongoCollection<Document> collection = db.getCollection("agents");

		Document searchObject = new Document();
		searchObject.put("identificador", identificador);
		Document search = collection.find(searchObject).first();
		return search;
	}

	/**
	 * Elimina todos los agentes
	 */
	public void eliminarAgentes() {
		MongoDatabase db = crearConexion();
		MongoCollection<Document> collection = db.getCollection("agents");
		collection.drop();
	}

}
