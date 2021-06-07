package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment implements Serializable{
	
	
	private static final long serialVersionUID = 1;
	private double amount;
	private PaymentMethod paymentMethod;
	private Date dateAndTime;
	private User creator;
	
	private Payment next;
	private Payment prev;

	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> Payment. <br>
	*<b>pre</b>: the variables  c, d, pm, a, are already initialized. <br>
	*<b>post:</b> the attributes and relationships of the class have been initialized.<br>
	*@param c Is a User object that references the user creator of the payment. c!=null<br>
	* @param pm Is a PaymentMethod variable that contains the payment method of the payment.  pm== EFECTIVO, pm==TARJETA_DE_DEBITO,	pm==TARJETA_DE_CREDITO,	or pm==TRANSFERENCIA_BANCARIA<br>
	* @param d Is a Date variable that contains the date of the creation of the payment. d!=null<br>
	* @param a Is a double variable that contains the value of the payment.<br>
		*/
	public Payment(double a, PaymentMethod pm, Date d, User c) {
		amount = a;
		paymentMethod = pm;
		dateAndTime = d;
		creator = c;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Payment getNext() {
		return next;
	}

	public void setNext(Payment next) {
		this.next = next;
	}

	public Payment getPrev() {
		return prev;
	}

	public void setPrev(Payment prev) {
		this.prev = prev;
	}
	
	public String getDateAndHour() {
		String strDateFormat = "yyyy-MM-dd HH:mm"; 
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		String dateAndHour = objSDF.format(dateAndTime);
		return dateAndHour;
	}
	
	public String toString() {
		return getDateAndHour()+" $"+amount+" "+paymentMethod+" - "+creator.getUserName();
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
