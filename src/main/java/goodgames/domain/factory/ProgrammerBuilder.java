package goodgames.domain.factory;

import goodgames.domain.CoffeType;
import goodgames.domain.PaymentType;
import goodgames.domain.Programmer;

public class ProgrammerBuilder {

	private CoffeType coffeeType;
	private PaymentType paymentType;

	public Programmer build() {
		return new Programmer(coffeeType, paymentType);
	}

	public ProgrammerBuilder withFavouriteCoffee(CoffeType coffeeType) {
		this.coffeeType = coffeeType;
		return this;
	}

	public ProgrammerBuilder withPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
		return this;
	}

}
