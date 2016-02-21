package goodgames.order.service;

import goodgames.order.domain.Order;

import java.util.List;

public interface OrderService {

	public List<Order> generateOrders(Integer numberOfEspresso, Integer numberOfLatte,
			Integer numberOfCapuccino);

}
