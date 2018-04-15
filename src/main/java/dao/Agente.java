package dao;

import java.util.Random;

import es.uniovi.asw.Csv;

public class Agente {
	private String nombre, localizacion, email, identificador;

	private String tipo;
	private String contrasena;

	public Agente(String nombre, String localizacion, String email, String identificador, String tipo) {
		
		this.nombre = nombre;
		this.localizacion = localizacion;
	
		this.email = email;
		this.identificador = identificador;
		this.tipo = tipo;
		this.contrasena = contrasena;
		crearPassword();
	}



	@Override
	public String toString() {
		return "Agente [nombre=" + nombre + ", localizacion=" + localizacion + ", email=" + email + ", identificador="
				+ identificador + ", tipo=" + tipo + ", contrasena=" + contrasena + "]";
	}



	public String getLocalizacion() {
		return localizacion;
	}



	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
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

}
