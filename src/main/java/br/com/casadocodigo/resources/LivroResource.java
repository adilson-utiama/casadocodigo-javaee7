package br.com.casadocodigo.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.casadocodigo.model.Livro;
import br.com.casadocodigo.repository.LivroDAO;

@Path("livros")
public class LivroResource {

	@Inject
	private LivroDAO dao;
	
	@GET
	@Path("lancamentos")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Livro> ultimosLancamentosJson() {
		return dao.ultimosLancamentos();
	}
}
