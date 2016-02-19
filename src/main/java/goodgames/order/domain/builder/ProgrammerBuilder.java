package goodgames.order.domain.builder;

import goodgames.order.domain.Programmer;
import goodgames.store.domain.CoffeeType;

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
