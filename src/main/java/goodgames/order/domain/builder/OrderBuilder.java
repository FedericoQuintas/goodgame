package goodgames.order.domain.builder;

import goodgames.common.validator.FieldValidator;
import goodgames.order.domain.Order;
import goodgames.order.domain.Programmer;
import goodgames.order.exception.OrderBuilderException;
import goodgames.store.domain.PaymentType;

public class OrderBuilder {

	private static final String PAYMENT_TYPE_FIELD_CANNOT_BE_NULL = "Payment type field cannot be null";
	private static final String PROGRAMMER_FIELD_CANNOT_BE_NULL = "Programmer field cannot be null";
	private Programmer programmer;
	private PaymentType paymentType;

	public Order build() {
		validateFields();
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

	private void validateFields() {
		FieldValidator.validateNotNull(programmer, new OrderBuilderException(
				PROGRAMMER_FIELD_CANNOT_BE_NULL));
		FieldValidator.validateNotNull(paymentType, new OrderBuilderException(
				PAYMENT_TYPE_FIELD_CANNOT_BE_NULL));
	}

}
