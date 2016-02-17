package goodgames.domain;

public class Programmer {

	private PaymentType paymentType;
	private CoffeType coffeeType;

	public Programmer(CoffeType coffeeType, PaymentType paymentType) {
		this.coffeeType = coffeeType;
		this.paymentType = paymentType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public CoffeType getCoffeeType() {
		return coffeeType;
	}

}
