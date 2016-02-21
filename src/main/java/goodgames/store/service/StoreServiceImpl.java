package goodgames.store.service;

import goodgames.CoffeeStoreSimulator;
import goodgames.config.GetPropertyValues;
import goodgames.order.domain.Order;
import goodgames.order.service.OrderService;
import goodgames.store.domain.SummaryInformation;
import goodgames.store.domain.factory.SummaryInformationDTOFactory;
import goodgames.store.resource.SummaryInformationDTO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Resource
	private OrderService orderService;

	private Integer numberOfMachines;

	@Override
	public SummaryInformationDTO getSummaryInformation(
			Integer numberOfEspresso, Integer numberOfLatte,
			Integer numberOfCapuccino) {

		List<Order> orders = orderService.generateOrders(numberOfEspresso,
				numberOfLatte, numberOfCapuccino);

		numberOfMachines = getNumberOfMachines();

		SummaryInformation summaryInformation = new CoffeeStoreSimulator(
				numberOfMachines).getCoffeeMachineInformation(orders);

		return SummaryInformationDTOFactory.create(summaryInformation);
	}

	private Integer getNumberOfMachines() {
		return GetPropertyValues.getInstance().getNumberOfMachines();
	}
}
