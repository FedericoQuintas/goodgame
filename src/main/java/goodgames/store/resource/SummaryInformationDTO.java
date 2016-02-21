package goodgames.store.resource;

import goodgames.store.domain.CoffeeSoldSummary;
import goodgames.store.domain.MachineSummary;

import java.util.Map;

public class SummaryInformationDTO {

	private Map<Integer, MachineSummary> machinesSummary;
	private Double totalAmmountOfTime;
	private Double slowestAmmountOfTime;
	private Double fastestAmmountOfTime;
	private CoffeeSoldSummary coffeeSold;
	private Double averageAmmountOfTime;

	public Map<Integer, MachineSummary> getMachinesSummary() {
		return machinesSummary;
	}

	public Double getTotalAmmountOfTime() {
		return totalAmmountOfTime;
	}

	public Double getSlowestAmmountOfTime() {
		return slowestAmmountOfTime;
	}

	public Double getFastestAmmountOfTime() {
		return fastestAmmountOfTime;
	}

	public CoffeeSoldSummary getCoffeeSold() {
		return coffeeSold;
	}

	public Double getAverageAmmountOfTime() {
		return averageAmmountOfTime;
	}

	public void setAverageAmmountOfTime(Double averageAmmountOfTime) {
		this.averageAmmountOfTime = averageAmmountOfTime;
	}

	public void setCoffeeSold(CoffeeSoldSummary coffeeSold) {
		this.coffeeSold = coffeeSold;
	}

	public void setFastestAmmountOfTime(Double fastestAmmountOfTime) {
		this.fastestAmmountOfTime = fastestAmmountOfTime;
	}

	public void setSlowestAmmountOfTime(Double slowestAmmountOfTime) {
		this.slowestAmmountOfTime = slowestAmmountOfTime;
	}

	public void setTotalAmmountOfTime(Double totalAmmountOfTime) {
		this.totalAmmountOfTime = totalAmmountOfTime;
	}

	public void setMachinesSummary(Map<Integer, MachineSummary> machinesSummary) {
		this.machinesSummary = machinesSummary;
	}

}
