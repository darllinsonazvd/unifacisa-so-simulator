package so.cpu;

import java.util.Timer;
import java.util.TimerTask;

import so.process.SubProcess;
import so.scheduler.Scheduler;

public class CpuManager {
	private Scheduler scheduler;
	private Core[] cores;
	public static int CLOCK = 1000;
	public static int NUMBER_OF_CORES = 4;
	public static int NUMBER_OF_INSTRUCTIONS_PER_CLOCK = 7;

	public CpuManager(Scheduler scheduler) {
		this.cores = new Core[NUMBER_OF_CORES];
		for (int i = 0; i < this.cores.length; i++) {
			this.cores[i] = new Core(NUMBER_OF_INSTRUCTIONS_PER_CLOCK);

		}
		this.scheduler = scheduler;
		this.clock();

	}

	public void registerProcess(int coreIndex, SubProcess sp) {
		this.cores[coreIndex].setActuallySubProcess(sp);
	}

	public void clock() {
		Timer time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				executeProcesses();

			}

		}, 0, CLOCK);

	}

	private void executeProcesses() {

		for (Core core : this.cores) {
			SubProcess sp = scheduler.execute();
			if (sp != null) {

				core.setActuallySubProcess(sp);
				core.run();

			}

		}

	}

	public Core[] getCores() {
		return this.cores;
	}

}
