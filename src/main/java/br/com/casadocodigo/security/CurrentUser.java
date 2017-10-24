package br.com.casadocodigo.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.casadocodigo.model.SystemUser;
import br.com.casadocodigo.repository.SecurityDAO;

@Model
public class CurrentUser {

	@Inject
    private HttpServletRequest request;

    @Inject
    private SecurityDAO securityDao;

	private SystemUser systemUser;
    
    
    @PostConstruct
    private void loadSystemUser() {

        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            String email = request.getUserPrincipal().getName();
            systemUser = securityDao.findByEmail(email);
        }
    }
    
    public boolean hasRole(String name) {
        return request.isUserInRole(name);
    }
    
    public String logout() {
    	request.getSession().invalidate();
    	return "/admin/livros/lista.xhtml?faces-redirect=true";
    }
    
    public SystemUser get() {
        return systemUser;
    }
    
    
}
