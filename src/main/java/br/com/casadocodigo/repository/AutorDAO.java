package br.com.casadocodigo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.model.Autor;

public class AutorDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Autor> listar() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}
}
