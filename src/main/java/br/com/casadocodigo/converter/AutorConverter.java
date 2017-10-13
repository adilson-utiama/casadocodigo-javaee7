package br.com.casadocodigo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.casadocodigo.model.Autor;

@FacesConverter("autorConverter")
public class AutorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		if(id == null || id.trim().isEmpty()) return null;
		System.out.println("Convertendo String para Autor: " + id);
		
		Autor autor = new Autor(Integer.valueOf(id));
		return autor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object autorObject) {
		if(autorObject == null) return null;
		System.out.println("Convertendo Autor para String: " + autorObject);
		
		Autor autor = (Autor) autorObject;
		return autor.getId().toString();
	}

}
