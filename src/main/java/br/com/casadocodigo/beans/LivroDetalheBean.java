package br.com.casadocodigo.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.model.Livro;
import br.com.casadocodigo.repository.LivroDAO;

@Model
public class LivroDetalheBean {

	@Inject
	private LivroDAO dao;

	private Livro livro;
	private Integer id;

	public void carregarDetalhe() {
		this.livro = dao.buscarPorId(id);
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
