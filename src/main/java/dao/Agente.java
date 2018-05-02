package dao;

import java.util.Random;

import es.uniovi.asw.Csv;

public class Agente {
	private String nombre, latitud, email, identificador, longitud;

	private String tipo;
	private String contrasena;

	public Agente(String nombre, String latitud, String longitud, String email, String identificador, String tipo) {

		this.nombre = nombre;
		this.latitud = latitud;
		this.setLongitud(longitud);
		this.email = email;
		this.identificador = identificador;
		this.tipo = tipo;
		crearPassword();
	}

	public Agente(String nombre, String email, String identificador, String tipo) {

		this.nombre = nombre;

		this.email = email;
		this.identificador = identificador;
		this.tipo = tipo;
		crearPassword();
	}

	@Override
	public String toString() {
		return "Agente [nombre=" + nombre + ", latitud=" + latitud + ", email=" + email + ", identificador="
				+ identificador + ", longitud=" + longitud + ", tipo=" + tipo + ", contrasena=" + contrasena + "]";
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void crearPassword() {
		setContrasena("");
		char[] minusculas = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] mayusculas = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
		char[] numeros = "0123456789".toCharArray();
		char[] simbolos = "'¿?*+-$%".toCharArray();

		// Tiene una letra mayuscula
		Random random = new Random();
		int pos = random.nextInt(mayusculas.length);
		setContrasena(getContrasena() + mayusculas[pos]);

		// Tiene 5 letras minusculas
		for (int i = 0; i < 5; i++) {
			random = new Random();
			pos = random.nextInt(minusculas.length);
			setContrasena(getContrasena() + minusculas[pos]);
		}

		// Tiene un numero
		random = new Random();
		pos = random.nextInt(numeros.length);
		setContrasena(getContrasena() + numeros[pos]);

		// Tiene un simbolo especial
		random = new Random();
		pos = random.nextInt(simbolos.length);
		setContrasena(getContrasena() + simbolos[pos]);
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

}
