package goodgames.store.domain;

import java.util.Map;

import com.google.common.collect.Maps;

public class PaymentTypeTimeCalculator {

	private static final Double CASH_TIME = new Double(0.5);
	private static final Double CARD_TIME = new Double(0.25);
	private Map<PaymentType, Double> timePerType;

	public PaymentTypeTimeCalculator() {
		generateDefaultCoffePerTypeValues();
	}

	public Double calculateFor(PaymentType paymentType) {
		return timePerType.get(paymentType);
	}

	private void generateDefaultCoffePerTypeValues() {
		timePerType = Maps.newHashMap();
		timePerType.put(PaymentType.CARD, CARD_TIME);
		timePerType.put(PaymentType.CASH, CASH_TIME);
	}
}
