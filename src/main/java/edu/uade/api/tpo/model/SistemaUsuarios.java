package edu.uade.api.tpo.model;

import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.dao.impl.UsuarioDaoImpl;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class SistemaUsuarios {
	private static final Logger logger = LoggerFactory.getLogger(SistemaUsuarios.class);
	private static SistemaUsuarios instance = null;
	private GenericDao<Usuario> usuarioDao;

	private SistemaUsuarios() {
		this.usuarioDao = UsuarioDaoImpl.getInstance();
	}

	public static SistemaUsuarios getInstance() {
		if (instance == null) {
			instance = new SistemaUsuarios();
		}
		return instance;
	}

	public Usuario altaUsuario(Usuario usuario) throws BusinessException {
		if (this.buscarUsuario(usuario.getNombreUsuario()) == null) {
			try {
				usuarioDao.create(usuario);
			} catch (SQLException e) {
				logger.error("Error creando usuario: " + usuario.getNombreUsuario(), e);
			}
			return usuario;
		} else {
			throw new BusinessException("El ususario ya existe");
		}
	}

	public void eliminarUsuario(String nombreUsuario) throws BusinessException {
		Usuario usuario = this.buscarUsuario(nombreUsuario);
		if (usuario != null) {
			usuario.setEstado(Estado.INACTIVO);
			try {
				usuarioDao.update(usuario);
			} catch (SQLException e) {
				logger.error("Error eliminando usuario: " + nombreUsuario, e);
			}
		} else {
			throw new BusinessException("El usuario no existe");
		}
	}

	public void modificarUsuario(Usuario usuario) throws BusinessException {
		if (this.buscarUsuario(usuario.getNombreUsuario()) != null) {
			try {
				usuarioDao.update(usuario);
			} catch (SQLException e) {
				logger.error("Error modificando usuario :" + usuario.getNombreUsuario(), e);
			}
		} else {
			throw new BusinessException("El usuario no existe");
		}
	}

	public Usuario buscarUsuario(String nombreUsuario) {
		Usuario u = null;
		try {
			u = usuarioDao.findBy("nombre_usuario", nombreUsuario);
		} catch (SQLException e) {
			logger.error("Error buscando usuario :" + nombreUsuario, e);
		}
		return u;
	}

	public boolean validarPassword(String password) throws InvalidPasswordException {
		
		String regex = "(?=.*[A-Z])(?=.*\\d)[\\w\\d]{8,20}";
		
		if(Pattern.matches(regex, password)){
			return true;
		}else {
			throw new InvalidPasswordException("La contraseña ingresada es incorrecta.\nLa misma debe tener entre 8 y 20 caracteres, al menos un número y al menos una letra mayúscula.");
		}
	}
}
