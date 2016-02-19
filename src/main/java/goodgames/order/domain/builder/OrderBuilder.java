package goodgames.order.domain.builder;

import goodgames.order.domain.Order;
import goodgames.order.domain.Programmer;
import goodgames.store.domain.PaymentType;

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
