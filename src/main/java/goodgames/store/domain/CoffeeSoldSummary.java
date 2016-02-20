package goodgames.store.domain;

import goodgames.order.domain.Order;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Maps;

public class CoffeeSoldSummary {

	private Map<CoffeeType, AtomicInteger> totalPerType;
	private AtomicInteger totalCoffeeSold = new AtomicInteger(0);

	public Integer getTotalCoffeeSold() {
		return totalCoffeeSold.intValue();
	}

	public CoffeeSoldSummary() {
		generateDefaultCoffeePerTypeValues();
	}

	public void addTotalOfCoffeeSold(Order order) {
		addTotalCoffeeSold();
		addByType(order.getCoffeeType());
	}

	private void addByType(CoffeeType coffeeType) {
		AtomicInteger coffeeTypeCurrentTotal = totalPerType.get(coffeeType);
		coffeeTypeCurrentTotal.incrementAndGet();
	}

	private void addTotalCoffeeSold() {
		totalCoffeeSold.incrementAndGet();
	}

	private void generateDefaultCoffeePerTypeValues() {
		totalPerType = Maps.newHashMap();
		for (CoffeeType coffeeType : CoffeeType.values()) {
			totalPerType.put(coffeeType, new AtomicInteger(0));
		}
	}

	public Integer getTotalSoldByType(CoffeeType coffeeType) {
		return totalPerType.get(coffeeType).intValue();
	}

}
