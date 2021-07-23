package com.gestiongastos.CRUDdbGGastos.model;

import java.lang.reflect.Member;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table
public class Expense {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private double value;
	
	
	
	//btw 1:n, member & expense
	@ManyToOne
	@JoinColumn(name = "VISITOR_FID")
	private Visitor visitor;
	
		
	public Expense() {
		super();
	}
	
	public Expense(String name, Date date, double price, double value) {
		super();
	
		this.name = name;
		this.date = date;
		this.price = price;
		this.value = value;
	}
	
	public Expense(String name, Date date, double price, double value, Visitor visitor) {
		super();
	
		this.name = name;
		this.date = date;
		this.value = value;
		this.visitor = visitor;
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
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", name=" + name + ", price=" + price + ", date=" + date + ", value=" + value
				+ ", visitor=" + visitor + "]";
	}

}
