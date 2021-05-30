package model;

import java.util.ArrayList;

public class SeparateReceipt extends Receipt implements PaymentTotal, UnpaidPrice{

	private static final long serialVersionUID = 1;
	private Payment firstPayment;
	private State state;

	public SeparateReceipt(ArrayList<Product> listProd,ArrayList<Integer> listQ,Client b, User c,String pm, double vp) {
		super(listProd,listQ,b, c, "", pm); 
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
		firstPayment = new Payment(vp, pMT,getDateAndTime(), c);
		state = State.NO_ENTREGADO;
	}
	
	public void addPayment(double vp,String pm, User c ) {
		
	}
	
	public State getState() {
		return state;
	}

	public void updateState(String st) {
		state = State.valueOf(st);
	}

	public Payment getFirstPayment() {
		return firstPayment;
	}

	public void setFirstPayment(Payment firstPayment) {
		this.firstPayment = firstPayment;
	}

	@Override
	public double calculatePaymentTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateUnpaidPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}
