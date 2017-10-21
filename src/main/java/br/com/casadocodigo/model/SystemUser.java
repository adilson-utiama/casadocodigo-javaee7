package br.com.casadocodigo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;

@Entity
public class SystemUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Email
	private String email;

	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<SystemRole> roles = new ArrayList<>();

	public String getLogin() {
		return email;
	}

	public void setLogin(String login) {
		this.email = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<SystemRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SystemRole> roles) {
		this.roles = roles;
	}

}
