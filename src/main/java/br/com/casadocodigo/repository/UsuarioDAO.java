package br.com.casadocodigo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.model.Usuario;

public class UsuarioDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}
}
