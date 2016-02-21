package goodgames.store.domain;

import java.util.Map;

import com.google.common.collect.Maps;

public class CoffeeTimeCalculator {

	private static final Double ESPRESSO_TIME = new Double(0.25);
	private static final Double LATTE_TIME = new Double(0.5);
	private static final Double CAPUCCINO_TIME = new Double(0.75);
	private Map<CoffeeType, Double> timePerType;

	public CoffeeTimeCalculator() {
		generateDefaultCoffePerTypeValues();
	}

	public Double calculateFor(CoffeeType coffeeType) {
		return timePerType.get(coffeeType);
	}

	private void generateDefaultCoffePerTypeValues() {
		timePerType = Maps.newHashMap();
		timePerType.put(CoffeeType.ESPRESSO, ESPRESSO_TIME);
		timePerType.put(CoffeeType.LATTE, LATTE_TIME);
		timePerType.put(CoffeeType.CAPUCCINO, CAPUCCINO_TIME);
	}
}
