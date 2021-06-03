package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SeparateReceipt extends Receipt implements PaymentTotal, UnpaidPrice{

	private static final long serialVersionUID = 1;
	private Payment firstPayment;
	private Payment lastPayment;
	private State state;

	public SeparateReceipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client b, User c,String pm, double vp) {
		super(listProd,listQ,b, c, "", pm); 
		
		firstPayment = new Payment(vp, stringToPaymentMethod(pm),getDateAndTime(), c);
		lastPayment=firstPayment;
		state = State.NO_ENTREGADO;
	}
	
	public void addPayment(double vp,String pm, User c ) {
		Date date=new Date();
		Payment payment=new Payment(vp, stringToPaymentMethod(pm), date, c);
		lastPayment.setNext(payment);
		payment.setPrev(lastPayment);
		lastPayment=payment;
		
		if(calculateUnpaidPrice()==0) {
			state = State.ENTREGADO;
			restUnitsToAddedProducts();
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
	@Override
	public double calculatePaymentTotal() {
		double paymentTotal=0;
		paymentTotal+=calculatePaymentTotal(firstPayment,paymentTotal);
		return paymentTotal;
	}
	
	private double calculatePaymentTotal(Payment current, double paymentTotal) {
		if(current==null) {
			return paymentTotal;
		}else {
			paymentTotal+=current.getAmount();
			current=current.getNext();
			return calculatePaymentTotal(current, paymentTotal);
		}
		
	}

	@Override
	public double calculateUnpaidPrice() {
		double unpaidPrice=calculateTotalPrice()-calculatePaymentTotal();
		return unpaidPrice;
	}

	

}
