package ru.edlorado.Model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Brega
 *
 */
public class Customer {

	private String name;
	private List<Double> prices;

	private List<Double> oSumm;

	public Customer() {
		prices = new ArrayList<Double>();
		oSumm = new ArrayList<Double>();
	}

	public List<Double> getoSumm() {
		return oSumm;
	}

	public void setoSumm(List<Double> oSumm) {
		this.oSumm = oSumm;
	}

	// вывод информации
	public String toString() {

		return this.name + this.prices;

	}

	// getters and setters

	public List<Double> getPrices() {
		return prices;
	}

	public void setPrices(List<Double> prices) {
		this.prices = prices;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
