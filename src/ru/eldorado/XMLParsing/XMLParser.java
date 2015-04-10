package ru.eldorado.XMLParsing;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ru.edlorado.Model.Customer;
import ru.edlorado.Model.Order;

/**
 * 
 * @author Brega
 *
 */
public class XMLParser extends DefaultHandler {

	// объ€вл€ем коллекции Customer
	private List<Customer> customersList = null;
	private Customer customer = null;

	// объ€вл€ем коллекции Order
	private List<Order> orderList = null;
	private Order order = null;

	// метод возврата коллекции List<Customer>
	public List<Customer> getCustomers() {
		return customersList;
	}

	public List<Order> getOrders() {
		return orderList;
	}

	// переменна€ принимающ€€ значени€ данных xml-файла
	private String tmpValue;

	// начало тега xml-файла
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equals("customer")) {
			customer = new Customer();
			if (customersList == null) {
				customersList = new ArrayList<>();
			}

		}

		if (qName.equals("order")) {
			order = new Order();
			if (orderList == null) {
				orderList = new ArrayList<>();
			}

		}

	}

	// конец тега xml-файла
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equals("order")) {

			orderList.add(order);
		}

		if (qName.equals("customer")) {

			customersList.add(customer);
		}

		// цена по коллекции Orders
		if (qName.equalsIgnoreCase("price")) {
			order.getPrices().add(Double.parseDouble(tmpValue));
		}

		if (qName.equalsIgnoreCase("price")) {

			customer.getPrices().add(Double.parseDouble(tmpValue));
		}

		if (qName.equals("name")) {
			customer.setName(tmpValue);
		}

	}

	// парсинг значений
	@Override
	public void characters(char[] c, int start, int length) throws SAXException {

		tmpValue = new String(c, start, length);

	}
}
