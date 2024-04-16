package so.scheduler;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import so.SystemCallType;
import so.SystemOperation;
import so.cpu.Core;
import so.process.SoProcess;
import so.process.SubProcess;

public class Priority extends Scheduler {

	private PriorityQueue<SubProcess> queue;
	private int order;

	public Priority(int order) {
		super();
		this.order = order;

		Comparator<SubProcess> c = new Comparator<SubProcess>() {

			@Override
			public int compare(SubProcess sp1, SubProcess sp2) {
				return 0;
			}
		};
		this.queue = new PriorityQueue<>(c);
	}

    @Override
    public SubProcess execute() {
        return null;
    }

    @Override
    public void finish(SoProcess p) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(SoProcess p) {

    }
}
