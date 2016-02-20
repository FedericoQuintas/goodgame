package goodgames;

import goodgames.order.domain.Order;
import goodgames.store.domain.CoffeTimeCalculator;
import goodgames.store.domain.PaymentTypeTimeCalculator;
import goodgames.store.domain.SummaryInformation;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CoffeeShopSimulator {
	private static final Double SELECT_COFFEE_TYPE_TIME = new Double(0.5);
	private static final Double PUT_CUP_UNDER_OUTLET_TIME = new Double(0.25);
	private static final Double PICK_PAID_COFFEE_TIME = new Double(0.25);
	private static final Double TAKE_CUP_AND_LEAVE_TIME = new Double(0.25);
	private static final Double FIND_A_CUP_TIME = new Double(0.25);
	private static final Integer PROGRAMMERS_SELECTING_COFFEE_LIMIT = 10;
	private static final Integer PROGRAMMERS_PAYING_COFFEE_LIMIT = 1;
	private static final Integer PROGRAMMERS_GETTING_PAID_COFFEE_LIMIT = 2;
	private SummaryInformation summaryInformation;
	private Integer numberOfMachines;
	private ExecutorService executor;
	private CoffeTimeCalculator coffeeTimeCalculator;
	private PaymentTypeTimeCalculator paymentTypeTimeCalculator;
	private final Semaphore selectCoffeeTypeSemaphore;
	private final Semaphore payCoffeeTypeSemaphore;
	private Map<Integer, Semaphore> semaphoresByMachine;

	public CoffeeShopSimulator(Integer numberOfMachines) {
		this.numberOfMachines = numberOfMachines;
		coffeeTimeCalculator = new CoffeTimeCalculator();
		paymentTypeTimeCalculator = new PaymentTypeTimeCalculator();
		selectCoffeeTypeSemaphore = new Semaphore(
				PROGRAMMERS_SELECTING_COFFEE_LIMIT);
		payCoffeeTypeSemaphore = new Semaphore(PROGRAMMERS_PAYING_COFFEE_LIMIT);
		generateMachineSemaphores(numberOfMachines);

	}

	private void generateMachineSemaphores(Integer numberOfMachines) {
		semaphoresByMachine = Maps.newHashMap();
		for (int i = 1; i <= numberOfMachines; i++) {
			semaphoresByMachine.put(i, new Semaphore(
					PROGRAMMERS_GETTING_PAID_COFFEE_LIMIT));

		}
	}

	public SummaryInformation getCoffeeMachineInformation(
			final List<Order> orders) {

		executor = Executors.newFixedThreadPool(orders.size());

		summaryInformation = new SummaryInformation(numberOfMachines);

		List<Future<?>> futures = processOrders(orders);
		waitUntilTerminate(futures);
		return summaryInformation;

	}

	private void waitUntilTerminate(List<Future<?>> futures) {
		for (Future<?> future : futures) {
			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				System.out.println("Error while expiration process: "
						+ e.getMessage());
			}
		}
	}

	private List<Future<?>> processOrders(List<Order> orders) {
		List<Future<?>> futures = Lists.newArrayList();
		for (final Order order : orders) {
			futures.add(processOrder(order));
		}
		return futures;
	}

	private Future<?> processOrder(final Order order) {
		return executor.submit(new Runnable() {

			@Override
			public void run() {
				try {
					selectCoffeeType(order);
					payCoffee(order);
					pickCoffeeFromMachine(order);
					addOrderStatsToSummaryInformation(order);
				} catch (Exception e) {
					System.out.println("Error while expiration process: "
							+ e.getMessage());
				}
			}
		});
	}

	private void addOrderStatsToSummaryInformation(Order order) {
		summaryInformation.addOrder(order);
	}

	private void pickCoffeeFromMachine(Order order) {
		selectMachine(order);
		Semaphore getPaidCoffeeTypeSemaphore = getSemaphoreForOrderMachine(order
				.getMachineNumber());
		getPaidCoffeeTypeSemaphore.acquireUninterruptibly();
		findACup(order);
		putCupUnderTheOutlet(order);
		pickPaidCoffeeType(order);
		fillCup(order);
		takeCupAndLeave(order);
		getPaidCoffeeTypeSemaphore.release();
	}

	private Semaphore getSemaphoreForOrderMachine(Integer machineNumber) {
		return semaphoresByMachine.get(machineNumber);
	}

	private void selectMachine(Order order) {
		Integer machineNumber = obtainRandomMachineNumber();
		order.setMachineNumber(machineNumber);
	}

	private Integer obtainRandomMachineNumber() {
		Random rand = new Random();
		Integer machineNumber = rand.nextInt(numberOfMachines) + 1;
		return machineNumber;
	}

	private void takeCupAndLeave(Order order) {
		order.addAccumulatedTime(TAKE_CUP_AND_LEAVE_TIME);
	}

	private void fillCup(Order order) {
		Double timeForOrder = coffeeTimeCalculator.calculateFor(order
				.getProgrammer().getCoffeeType());
		order.addAccumulatedTime(timeForOrder);
	}

	private void pickPaidCoffeeType(Order order) {
		order.addAccumulatedTime(PICK_PAID_COFFEE_TIME);
	}

	private void putCupUnderTheOutlet(Order order) {
		order.addAccumulatedTime(PUT_CUP_UNDER_OUTLET_TIME);
	}

	private void findACup(Order order) {
		order.addAccumulatedTime(FIND_A_CUP_TIME);
	}

	private void payCoffee(Order order) {

		Double paymentTime = paymentTypeTimeCalculator.calculateFor(order
				.getPaymentType());

		payCoffeeTypeSemaphore.acquireUninterruptibly();

		order.addAccumulatedTime(paymentTime);

		payCoffeeTypeSemaphore.release();

	}

	private void selectCoffeeType(final Order order) {

		selectCoffeeTypeSemaphore.acquireUninterruptibly();
		order.addAccumulatedTime(SELECT_COFFEE_TYPE_TIME);
		selectCoffeeTypeSemaphore.release();

	}

}
