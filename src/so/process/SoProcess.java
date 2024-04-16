package so.process;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//import so.memory.AdressMemory;

public class SoProcess {
	private int sizeInMemory;
	private String id;
	private List<String> subProcesses;
	private PriorityEnum priority;
	private Integer timeToExecute;
	private Integer numberOfInstructions;
	private Integer numberOfInstructionsExecuted;

	private final Integer MINIMUM_PRIORITY = 1;
	private final Integer MAXIMUM_PRIORITY = 3;
	private static int count;

	public SoProcess(int sizeInMemory, Integer timeToExecute) {
		Random random = new Random();
		var randomPriority = random.nextInt(MINIMUM_PRIORITY, MAXIMUM_PRIORITY + 1);

		count++;
		this.id = "P" + count ;
		this.sizeInMemory = sizeInMemory;
		this.subProcesses = this.getSubProcesses();

		var priorities = PriorityEnum.values();

		this.priority = Arrays.stream(priorities).filter(priorityEnum -> priorityEnum.getValue() == randomPriority).findFirst().get();
		System.out.println(id + " - Prioridade -> " + priority.getValue());

		this.numberOfInstructions = this.subProcesses.size() * 7;
		this.numberOfInstructionsExecuted = 0;
		this.timeToExecute = timeToExecute;
	}

	public List<String> getSubProcesses() {
		if (this.subProcesses == null || this.subProcesses.isEmpty()) {
			List<String> sp = new LinkedList<>();
			for (int i = 0; i < this.getSizeInMemory(); i++) {
				sp.add(this.getId() + ( "-SP" + i ));
			}
			this.subProcesses = sp;
		}

		return subProcesses;
	}

	public int getSizeInMemory() {
		return sizeInMemory;
	}

	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	public Integer getTimeToExecute() {
		return timeToExecute;
	}

	public void setTimeToExecute(Integer timeToExecute) {
		this.timeToExecute = timeToExecute;
	}

	public Integer getNumberOfInstructionsExecuted() {
		return numberOfInstructionsExecuted;
	}

	public void setNumberOfInstructionsExecuted() {
		this.numberOfInstructionsExecuted += 7;
	}

	public Integer getNumberOfInstructions() {
		return numberOfInstructions;
	}

	public void setNumberOfInstructions(Integer numberOfInstructions) {
		this.numberOfInstructions = numberOfInstructions;
	}

	@Override
	public String toString() {
		return "SoProcess{" +
				"sizeInMemory=" + sizeInMemory +
				", id='" + id + '\'' +
				", priority=" + priority +
				'}';
	}
}
