package br.com.casadocodigo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.model.SystemUser;

public class SecurityDAO {

	@PersistenceContext
    private EntityManager manager;

    public SystemUser findByEmail(String email) {
        return manager.createQuery("select su from SystemUser su "
                + "where su.email = :email", SystemUser.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
