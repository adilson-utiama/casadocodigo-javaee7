package br.com.casadocodigo.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.model.Livro;
import br.com.casadocodigo.repository.LivroDAO;

@Model
public class HomeBean {

	@Inject
	private LivroDAO dao;
	
	public List<Livro> ultimosLancamentos() {
		System.out.println("Entrando nos ultimos lancamentos");
		return dao.ultimosLancamentos();
	}
	
	public List<Livro> demaisLivros() {
		System.out.println("Entrando nos demais livros");
		return dao.demaisLivros();
	}
}
