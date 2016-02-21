package goodgames.order.domain.builder;

import goodgames.common.validator.FieldValidator;
import goodgames.order.domain.Order;
import goodgames.order.exception.OrderCreationException;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.PaymentType;

public class OrderBuilder {

	private static final String PAYMENT_TYPE_FIELD_CANNOT_BE_NULL = "Payment type field cannot be null";
	private static final String COFFEE_TYPE_FIELD_CANNOT_BE_NULL = "Coffee type field cannot be null";

	private PaymentType paymentType;
	private CoffeeType coffeeType;

	public Order build() {
		validateFields();
		return new Order(coffeeType, paymentType);
	}

	public OrderBuilder withCoffeeType(CoffeeType coffeeType) {
		this.coffeeType = coffeeType;
		return this;
	}

	public OrderBuilder withPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
		return this;
	}

	private void validateFields() {
		FieldValidator.validateNotNull(coffeeType, new OrderCreationException(
				COFFEE_TYPE_FIELD_CANNOT_BE_NULL));
		FieldValidator.validateNotNull(paymentType, new OrderCreationException(
				PAYMENT_TYPE_FIELD_CANNOT_BE_NULL));
	}

}
