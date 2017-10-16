package br.com.casadocodigo.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.casadocodigo.model.Compra;

public class CompraDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void salvar(Compra compra) {
		manager.persist(compra);
		
	}

	public Compra buscaPorUuid(String uuid) {
		return manager.createQuery("select c from Compra c where c.uuid = :uuid", Compra.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
		
	}
}
