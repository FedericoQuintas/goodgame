package goodgames.store.resource;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class SummaryInformationDTO {

	@JsonProperty("machines_summary")
	private Map<Integer, MachineSummaryDTO> machinesSummary;
	@JsonProperty("total_ammount_of_time")
	private Double totalAmmountOfTime;
	@JsonProperty("slowest_ammount_of_time")
	private Double slowestAmmountOfTime;
	@JsonProperty("fastest_ammount_of_time")
	private Double fastestAmmountOfTime;
	@JsonProperty("coffee_sold")
	private CoffeeSoldSummaryDTO coffeeSold;
	@JsonProperty("average_ammount_of_time")
	private Double averageAmmountOfTime;

	public Map<Integer, MachineSummaryDTO> getMachinesSummary() {
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

	public CoffeeSoldSummaryDTO getCoffeeSold() {
		return coffeeSold;
	}

	public Double getAverageAmmountOfTime() {
		return averageAmmountOfTime;
	}

	public void setAverageAmmountOfTime(Double averageAmmountOfTime) {
		this.averageAmmountOfTime = averageAmmountOfTime;
	}

	public void setCoffeeSold(CoffeeSoldSummaryDTO coffeeSoldSummaryDTO) {
		this.coffeeSold = coffeeSoldSummaryDTO;
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

	public void setMachinesSummary(
			Map<Integer, MachineSummaryDTO> machinesSummaryDTO) {
		this.machinesSummary = machinesSummaryDTO;
	}

}
