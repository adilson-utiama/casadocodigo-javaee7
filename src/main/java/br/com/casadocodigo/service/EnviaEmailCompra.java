package br.com.casadocodigo.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.casadocodigo.infra.MailSender;
import br.com.casadocodigo.model.Compra;
import br.com.casadocodigo.repository.CompraDAO;

@MessageDriven(activationConfig = {
	    @ActivationConfigProperty(
	    		propertyName="destinationLookup", 
	    		propertyValue="java:/jms/topics/CarrinhoComprasTopico"),
	    @ActivationConfigProperty(
	    		propertyName="destinationType",
	            propertyValue="javax.jms.Topic")
	})
public class EnviaEmailCompra implements MessageListener{
		
	@Inject
	private MailSender mailSender;
	
	@Inject
	private CompraDAO compraDao;

	@Override
	public void onMessage(Message message) {
		
		try {
			TextMessage textMessage = (TextMessage) message;
			
			Compra compra = compraDao.buscaPorUuid(textMessage.getText());
			
			String messageBody = "Nova Compra na CDC, Sua compra foi realizada com sucesso, "
					+ "com o número de pedido " + compra.getUuid();
			
			mailSender.send("wilson.developer.tester@gmail.com", compra.getUsuario().getEmail(), "Nova compra no CDC", messageBody );
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
