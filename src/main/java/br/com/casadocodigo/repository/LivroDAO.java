package br.com.casadocodigo.repository;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.QueryHints;

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
	
	private void LimpaCacheJPA() {
		Cache cache = manager.getEntityManagerFactory().getCache();
		//cache.evict(Livro.class); Limpa uma determinada entidade do cache
		cache.evictAll(); //limpa todo o cache
	}
	
	private void limpaCAcheHibernate() {
		SessionFactory factory = manager.getEntityManagerFactory().unwrap(SessionFactory.class);
		factory.getCache().evictAllRegions(); //Limpa todo o cache
		//factory.getCache().evictQueryRegion("home"); //Limpa uma determinada regiao do cache, no caso todas associadas a home
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "select l from Livro l order by l.id desc";
	    return manager.createQuery(jpql, Livro.class)
	            .setMaxResults(5)
	            .setHint(QueryHints.HINT_CACHEABLE,	true)
	            //.setHint(QueryHints.HINT_CACHE_REGION, "home") Setando a Regiao a limpar cache
	            .getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "select l from Livro l order by l.id desc";
        return manager.createQuery(jpql, Livro.class)
        		.setFirstResult(5)
        		.setHint(QueryHints.HINT_CACHEABLE,	true)
        		//.setHint(QueryHints.HINT_CACHE_REGION, "home") Setando a Regiao a limpar cache
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
