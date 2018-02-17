package es.uniovi.asw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hsqldb.jdbc.JDBCDriver;

import dao.Agente;

public class BBDD {

	/**
	 * Metodo que establece conexión con la base de datos local
	 * 
	 * @return objeto conexion
	 */
	public static Connection crearConexion() {
		Connection conexion = null;
		try {
			DriverManager.registerDriver(new JDBCDriver());
			//String url = "jdbc:hsqldb:file:./DDBB/data/test";
			// Descomentar para probar los test en local.
			 String url = "jdbc:hsqldb:hsql://localhost/";
			String user = "SA";
			String pass = "";
			conexion = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}

	/**
	 * Añade ciudadanos a la base de datos
	 * 
	 * @param ciudadanos,
	 *            lista de ciudadanos a insertar en la base de datos
	 */
	public static void insertarAgente(List<Agente> agentes) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into AGENTE ");
			sb.append("(nombre, localizacion, email, identificador, tipo, password) ");
			sb.append("values (?,?,?,?,?,?)");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			for (Agente agen : agentes) {
				ps.setString(1, agen.getNombre());
				ps.setString(2, agen.getLocalizacion());
				ps.setString(3, agen.getEmail());
				ps.setString(4, agen.getIdentificador());
				ps.setString(5, String.valueOf(agen.getTipo()));
				ps.setString(6, agen.getPassword());
				ps.execute();
			}
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Elimina 1 ciudadano cuyo dni se introduce como parametro
	 * 
	 * @param dni
	 *            del ciudadano a borrar
	 */
	public static void eliminarAgente(String dni) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("delete from AGENTE ");
			sb.append("where identificador = ?");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setString(1, dni);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.print("Seguramente es porque el formato dni es incorrecto");
			e.printStackTrace();
		}

	}

	/**
	 * Se actualizan los datos de un usuario. Los nuevos datos se añaden a un objeto
	 * ciudadano que sera el que se use para actualizar los datos (se basa en el
	 * dni)
	 * 
	 * @param ciudadano
	 *            a actualizar
	 */
	public static void updateAgente(Agente ciudadano) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE AGENTE "
					+ "set nombre= ?, localizacion= ?, email= ?, identificador= ?, tipo= ?, password= ?"
					+ "where dni=?");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setString(1, ciudadano.getNombre());
			ps.setString(2, ciudadano.getLocalizacion());
			ps.setString(3, ciudadano.getEmail());
			ps.setString(4, ciudadano.getIdentificador());
			ps.setInt(7, ciudadano.getTipo());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("no existe el ciudadano especificado");
			e.printStackTrace();
		}
	}

	public static Agente obtenerAgente(String dni) {
		Connection con = crearConexion();
		String consulta = "SELECT c.* FROM ciudadano c WHERE c.identificador = ?";
		Agente ciudadano = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(consulta);
			ps.setString(1, dni);
			rs = ps.executeQuery();
			while (rs.next()) {
				ciudadano = new Agente(rs.getString("nombre"),
						rs.getString("localizacion"), rs.getString("email"),
		rs.getString("identificador"), rs.getInt("tipo"));
				ciudadano.setPassword(rs.getString("password"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ciudadano;
	}

	/**
	 * Metodo que guarda en la base de datos la contraseña asociada al usuario que
	 * se identifica con el dni
	 */
	public static void guardaarPasswordUsuario(String dni, String password) {
		Connection con = crearConexion();
		String consulta = "update Agente set password = ? where dni = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(consulta);
			ps.setString(1, password);
			ps.setString(2, dni);
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina todos los ciudadanos
	 */
	public static void eliminarAgentes() {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("delete from AGENTE ");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.print("Error al borrar todos los ciudadanos");
			e.printStackTrace();
		}
	}

}
