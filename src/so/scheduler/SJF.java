package so.scheduler;

import so.SystemCallType;
import so.SystemOperation;
import so.process.SoProcess;
import so.process.SubProcess;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SJF extends Scheduler {
    private LinkedList<SoProcess> processQueue = new LinkedList<>();
    private LinkedList<SubProcess> subProcessQueue = new LinkedList<>();

    public SJF() {
        super();
    }

    @Override
    public SubProcess execute() {
        orderByNumberOfInstructions();
        return this.subProcessQueue.poll();
    }

    @Override
    public void finish(SoProcess p) {
        // TODO
    }

    @Override
    public boolean isEmpty() {
        return this.processQueue.isEmpty();
    }

    @Override
    public void add(SoProcess p) {
        this.processQueue.add(p);
    }

    private void orderByNumberOfInstructions() {
        Comparator<SoProcess> comparator = (p1, p2) -> {
            if (p1.getTimeToExecute() < p2.getTimeToExecute()) {
                return -1;
            }

            if (p1.getTimeToExecute() > p2.getTimeToExecute()) {
                return 1;
            }

            return 0;
        };

        this.processQueue = new LinkedList<>(this.processQueue.stream().sorted(comparator).collect(Collectors.toList()));

        if (!processQueue.isEmpty()) {
            var process = this.processQueue.poll();
            List<SubProcess> subProcesses = SystemOperation.systemCall(
                    SystemCallType.READ_PROCESS,
                    process
            );

            for (SubProcess subProcess : subProcesses) {
                subProcessQueue.add(subProcess);
            }
        }
    }
}


