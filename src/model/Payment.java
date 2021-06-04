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
	
}
