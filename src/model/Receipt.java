package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Receipt implements Serializable, Comparable<Receipt>{

	private static final long serialVersionUID = 1;
	private String code;
	private ArrayList<Product> listOfProducts;
	private ArrayList<Integer> listOfQuantity;
	private Date dateAndTime;
	private String observations;
	private double totalValue;
	private User creator;
	private Client buyer;
	private PaymentMethod paymentMethod;
	
	public Receipt(Client b, User c, Date d, String obs, String pm) {
		listOfProducts = new ArrayList<Product>();
		listOfQuantity = new ArrayList<Integer>();
		creator = c;
		buyer = b;
		observations = obs;
		switch(pm) {
		case "Efectivo": 
			paymentMethod = PaymentMethod.EFECTIVO;
			break;
		case "Tarjeta de debito":
			paymentMethod = PaymentMethod.TARJETA_DE_DEBITO;
			break;
		case "Tarjeta de credito":
			paymentMethod = PaymentMethod.TARJETA_DE_CREDITO;
			break;
		case "Transferencia bancaria":
			paymentMethod = PaymentMethod.TRANSFERENCIA_BANCARIA;
			break;
		}
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<Product> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(ArrayList<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	public ArrayList<Integer> getListOfQuantity() {
		return listOfQuantity;
	}

	public void setListOfQuantity(ArrayList<Integer> listOfQuantity) {
		this.listOfQuantity = listOfQuantity;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	public String getDateAndHour() {
		String strDateFormat = "yyyy-MM-dd HH:mm"; 
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String dateAndHour = objSDF.format(dateAndTime);
        return dateAndHour;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public String getUserName() {
		String name = creator.toString();
		return name;
	}

	public Client getBuyer() {
		return buyer;
	}

	public void setBuyer(Client buyer) {
		this.buyer = buyer;
	}
	
	public String getClientName() {
		String name = buyer.toString();
		return name;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public int compareTo(Receipt r) {
		return dateAndTime.compareTo(r.getDateAndTime());
	}

}
