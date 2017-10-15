package br.com.casadocodigo.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.model.CarrinhoCompras;
import br.com.casadocodigo.model.Usuario;
import br.com.casadocodigo.repository.UsuarioDAO;

@Model
public class CheckoutBean {

	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDAO dao;
	
	@Inject
	private CarrinhoCompras compras;
	
	@Transactional
	public void finalizar() {
		compras.finalizar(usuario);
		dao.salvar(usuario);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
