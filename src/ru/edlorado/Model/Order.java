package ru.edlorado.Model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Brega
 *
 */
public class Order {

	private List<Double> prices;
	private String position;

	public Order() {
		prices = new ArrayList<Double>();

	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<Double> getPrices() {
		return prices;
	}

	public void setPrices(List<Double> prices) {
		this.prices = prices;
	}

	public String toString() {

		return "Цена заказ: " + this.prices;

	}

}
