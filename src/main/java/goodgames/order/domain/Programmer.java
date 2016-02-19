package goodgames.order.domain;

import goodgames.common.domain.CoffeeType;

public class Programmer {

	private CoffeeType coffeeType;

	public Programmer(CoffeeType coffeeType) {
		this.coffeeType = coffeeType;
	}

	public CoffeeType getCoffeeType() {
		return coffeeType;
	}

}
