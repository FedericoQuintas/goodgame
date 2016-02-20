package goodgames.store.domain;

import goodgames.order.domain.Order;

import java.util.Map;

import com.google.common.collect.Maps;

public class MachineSummary {

	private Integer totalSold;

	private Map<CoffeeType, Integer> totalPerType;

	public MachineSummary() {
		totalSold = 0;
		generateDefaultCoffePerTypeValues();
	}

	public Integer getTotalSold() {
		return totalSold;
	}

	public void addOrder(Order order) {
		totalSold++;
		addByType(order.getProgrammer().getCoffeeType());
	}

	public Integer getTotalPerType(CoffeeType coffeeType) {
		return totalPerType.get(coffeeType);
	}

	private void generateDefaultCoffePerTypeValues() {
		totalPerType = Maps.newHashMap();
		for (CoffeeType coffeeType : CoffeeType.values()) {
			totalPerType.put(coffeeType, 0);
		}
	}

	private void addByType(CoffeeType coffeeType) {
		Integer coffeeTypeCurrentTotal = totalPerType.get(coffeeType);
		coffeeTypeCurrentTotal++;
		totalPerType.put(coffeeType, coffeeTypeCurrentTotal);
	}

}
