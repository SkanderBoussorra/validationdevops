package io.order.manager.food.order.manager.entities;

import javax.persistence.*;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Category(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}

	public Category() {
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", nom='" + nom + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
