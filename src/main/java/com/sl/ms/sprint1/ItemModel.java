package com.sl.ms.sprint1;

import java.time.LocalDate;

public class ItemModel {
	int id;
	String name;
	Double price;
	int total_amount;
	LocalDate dt;
	
	
	
	public LocalDate getDt() {
		return dt;
	}
	public void setDt(LocalDate dt) {
		this.dt = dt;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
@Override
public String toString() {
	return id+"--"+name+"--"+price+"--"+total_amount+"--"+dt;
	
}

}
