package goodgames.store.service;

import goodgames.CoffeeStoreSimulator;
import goodgames.config.GetPropertyValues;
import goodgames.order.domain.Order;
import goodgames.order.service.OrderService;
import goodgames.store.domain.SummaryInformation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Resource
	private OrderService orderService;
	private Integer numberOfMachines;
	private CoffeeStoreSimulator coffeeShopSimulator;

	@Resource(name = "getPropertyValues")
	private GetPropertyValues getPropertyValues;

	@PostConstruct
	public void initialize() {
		numberOfMachines = getNumberOfMachines();
		coffeeShopSimulator = new CoffeeStoreSimulator(numberOfMachines);
	}

	@Override
	public SummaryInformation getSummaryInformation(Integer numberOfEspresso,
			Integer numberOfLatte, Integer numberOfCapuccino) {

		List<Order> orders = orderService.generateOrders(numberOfEspresso,
				numberOfLatte, numberOfCapuccino);

		return coffeeShopSimulator.getCoffeeMachineInformation(orders);

	}

	private Integer getNumberOfMachines() {
		return getPropertyValues.getNumberOfMachines();
	}
}
