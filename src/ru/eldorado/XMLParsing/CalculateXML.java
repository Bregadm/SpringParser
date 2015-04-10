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

	// ���������� �� ����� � jsp
	private String total; // ����� �����
	private String maxValCustomer; // ������ � ������������ ������ �������
	private String countOrder; // ���������� �������

	private String maxValueOrder; // ����. ����� ������
	private String minValueOrder; // ���. ����� ������


	private double summO;
	private double middleOrder;
	private String client;

	private int m;
	private int y;

	private int c;
	
	private ArrayList<Double> ar;

	// ��������� �����
	private String resultDouble;

	// public static void main (String args[]){
	public void pars(String f) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {

			
			
			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLParser handler = new XMLParser();
			// ������� ����
			saxParser.parse(new File(f), handler);
			// �������� ��������� customer �� �������
			List<Customer> cList = handler.getCustomers();
			// �������� ��������� order �� �������
			List<Order> cOrder = handler.getOrders();
			int countO = cOrder.size();
			// ������

			ar = new ArrayList<Double>();

			int x;

			double summ = 0;
			double summAll = 0;

			double max = cList.get(0).getPrices().get(0);

		

			// ���� �� ��������� order

			for (Order o : cOrder) {
				// ���������� �� ������� � ���������� ���� ������� � ������ ��
				// �������
				if (o.getPrices().size() != 0 && o.getPrices().size() > 0) {

					for (y = 0; y < o.getPrices().size(); y++) {
						summO += o.getPrices().get(y);
					}
				}
				// ����� ������� �������� � ��������� ������
				ar.add(summO);
				summO = 0;
			}

			// ��������� ������������ � ����������� ����� ������
			double maxOrd = ar.get(0);
			double minOrd = ar.get(0);
			for (m = 0; m < ar.size(); m++) {
				maxOrd = Math.max(maxOrd, ar.get(m));
				minOrd = Math.min(minOrd, ar.get(m));
			}

			
			// ���� �� ��������� customer
			for (Customer custom : cList) {
				// ������� ����� ����� ������� � ��������
				for (x = 0; x < custom.getPrices().size(); x++) {
					summ += custom.getPrices().get(x);
				}
				// ������� ���������� ������� �� id-������

				// ��������� ������� �������� ����� ������� ������� �������
				custom.getPrices().add(0, summ);
				// �������� ����� ��� ������������ �����
				summ = 0;
				// ����������� �������� ������
				max = Math.max(max, custom.getPrices().get(0));

				// ����� ����� ���� ������� ��������
				summAll += custom.getPrices().get(0);
				
				if(cList.get(c).getPrices().contains(max)) {
					client = cList.get(c).getName() + ":" +  cList.get(c).getPrices().get(c);
					
				}
				
				c++;
			}
			System.out.println(client);
			System.out.println(cList);
			
		
			// ������� ����� �������
			middleOrder = summAll / cOrder.size();
			setMiddleOrder(middleOrder);

			// ������������ ����� �������
			maxValueOrder = "" + maxOrd;
			setMaxValueOrder(maxValueOrder);

			// ����������� ����� �������
			minValueOrder = "" + minOrd;
			setMinValueOrder(minValueOrder);

			// ����� ���������� �������
			countOrder = "" + countO;

			// ����� ����� �������
			total = "" + summAll;
			setS(total);

			// ������ � ������������ ������ �������
			maxValCustomer = client;
			setMaxVal(maxValCustomer);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}


	}
	


	// ������� ����� ������

	public double getMiddleOrder() {
		getFormatNumber();
		return middleOrder;
	}

	public void setMiddleOrder(double middleOrder) {
		setFormatNumber(middleOrder);
		// this.middleOrder = middleOrder;
	}

	// ��� � ��� ������������ � ����������� ����� ������
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

	// ��� � ��� ���������� �������
	public String getCountOrder() {
		return countOrder;
	}

	public void setCountOrder(String countOrder) {
		this.countOrder = countOrder;
	}

	// ��� � ��� ������� � ������������ ������ ������
	public String getMaxValCustomer() {
		return maxValCustomer;
	}

	public void setMaxVal(String maxVal) {
		this.maxValCustomer = maxVal;
	}

	// ��� � ��� ����� ����� ������
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
