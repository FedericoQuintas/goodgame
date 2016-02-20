package goodgames.store.domain;

import goodgames.order.domain.Order;

import java.util.Map;

import com.google.common.collect.Maps;

public class CoffeeSoldSummary {

	private Map<CoffeeType, Integer> totalPerType;
	private Integer totalCoffeeSold = new Integer(0);

	public Integer getTotalCoffeeSold() {
		return totalCoffeeSold;
	}

	public CoffeeSoldSummary() {
		generateDefaultCoffePerTypeValues();
	}

	public void addTotalOfCoffeeSold(Order order) {
		addTotalCoffeeSold();
		addByType(order.getCoffeeType());
	}

	private void addByType(CoffeeType coffeeType) {
		Integer coffeeTypeCurrentTotal = totalPerType.get(coffeeType);
		coffeeTypeCurrentTotal++;
		totalPerType.put(coffeeType, coffeeTypeCurrentTotal);
	}

	private void addTotalCoffeeSold() {
		totalCoffeeSold++;
	}

	private void generateDefaultCoffePerTypeValues() {
		totalPerType = Maps.newHashMap();
		for (CoffeeType coffeeType : CoffeeType.values()) {
			totalPerType.put(coffeeType, 0);
		}
	}

	public Integer getTotalSoldByType(CoffeeType coffeeType) {
		return totalPerType.get(coffeeType);
	}

}
