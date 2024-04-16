package so.cpu;

import so.process.SubProcess;

public class Core implements Runnable {
	private int numberOfInstructionsPerClock;
	private SubProcess actuallySubProcess;
	private int numExecutedInstructions;
	private int id;

	public Core(int numberOfInstructionsPerClock, int id) {
		this.numberOfInstructionsPerClock = numberOfInstructionsPerClock;
		this.id = id;
	}

	public Core(int id) {
		this(7, id);

	}

	public SubProcess getActuallySubProcess() {
		return actuallySubProcess;
	}

	public void setActuallySubProcess(SubProcess actuallySubProcess) {
		this.actuallySubProcess = actuallySubProcess;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		this.numExecutedInstructions += numberOfInstructionsPerClock;
		if (this.numExecutedInstructions >= actuallySubProcess.getInstructions()) {
			System.out.println(this.actuallySubProcess.getId());
			this.finishSubProcess();
		}
	}

	private void finishSubProcess() {
		this.actuallySubProcess.getProcess().setNumberOfInstructionsExecuted();

		this.actuallySubProcess = null;
		this.numExecutedInstructions = 0;
	}
}
