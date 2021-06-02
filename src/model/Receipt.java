package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Receipt implements TotalPrice,Serializable, Comparable<Receipt>{

	private static final long serialVersionUID = 1;
	private String code;
	private ArrayList<Product> listOfProducts;
	private ArrayList<Integer> listOfQuantity;
	private Date dateAndTime;
	private String observations;
	private User creator;
	private Client buyer;
	private PaymentMethod paymentMethod;

	public Receipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client b, User c, String obs, String pm) {
		dateAndTime=new Date();
		listOfProducts = listProd;
		listOfQuantity = listQ;
		creator = c;
		buyer = b;
		observations = obs;
		paymentMethod=stringToPaymentMethod(pm);
	}
	public PaymentMethod stringToPaymentMethod(String pm) {
		PaymentMethod pMT = null;
		switch(pm) {
		case "Efectivo": 
			pMT = PaymentMethod.EFECTIVO;
			break;
		case "Tarjeta de debito":
			pMT = PaymentMethod.TARJETA_DE_DEBITO;
			break;
		case "Tarjeta de credito":
			pMT = PaymentMethod.TARJETA_DE_CREDITO;
			break;
		case "Transferencia bancaria":
			pMT = PaymentMethod.TRANSFERENCIA_BANCARIA;
			break;
		}
		return pMT;
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

	public boolean findProduct(String code) {
		boolean found=false;
		for(int i=0; i<listOfProducts.size() && !found;i++ ) {
			if(listOfProducts.get(i).getCode().equals(code)) {
				found=true;						
			}
		}		
		return found;
	}

	public boolean findElectronicEquipmentProduct() {
		boolean found=false;
		for(int i=0; i<listOfProducts.size() && !found;i++) {
			if(listOfProducts.get(i).getType() instanceof ElectronicEquipment) {
				found=true;
			}
		}
		return found;
	}

	public boolean isInForce() {
		boolean inForce=false;
		Date date= new Date();

		long diff = date.getTime() - dateAndTime.getTime();

		TimeUnit time = TimeUnit.DAYS; 
		long difference = time.convert(diff, TimeUnit.MILLISECONDS);
		if(difference<366) {
			inForce=true;
		}
		return inForce;
	}


	@Override
	public int compareTo(Receipt r) {
		return dateAndTime.compareTo(r.getDateAndTime());
	}

	@Override
	public double calculateTotalPrice() {
		double totalPrice=0;
		for(int i=0; i<listOfProducts.size();i++) {
			totalPrice+=listOfProducts.get(i).getPrice() *listOfQuantity.get(i);
		}
		return totalPrice;
	}

}
