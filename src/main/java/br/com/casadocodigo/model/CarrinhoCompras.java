package br.com.casadocodigo.model;

import java.util.HashSet;
import java.util.Set;

public class CarrinhoCompras {

	private Set<CarrinhoItem> itens = new HashSet<>();

    public void add(CarrinhoItem item) {
        itens.add(item);
    }
	
}
