package goodgames.domain.factory;

import goodgames.domain.Order;
import goodgames.domain.Programmer;

public class OrderBuilder {

	private Programmer programmer;

	public Order build() {
		return new Order(programmer);
	}


	public OrderBuilder withProgrammer(Programmer programmer) {
		this.programmer = programmer;
		return this;
	}

}
