package model;

import java.util.Date;

public class Payment {
	
	private double amount;
	private PaymentMethod paymentMethod;
	private Date dateAndTime;
	private User creator;
	
	private Payment next;
	
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
	
}
