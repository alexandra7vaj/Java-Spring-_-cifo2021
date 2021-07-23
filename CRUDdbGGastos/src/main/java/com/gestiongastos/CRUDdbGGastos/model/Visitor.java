package com.gestiongastos.CRUDdbGGastos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class Visitor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public String name;
	public String surname;
	public int age;
	public String email;
	public String password;

// --------------- RELATIONSHIP Table ----------------------------------------
	
	@OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
	private List<Expense> expenses = new ArrayList<>();
	
// ---------------- CONSTRUCTOR ------------------------------------------
	
	public Visitor() {
		super();
	}

	public Visitor(String name, String surname, int age, String email, String password) {
		super();

		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.password = password;
	}

// --------------- RELATIONSHIP visitor&expense --------------------------------	
	public List<Expense> getExpenses() {
		return expenses;
	}

	// изменила для последущего лучшего использования getter 
	public void addExpense(Expense expense) {
		this.expenses.add(expense);
		expense.setVisitor(this);
	}

// ---------------- GETTERS & SETTERS --------------------------------------------

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

// -------------- ToSTRING -------------------------------------------
	
	@Override
	public String toString() {
		return "Visitor [id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", email=" + email
				+  "]\n";
	}

}
