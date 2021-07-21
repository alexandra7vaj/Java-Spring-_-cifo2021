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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private double value;
	
	
	
	//btw 1:n, member & expense
	@ManyToOne
	@JoinColumn(name = "MEMBER_FID")
	private Member member;
		
	public Expense() {
		super();
	}
	
	public Expense(String name, Date date, double value) {
		super();
	
		this.name = name;
		this.date = date;
		this.value = value;
	}
	
	public Expense(String name, Date date, double value, Member member) {
		super();
	
		this.name = name;
		this.date = date;
		this.value = value;
		this.member = member;
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
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", name=" + name + ", date=" + date + ", value=" + value + "]";
	}

	public void setMember(com.gestiongastos.CRUDdbGGastos.model.Member member2) {
		// TODO Auto-generated method stub
		
	}
	


}
