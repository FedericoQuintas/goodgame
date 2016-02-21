package goodgames.order.service;

import goodgames.order.domain.Order;
import goodgames.order.domain.builder.OrderBuilder;
import goodgames.store.domain.CoffeeType;
import goodgames.store.domain.PaymentType;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private List<PaymentType> paymentTypes;

	public OrderServiceImpl() {
		loadPaymentTypes();
	}

	public List<Order> generateOrders(Integer numberOfEspressoOrders,
			Integer numberOfLatteOrders, Integer numberOfCapuccinoOrders) {
		List<Order> orders = Lists.newArrayList();
		orders.addAll(generateOrders(numberOfEspressoOrders,
				CoffeeType.ESPRESSO));
		orders.addAll(generateOrders(numberOfLatteOrders, CoffeeType.LATTE));
		orders.addAll(generateOrders(numberOfCapuccinoOrders,
				CoffeeType.CAPUCCINO));
		return orders;
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
