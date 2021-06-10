package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SeparateReceipt extends Receipt implements PaymentTotal, UnpaidPrice{

	private static final long serialVersionUID = 1;
	private Payment firstPayment;
	private Payment lastPayment;
	private int numPayments;
	private State state;

	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> SeparateReceipt. <br>
	*<b>pre</b>: the variables listProd, listQ, b, c, pm, vp, are already initialized. <br>
	*<b>post:</b> the attributes and relationships of the class have been initialized.<br>
	*@param b Is a Client object that references the client buyer of the receipt. b!=null<br>
	*@param c Is a User object that references the user creator of the receipt. c!=null<br>
	* @param listProd Is an ArrayList of Product that contains the list of products for the receipt. listProd!=null.<br>
	* @param listQ Is an ArrayList of Integer that contains the list of quantities of products for the receipt. listQ!=null.<br>
	* @param pm Is a String variable that contains the payment method of the first payment.  pm equals "Efectivo", pm equals "Tarjeta de debito", pm equals "Tarjeta de credito", or pm equals "Transferencia bancaria"<br>
	* @param vp Is a double variable that contains the value of the first payment.<br>
		*/
	public SeparateReceipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client b, User c,String pm, double vp) {
		super(listProd,listQ,b, c, "", pm); 
		
		firstPayment = new Payment(vp, stringToPaymentMethod(pm),getDateAndTime(), c);
		lastPayment=firstPayment;
		state = State.NO_ENTREGADO;
		numPayments = 1;
	}
	

	/**
	* This method add a payment to the separate receipt.<br>
	* <b>name</b>: addPayment <br>
	* <b>pre</b>: The variable vp, pm, c, are already initialized. <br>
	*<b>post:</b> the payment has been added. <br>
	* @param pm Is a String variable that contains the payment method of the payment.  paymentMethod equals "Efectivo", paymentMethod equals "Tarjeta de debito", paymentMethod equals "Tarjeta de credito", or paymentMethod equals "Transferencia bancaria"<br>
	* @param vp Is a double variable that contains the value of the payment.<br>
	*@param c Is a User object that references the user creator of the payment. c!=null<br>
	*/
	public void addPayment(double vp,String pm, User c ) {
		Date date=new Date();
		Payment payment=new Payment(vp, stringToPaymentMethod(pm), date, c);
		numPayments +=1;
		lastPayment.setNext(payment);
		payment.setPrev(lastPayment);
		lastPayment=payment;
		
		if(calculateUnpaidPrice()==0) {
			state = State.ENTREGADO;
			restUnitsToAddedProducts();
		}
	}
	
	/**
	* This method returns a string with all payments of the receipt.<br>
	* <b>name</b>: getAllPayments <br>
	* <b>post</b>: all payments have been gotten. <br>
	* @return a <code> String </code>  that contains all payments of the receipt.
	*/
	public String getAllPayments() {
		String payments="";
		return getAllPayments(firstPayment, payments);
	}
	
	/**
	* This method returns a string with all payments of the receipt.<br>
	* <b>name</b>: getAllPayments <br>
	* <b>post</b>: all payments have been gotten. <br>
	* @param payments Is a String variable that contains the payments passed. payments!=null.<br>
	*@param current Is a Payment object that references a payment in the linked list of payments of the receipt.<br>
	* @return a <code> String </code>  that contains all payments of the receipt.
	*/
	private String getAllPayments(Payment current, String payments) {
		if(current==null) {
			return "";
		}else {
			payments+=current+"\n"+getAllPayments(current.getNext(),payments);
			
			return payments;
		}
		
	}
	
	public State getState() {
		return state;
	}

	public Payment getFirstPayment() {
		return firstPayment;
	}

	public void setFirstPayment(Payment firstPayment) {
		this.firstPayment = firstPayment;
	}
	
	public Payment getLastPayment() {
		return lastPayment;
	}

	public void setLastPayment(Payment lastPayment) {
		this.lastPayment = lastPayment;
	}
	
	/**
	* This method indicates if the receipt is in force.<br>
	* <b>name</b>: isInForce <br>
	* <b>post</b>: the receipt is in force or not. <br>
	* @return an <code> boolean </code> specifying inForce, a variable that indicates if the receipt is in force or not.<br>
	*/
	@Override
	public boolean isInForce() {
		boolean inForce=false;
		Date date= new Date();

		long diff = date.getTime() - lastPayment.getDateAndTime().getTime();

		TimeUnit time = TimeUnit.DAYS; 
		long difference = time.convert(diff, TimeUnit.MILLISECONDS);
		if(difference<366) {
			inForce=true;
		}
		return inForce;
	}

	/**
	* This method returns the payment total price of the receipt.<br>
	* <b>name</b>: calculatePaymentTotal <br>
	* <b>post</b>: the payment total price price has been gotten. <br>
	* @return an <code> double </code> specifying paymentTotal, the the payment total price of the receipt.
	*/
	@Override
	public double calculatePaymentTotal() {
		double paymentTotal=0;
		paymentTotal+=calculatePaymentTotal(firstPayment,paymentTotal);
		return paymentTotal;
	}
	
	/**
	* This method returns the payment total price of the receipt recursively.<br>
	* <b>name</b>: calculatePaymentTotal <br>
	* <b>pre</b>: The variables current, paymentTotal, are already initialized. <br>
	* <b>post</b>: the payment total price price has been gotten. <br>
	*@param current Is a Payment object that references the a payment in the linked list of payments<br>
	* @param paymentTotal Is a double variable that contains the sum of the value of the payments.<br>
	* @return an <code> double </code> the the payment total price of the receipt.
	*/
	private double calculatePaymentTotal(Payment current, double paymentTotal) {
		if(current==null) {
			return paymentTotal;
		}else {
			paymentTotal+=current.getAmount();
			current=current.getNext();
			return calculatePaymentTotal(current, paymentTotal);
		}
		
	}

	/**
	* This method returns the unpaid price of the receipt.<br>
	* <b>name</b>: calculateUnpaidPrice <br>
	* <b>post</b>: the unpaid price has been gotten. <br>
	* @return an <code> double </code> specifying unpaidPrice, the unpaid price of the receipt.
	*/
	@Override
	public double calculateUnpaidPrice() {
		double unpaidPrice=getTotal()-calculatePaymentTotal();
		return unpaidPrice;
	}
	
	public double getUnpaidPrice() {
		return calculateUnpaidPrice();
	}

	public int getNumPayments() {
		return numPayments;
	}

	public void setNumPayments(int numPayments) {
		this.numPayments = numPayments;
	}

	public String getStateString() {
		String s  = "";
		if(state==State.ENTREGADO) {
			s = state.name();
		}else {
			s = "NO ENTREGADO";
		}
		return s;
	}
}
