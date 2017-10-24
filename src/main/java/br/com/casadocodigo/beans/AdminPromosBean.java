package br.com.casadocodigo.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.model.Promo;
import br.com.casadocodigo.websockets.PromosEndpoint;

@Model
public class AdminPromosBean {

	private Promo promo = new Promo();
	
	@Inject
	private PromosEndpoint promosEndpoint;

    public void enviar() {
        promosEndpoint.send(promo);       
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }
}
