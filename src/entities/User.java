package entities;

import enums.Role;

public class User {

	private Integer id;
	private Boolean active;
	private String name;
	private Role role;
	private String email;
	private String password;

	public User(Integer id, Boolean active, String name, Role role, String email, String password) {
		super();
		this.id = id;
		this.active = active;
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
