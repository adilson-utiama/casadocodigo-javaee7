package br.com.casadocodigo.repository;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import br.com.casadocodigo.model.Livro;

@Stateful  //Essa annotation @Stateful torna nossa classe um Enterprise Java Bean
public class LivroDAO {

	//ativa o contexto estendido do Entity Manager, mantendo a sessao por mais tempo
	//resolvend o problema da LazyInitializationException
	@PersistenceContext(type=PersistenceContextType.EXTENDED) 
	private EntityManager manager;
	
	@Transactional
	public void salvar(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "select distinct(l) from Livro l"
	            + " join fetch l.autores";        

        return manager.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "select l from Livro l order by l.id desc";
	    return manager.createQuery(jpql, Livro.class)
	            .setMaxResults(5)
	            .getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "select l from Livro l order by l.id desc";
        return manager.createQuery(jpql, Livro.class)
        		.setFirstResult(5)
                .getResultList();
	}

	public Livro buscarPorId(Integer id) {
		//Planned Queries
		String jpql = "select l from Livro l join fetch l.autores "
	            + "where l.id = :id";
	    return manager.createQuery(jpql, Livro.class)
	            .setParameter("id", id)
	            .getSingleResult();
	}
}
