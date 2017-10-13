package br.com.casadocodigo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.model.Livro;
import br.com.casadocodigo.repository.LivroDAO;

@Model
public class AdminListaLivrosBean {
	
	@Inject
	private LivroDAO dao;
	
	private List<Livro> livros = new ArrayList<>();
	
	public List<Livro> getLivros() {
		this.livros = dao.listar();
		
		return livros;
	}

}
