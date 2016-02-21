package goodgames.store.domain;

public enum CoffeeType {
	ESPRESSO("ESPRESSO"), CAPUCCINO("CAPUCCINO"), LATTE("LATTE");

	private String name;

	public String getName() {
		return name;
	}

	private CoffeeType(String name) {
		this.name = name;
	}
}
