package so.scheduler;

import so.cpu.CpuManager;
import so.process.SoProcess;
import so.process.SubProcess;

public abstract class Scheduler {

    private CpuManager cm;

    public Scheduler() {
        this.cm = new CpuManager(this);
    }

    public CpuManager getCm() {
        return cm;
    }

    public abstract SubProcess execute();

    public abstract void finish(SoProcess p);

    public abstract boolean isEmpty();

	public abstract void add(SoProcess p);
}
