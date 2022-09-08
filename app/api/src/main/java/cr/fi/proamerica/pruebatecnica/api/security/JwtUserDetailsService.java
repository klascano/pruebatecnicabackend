package cr.fi.proamerica.pruebatecnica.api.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cr.fi.proamerica.pruebatecnica.api.model.Usuario;
import cr.fi.proamerica.pruebatecnica.api.model.UsuarioDto;
import cr.fi.proamerica.pruebatecnica.api.repository.UsuarioRepositorio;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio userDao;	

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userDao.findByUsuario(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getContrasena(),
				new ArrayList<>());
	}
	
	public Usuario save(UsuarioDto user) {
		Usuario newUser = null;				
		newUser = userDao.findByUsuario(user.getUsuario());
		if(newUser!=null) {			
			newUser.setContrasena(bcryptEncoder.encode(user.getContrasena()));					
		}else {
			newUser = new Usuario();
			newUser.setUsuario(user.getUsuario());
			newUser.setContrasena(bcryptEncoder.encode(user.getContrasena()));
			newUser.setRol("admin");	
		}						
		return userDao.save(newUser);
		
	}
	

}
