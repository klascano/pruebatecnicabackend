package cr.fi.proamerica.pruebatecnica.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cr.fi.proamerica.pruebatecnica.api.model.Usuario;

public interface UsuarioRepositorio extends MongoRepository<Usuario, Long> {
		
    Usuario findByUsuario(String usuario);

}
