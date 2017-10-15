package br.com.casadocodigo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.com.casadocodigo.repository.CompraDAO;
import br.com.casadocodigo.service.PagamentoGateway;

@Named
@SessionScoped
public class CarrinhoCompras implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CompraDAO compraDao;
	
	@Inject
	private PagamentoGateway pagamentoGateway;
	
	private Set<CarrinhoItem> itens = new HashSet<>();

    public void add(CarrinhoItem item) {
        itens.add(item);
    }
    
    public List<CarrinhoItem> getItens() {
        return new ArrayList<CarrinhoItem>(itens);
    }
    
    public BigDecimal getTotal(CarrinhoItem item) {
    	return item.getLivro().getPreco().multiply(new BigDecimal(item.getQuantidade()));
    }
    
    public BigDecimal getTotal() {
    	BigDecimal total = BigDecimal.ZERO;
    	for (CarrinhoItem carrinhoItem : itens) {
			total = total.add(carrinhoItem.getLivro().getPreco().multiply(new BigDecimal(carrinhoItem.getQuantidade())));
		}
    	return total;
    }
    
    public void remover(CarrinhoItem item) {
        this.itens.remove(item);
    }
    
    public Integer getQuantidadeTotal() {
        return itens.stream().mapToInt(item -> item.getQuantidade()).sum();
    }

	public void finalizar(Usuario usuario) {
		Compra compra = new Compra();
		compra.setUsuario(usuario);
	    compra.setItens(this.toJson(itens));
	    compraDao.salvar(compra);
	    
	    String response = pagamentoGateway.pagar(getTotal());
	    System.out.println(response);
		
	}

	private String toJson(Set<CarrinhoItem> itens) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
	    for (CarrinhoItem item : itens) {
	        builder.add(Json.createObjectBuilder()
	                .add("titulo", item.getLivro().getTitulo())
	                .add("preco", item.getLivro().getPreco())
	                .add("quantidade", item.getQuantidade())
	                .add("total", getTotal(item)));
	    }
	    String json = builder.build().toString();
	    System.out.println(json);
		return json;
	}
	
}
