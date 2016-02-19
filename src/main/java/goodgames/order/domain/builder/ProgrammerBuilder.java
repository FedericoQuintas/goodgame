package goodgames.order.domain.builder;

import goodgames.common.domain.CoffeeType;
import goodgames.order.domain.Programmer;

public class ProgrammerBuilder {

	private CoffeeType coffeeType;

	public Programmer build() {
		return new Programmer(coffeeType);
	}

	public ProgrammerBuilder withFavouriteCoffee(CoffeeType coffeeType) {
		this.coffeeType = coffeeType;
		return this;
	}

}
