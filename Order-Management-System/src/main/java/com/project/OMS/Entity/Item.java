package com.project.OMS.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/* 
@Entity - Used to map Item class to table.
@ID - Assigned annotation @Id to itemId to set it as Primary Key.
@GeneratedValue - Auto generates the order Id for each order added
@Column - Used to assign specific column name to created column.
@ManyToMany - Since each order can have many items & Each Item can have many orders we have used
 			   @ManyToMany to map it in the database.
*/


@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@Column(name = "Item_Name")
	private String name;

	@Column(name = "Procuct_cost")
	private Double cost;
	
	@ManyToMany(mappedBy = "items",fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();
	
	public Item() {

	}

	public Item(String name, Double cost) {
		super();
		this.name = name;
		this.cost = cost;
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

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void addOrders(Order orders) {
		this.orders.add(orders);
	}
	
	public void removeOrders(Order orders) {
		this.orders.remove(orders);
	}
	

}
