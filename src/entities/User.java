package entities;


import java.time.LocalDate;

import enums.Role;

public abstract class User {
	
	protected Integer id;
	protected String name;
	protected Boolean active;
	protected Role role;
	protected String email;
	protected String password;
	protected LocalDate created_at;
	protected String deleteDescription;
	protected Integer idFavorites;
	
	
	public User(Integer id, String name, Boolean active, Role role, String email, String password, LocalDate created_at,
			String deleteDescription, Integer idFavorites) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.role = Role.COLLECTIONATOR;
		this.email = email;
		this.password = password;
		this.created_at = created_at;
		this.deleteDescription = deleteDescription;
		this.idFavorites = idFavorites;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}
	public String getDeleteDescription() {
		return deleteDescription;
	}
	public void setDeleteDescription(String deleteDescription) {
		this.deleteDescription = deleteDescription;
	}
	public Integer getIdFavorites() {
		return idFavorites;
	}
	public void setIdFavorites(Integer idFavorites) {
		this.idFavorites = idFavorites;
	}
	
	
	
}
