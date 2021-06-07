package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Receipt implements SubtotalPrice,Serializable,taxIVA, Comparable<Receipt>{

	private static final long serialVersionUID = 1;
	private String code;
	private ArrayList<Product> listOfProducts;
	private ArrayList<Integer> listOfQuantity;
	private Date dateAndTime;
	private String observations;
	private User creator;
	private Client buyer;
	private PaymentMethod paymentMethod;

	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> Receipt. <br>
	*<b>pre</b>: the variables listProd, listQ, b, c, obs, pm, are already initialized. <br>
	*<b>post:</b> the attributes and relationships of the class have been initialized.<br>
	*@param b Is a Client object that references the client buyer of the receipt. b!=null<br>
	*@param c Is a User object that references the user creator of the receipt. c!=null<br>
	* @param listProd Is an ArrayList of Product that contains the list of products for the receipt. listProd!=null.<br>
	* @param listQ Is an ArrayList of Integer that contains the list of quantities of products for the receipt. listQ!=null.<br>
	* @param pm Is a String variable that contains the payment method of the receipt.  pm equals "Efectivo", pm equals "Tarjeta de debito", pm equals "Tarjeta de credito", or pm equals "Transferencia bancaria"<br>
	* @param obs Is a String variable that contains the observations of the receipt. obs!=null<br>
		*/
	public Receipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client b, User c, String obs, String pm) {
		dateAndTime=new Date();
		listOfProducts = listProd;
		listOfQuantity = listQ;
		creator = c;
		buyer = b;
		observations = obs;
		paymentMethod=stringToPaymentMethod(pm);
	}
	

	/**
	* This method rests units available to the added products.<br>
	* <b>name</b>: restUnitsToAddedProducts <br>
	* <b>post</b>: the units have been rested. <br>
	*/
	public void restUnitsToAddedProducts() {
		for(int i=0;i<listOfProducts.size();i++) {
			listOfProducts.get(i).setUnits(listOfProducts.get(i).getUnits()-listOfQuantity.get(i));
			if(listOfProducts.get(i).getUnits()==0) {
				listOfProducts.get(i).setEnabled(false);
			}
		}
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
	
	/**
	* This method returns a string with all products of the receipt.<br>
	* <b>name</b>: getAllProducts <br>
	* <b>post</b>: all products have been gotten. <br>
	* @return a <code> String </code> specifying allProducts, that contains all products of the receipt.
	*/
	public String getAllProducts() {
		String allProducts = "";
		for(int k=0; k<listOfProducts.size();k++) {
			allProducts += listOfQuantity.get(k)+" "+listOfProducts.get(k)+"\n";
		}
		return allProducts;
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
		String name = creator.getUserName();
		return name;
	}

	public Client getBuyer() {
		return buyer;
	}

	public void setBuyer(Client buyer) {
		this.buyer = buyer;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	* This method indicates if the receipt contains a product with the given code.<br>
	* <b>name</b>: findProduct <br>
	* <b>post</b>: the receipt has a product with the given code or not. <br>
	*@param code Is a String variable that contains the code of the product. code!=null and code!="".<br>
	* @return an <code> boolean </code> specifying found, a variable that indicates if the receipt contains the product or not.<br>
	*/
	public boolean findProduct(String code) {
		boolean found=false;
		for(int i=0; i<listOfProducts.size() && !found;i++ ) {
			if(listOfProducts.get(i).getCode().equals(code)) {
				found=true;						
			}
		}		
		return found;
	}

	/**
	* This method indicates if the receipt contains an electronic equipment.<br>
	* <b>name</b>: findElectronicEquipmentProduct <br>
	* <b>post</b>: the receipt has an electronic equipment or not. <br>
	* @return an <code> boolean </code> specifying found, a variable that indicates if the receipt contains an electronic equipment or not.<br>
	*/
	public boolean findElectronicEquipmentProduct() {
		boolean found=false;
		for(int i=0; i<listOfProducts.size() && !found;i++) {
			if(listOfProducts.get(i).getType() instanceof ElectronicEquipment) {
				found=true;
			}
		}
		return found;
	}
	
	/**
	* This method indicates if the receipt is in force.<br>
	* <b>name</b>: isInForce <br>
	* <b>post</b>: the receipt is in force or not. <br>
	* @return an <code> boolean </code> specifying inForce, a variable that indicates if the receipt is in force or not.<br>
	*/
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

	/**
	* This method compares the dates of two receipts.<br>
	* <b>name</b>: compareTo <br>
	* <b>post</b>: the receipts have been compared. <br>
	*@param r Is a Receipt object that references the receipt that wants to be compared. r!=null<br>
	* @return an <code> integer </code> a variable that indicates if the date of the receipt is greater than the other, equal, or less.
	*/
	@Override
	public int compareTo(Receipt r) {
		return dateAndTime.compareTo(r.getDateAndTime());
	}
	
	/**
	* This method returns the subtotal price of the receipt.<br>
	* <b>name</b>: calculateSubtotalPrice <br>
	* <b>post</b>: the subtotal price has been gotten. <br>
	* @return an <code> double </code> specifying subtotalPrice, the subtotal price of the receipt.
	*/
	@Override
	public double calculateSubtotalPrice() {
		double subtotalPrice=0;
		for(int i=0; i<listOfProducts.size();i++) {
			subtotalPrice+=listOfProducts.get(i).getPrice() *listOfQuantity.get(i);
		}
		return subtotalPrice;
	}
	
	/**
	* This method returns the IVA tax sum value of all products in the receipt.<br>
	* <b>name</b>: calculateIVA <br>
	* <b>post</b>: the IVA tax sum value has been gotten. <br>
	* @return an <code> double </code> specifying iva, the IVA tax sum value of all products.
	*/
	@Override
	public double calculateIVA() {
		double iva=0;
		for(int i=0; i<listOfProducts.size();i++) {
			iva+=listOfProducts.get(i).calculateIVA() *listOfQuantity.get(i);
		}
		return iva;
	}
	
	public double getSubtotal() {
		return calculateSubtotalPrice();
	}
	
	public double getIVA() {
		return calculateIVA();
	}
	
	/**
	* This method returns the total price of the receipt.<br>
	* <b>name</b>: getTotal <br>
	* <b>post</b>: the total price has been gotten. <br>
	* @return an <code> double </code> the total price of the receipt.
	*/
	public double getTotal() {
		return calculateSubtotalPrice()+calculateIVA();
	}

	public String getPaymentMString() {
		String pMT = "";
		switch(paymentMethod) {
		case EFECTIVO: 
			pMT = "Efectivo";
			break;
		case TARJETA_DE_DEBITO:
			pMT = "Tarjeta de debito";
			break;
		case TARJETA_DE_CREDITO:
			pMT = "Tarjeta de credito";
			break;
		case TRANSFERENCIA_BANCARIA:
			pMT = "Transferencia bancaria";
			break;
		}
		return pMT;
	}

}
