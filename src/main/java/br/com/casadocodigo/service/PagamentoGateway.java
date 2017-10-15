package br.com.casadocodigo.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.casadocodigo.model.Pagamento;

public class PagamentoGateway implements Serializable{

	private static final long serialVersionUID = 1L;

	public String pagar(BigDecimal total) {
		
		Pagamento pagamento = new Pagamento(total);

	    //Utlizando JAX-RS para realizar o pagamento
	    Client client = ClientBuilder.newClient();
	    Entity<Pagamento> json = Entity.json(pagamento); //Transforma objeto em JSON
	    String target = "http://book-payment.herokuapp.com/payment";
	    return client.target(target)
	    		.request()
	    		.post(json, String.class);
	  
	}
}
