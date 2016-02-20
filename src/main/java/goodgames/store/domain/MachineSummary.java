package goodgames.store.domain;

import goodgames.order.domain.Order;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Maps;

public class MachineSummary {

	private AtomicInteger totalSold;

	private Map<CoffeeType, AtomicInteger> totalPerType;

	public MachineSummary() {
		totalSold = new AtomicInteger();
		generateDefaultCoffePerTypeValues();
	}

	public Integer getTotalSold() {
		return totalSold.getAndIncrement();
	}

	public void addOrder(Order order) {
		totalSold.incrementAndGet();
		addByType(order.getCoffeeType());
	}

	public Integer getTotalPerType(CoffeeType coffeeType) {
		return totalPerType.get(coffeeType).intValue();
	}

	private void generateDefaultCoffePerTypeValues() {
		totalPerType = Maps.newHashMap();
		for (CoffeeType coffeeType : CoffeeType.values()) {
			totalPerType.put(coffeeType, new AtomicInteger(0));
		}
	}

	private void addByType(CoffeeType coffeeType) {
		AtomicInteger coffeeTypeCurrentTotal = totalPerType.get(coffeeType);
		coffeeTypeCurrentTotal.incrementAndGet();
	}

}
