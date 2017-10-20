package br.com.casadocodigo.service;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.casadocodigo.model.Compra;
import br.com.casadocodigo.repository.CompraDAO;


@Path("/pagamento")
public class PagamentoService {
	
	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	
	@Inject
	private CompraDAO compraDao;
	
	@Inject
	private PagamentoGateway pagamentoGateway;
	
	@Context
	private ServletContext context;

	@Inject
	private JMSContext jmsContext;
	
	@Resource(name="java:/jms/topics/CarrinhoComprasTopico")
	private Destination destination;
	
	
	@POST
    public void pagar(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		Compra compra = compraDao.buscaPorUuid(uuid);
		
		String contextPath = "http://localhost:8080" + context.getContextPath() + "/index.xhtml";
		System.out.println(contextPath);
		
		JMSProducer producer = jmsContext.createProducer();
		
		executor.submit(() -> {
			try {
				pagamentoGateway.pagar(compra.getTotal());
				
				URI responseUri= UriBuilder.fromPath(contextPath)
						.queryParam("msg", "Compra Realizada com Sucesso").build();
				Response response = Response.seeOther(responseUri)
						.build();
				
				producer.send(destination, compra.getUuid());
				
				ar.resume(response);
		        
		    } catch (Exception e) {
		        ar.resume(new WebApplicationException(e));
		    }
		});

    }
}
