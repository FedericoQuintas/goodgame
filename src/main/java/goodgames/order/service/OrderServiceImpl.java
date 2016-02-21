package goodgames.order.service;

import goodgames.common.validator.FieldValidator;
import goodgames.order.domain.Order;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.order.exception.OrderCreationException;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.PaymentType;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private static final String NUMBER_OF_LATTE_ORDERS_CANNOT_BE_NULL = "Number of Latte Orders cannot be null";
	private static final String NUMBER_OF_CAPUCCINO_ORDERS_CANNOT_BE_NULL = "Number of Capuccino Orders cannot be null";
	private static final String NUMBER_OF_ESPRESSO_ORDERS_CANNOT_BE_NULL = "Number of Espresso Orders cannot be null";
	private List<PaymentType> paymentTypes;

	public OrderServiceImpl() {
		loadPaymentTypes();
	}

	public List<Order> generateOrders(Integer numberOfEspressoOrders,
			Integer numberOfLatteOrders, Integer numberOfCapuccinoOrders) {
		validateNumbers(numberOfCapuccinoOrders, numberOfEspressoOrders,
				numberOfLatteOrders);

		List<Order> orders = Lists.newArrayList();
		List<Order> espressoOrders = generateOrders(numberOfEspressoOrders,
				CoffeeType.ESPRESSO);
		addOrders(orders, espressoOrders);
		List<Order> latteOrders = generateOrders(numberOfLatteOrders,
				CoffeeType.LATTE);
		addOrders(orders, latteOrders);
		List<Order> capuccinoOrders = generateOrders(numberOfCapuccinoOrders,
				CoffeeType.CAPUCCINO);
		addOrders(orders, capuccinoOrders);
		return orders;
	}

	private void validateNumbers(Integer numberOfCapuccinoOrders,
			Integer numberOfEspressoOrders, Integer numberOfLatteOrders) {
		FieldValidator.validateNotNull(numberOfCapuccinoOrders,
				new OrderCreationException(
						NUMBER_OF_CAPUCCINO_ORDERS_CANNOT_BE_NULL));
		FieldValidator.validateNotNull(numberOfEspressoOrders,
				new OrderCreationException(
						NUMBER_OF_ESPRESSO_ORDERS_CANNOT_BE_NULL));
		FieldValidator
				.validateNotNull(numberOfLatteOrders,
						new OrderCreationException(
								NUMBER_OF_LATTE_ORDERS_CANNOT_BE_NULL));
	}

	private void addOrders(List<Order> orders, List<Order> specificCoffeeOrders) {
		if (!specificCoffeeOrders.isEmpty()) {
			orders.addAll(specificCoffeeOrders);
		}
	}

	private List<Order> generateOrders(Integer expectedOrders,
			CoffeeType coffeeType) {

		List<Order> orders = Lists.newArrayList();
		for (int i = 0; i < expectedOrders; i++) {
			orders.add(generateOrder(coffeeType));
		}
		return orders;
	}

	private Order generateOrder(CoffeeType coffeeType) {
		OrderBuilder orderBuilder = new OrderBuilder();

		PaymentType paymentType = obtainRandomPaymentType();
		Order order = orderBuilder.withPaymentType(paymentType)
				.withCoffeeType(coffeeType).build();
		return order;
	}

	private PaymentType obtainRandomPaymentType() {
		Random rand = new Random();
		Integer paymentTypeIndex = rand.nextInt(PaymentType.values().length);
		return paymentTypes.get(paymentTypeIndex);
	}

	private void loadPaymentTypes() {
		paymentTypes = Lists.newArrayList();
		for (PaymentType paymentType : PaymentType.values()) {
			paymentTypes.add(paymentType);
		}
	}

}
