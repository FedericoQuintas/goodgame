package goodgames.domain.builder;

import goodgames.domain.Order;
import goodgames.domain.PaymentType;
import goodgames.domain.Programmer;

public class OrderBuilder {

	private Programmer programmer;
	private PaymentType paymentType;

	public Order build() {
		return new Order(programmer, paymentType);
	}

	public OrderBuilder withProgrammer(Programmer programmer) {
		this.programmer = programmer;
		return this;
	}

	public OrderBuilder withPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
		return this;
	}

}
