package model;

import java.util.Date;

public class SeparateReceipt extends Receipt{

	private static final long serialVersionUID = 1;
	private Payment firstPayment;
	private State state;

	public SeparateReceipt(Client b, User c, Date d, String obs, String pm, double vp) {
		super(b, c, d, obs, pm); 
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
		firstPayment = new Payment(vp, pMT, d, c);
		state = State.NO_ENTREGADO;
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

}
