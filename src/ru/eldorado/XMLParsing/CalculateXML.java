package ru.eldorado.XMLParsing;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import ru.edlorado.Model.Customer;
import ru.edlorado.Model.Order;

/**
 * 
 * @author Brega
 *
 */

public class CalculateXML {

	// переменные на вывод в jsp
	private String total; // общая сумма
	private String maxValCustomer; // клиент с максимальной суммой заказов
	private String countOrder; // количество заказов

	private String maxValueOrder; // макс. сумма заказа
	private String minValueOrder; // мин. сумма заказа


	private double summO;
	private double middleOrder;
	private String client;

	private int m;
	private int y;

	private int c;
	
	private ArrayList<Double> ar;

	// округляем числа
	private String resultDouble;

	// public static void main (String args[]){
	public void pars(String f) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {

			
			
			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLParser handler = new XMLParser();
			// Находим файл
			saxParser.parse(new File(f), handler);
			// получаем коллекцию customer из парсера
			List<Customer> cList = handler.getCustomers();
			// получаем коллекцию order из парсера
			List<Order> cOrder = handler.getOrders();
			int countO = cOrder.size();
			// массив

			ar = new ArrayList<Double>();

			int x;

			double summ = 0;
			double summAll = 0;

			double max = cList.get(0).getPrices().get(0);

		

			// цикл по коллекции order

			for (Order o : cOrder) {
				// проходимся по массиву и складываем цены позиций в каждом из
				// заказов
				if (o.getPrices().size() != 0 && o.getPrices().size() > 0) {

					for (y = 0; y < o.getPrices().size(); y++) {
						summO += o.getPrices().get(y);
					}
				}
				// суммы заказов помещаем в отдельный массив
				ar.add(summO);
				summO = 0;
			}

			// вычисляем максимальную и минимальную сумму заказа
			double maxOrd = ar.get(0);
			double minOrd = ar.get(0);
			for (m = 0; m < ar.size(); m++) {
				maxOrd = Math.max(maxOrd, ar.get(m));
				minOrd = Math.min(minOrd, ar.get(m));
			}

			
			// цикл по коллекции customer
			for (Customer custom : cList) {
				// подсчет общей суммы заказов у клиентов
				for (x = 0; x < custom.getPrices().size(); x++) {
					summ += custom.getPrices().get(x);
				}
				// подсчет количества заказов по id-заказа

				// Добавляем нулевым индексом сумму заказов каждого клиента
				custom.getPrices().add(0, summ);
				// обнуляем сумму для корректности цикла
				summ = 0;
				// максимально значение заказа
				max = Math.max(max, custom.getPrices().get(0));

				// общая сумма всех заказов клиентов
				summAll += custom.getPrices().get(0);
				
				if(cList.get(c).getPrices().contains(max)) {
					client = cList.get(c).getName() + ":" +  cList.get(c).getPrices().get(c);
					
				}
				
				c++;
			}
			System.out.println(client);
			System.out.println(cList);
			
		
			// Средняя сумма заказов
			middleOrder = summAll / cOrder.size();
			setMiddleOrder(middleOrder);

			// максимальная сумма заказов
			maxValueOrder = "" + maxOrd;
			setMaxValueOrder(maxValueOrder);

			// минимальная сумма заказов
			minValueOrder = "" + minOrd;
			setMinValueOrder(minValueOrder);

			// общее количество заказов
			countOrder = "" + countO;

			// Общая сумма заказов
			total = "" + summAll;
			setS(total);

			// клиент с максимальной суммой заказов
			maxValCustomer = client;
			setMaxVal(maxValCustomer);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}


	}
	


	// Средняя сумма заказа

	public double getMiddleOrder() {
		getFormatNumber();
		return middleOrder;
	}

	public void setMiddleOrder(double middleOrder) {
		setFormatNumber(middleOrder);
		// this.middleOrder = middleOrder;
	}

	// сет и гет максимальной и минимальной суммы заказа
	public String getMaxValueOrder() {
		return maxValueOrder;
	}

	public void setMaxValueOrder(String maxValueOrder) {
		this.maxValueOrder = maxValueOrder;
	}

	public String getMinValueOrder() {
		return minValueOrder;
	}

	public void setMinValueOrder(String minValueOrder) {
		this.minValueOrder = minValueOrder;
	}

	// сет и гет количества заказов
	public String getCountOrder() {
		return countOrder;
	}

	public void setCountOrder(String countOrder) {
		this.countOrder = countOrder;
	}

	// сет и гет клиента с максимальной суммой заказа
	public String getMaxValCustomer() {
		return maxValCustomer;
	}

	public void setMaxVal(String maxVal) {
		this.maxValCustomer = maxVal;
	}

	// сет и гет общей суммы заказа
	public String getTotal() {
		return total;
	}

	public void setS(String total) {
		this.total = total;
	}

	//
	private void setFormatNumber(Double double_value) {
		DecimalFormatSymbols s = new DecimalFormatSymbols();
		s.setDecimalSeparator('.');
		DecimalFormat f = new DecimalFormat("#,###0000", s);
		resultDouble = f.format(double_value);

	}

	public String getFormatNumber() {
		return resultDouble;
	}
}
