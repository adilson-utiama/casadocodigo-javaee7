package br.com.casadocodigo.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.infra.FileSaver;
import br.com.casadocodigo.model.Autor;
import br.com.casadocodigo.model.Livro;
import br.com.casadocodigo.repository.AutorDAO;
import br.com.casadocodigo.repository.LivroDAO;

//CDI
@Named
@RequestScoped
public class AdminLivroBean {

	private Livro livro = new Livro();
		
	@Inject
	private LivroDAO livroDAO;
	@Inject
	private AutorDAO autorDAO;
	@Inject
	private FacesContext context;
	
	private Part capaLivro;
	
	@Transactional
	public String salvar() {
		//Salvando arquivo recebido
		FileSaver fileSaver = new FileSaver();
		livro.setCapaPath(fileSaver.write(capaLivro, "livros"));
		
		System.out.println("Livro salvo: " + livro);
		
		livroDAO.salvar(livro);
		
		//Ativa o escopo de flash
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro " + livro.getTitulo() + " cadastrado com Sucesso!"));

		return "/livros/lista?faces-redirect=true";
	}
	
	public List<Autor> getAutores() {
		return autorDAO.listar();
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}
	
	
}
