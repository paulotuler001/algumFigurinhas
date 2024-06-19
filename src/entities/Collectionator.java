package entities;

import java.time.LocalDate;

import enums.Role;

public class Collectionator extends User {

	public Collectionator(Integer id, String name, Boolean active, Role role, String email, String senha,
			LocalDate created_at, String deleteDescription, Integer idFavorites) {
		super(id, name, active, role, email, senha, created_at, deleteDescription, idFavorites);
		this.role = Role.COLLECTIONATOR;
	}

}
