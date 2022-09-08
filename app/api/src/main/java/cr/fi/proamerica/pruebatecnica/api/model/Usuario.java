package cr.fi.proamerica.pruebatecnica.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("usuarios")
public class Usuario {	

	@Id	
	private String id;

	private String usuario;
		
	private String contrasena;

	private String rol;
	
	public Usuario() {
	}

	public Usuario(String id, String usuario, String contrasena, String rol) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
		
}