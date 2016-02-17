package goodgames.domain.builder;

import goodgames.domain.CoffeeType;
import goodgames.domain.Programmer;

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
