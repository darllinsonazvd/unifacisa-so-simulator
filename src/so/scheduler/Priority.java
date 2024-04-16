package so.scheduler;

import so.SystemCallType;
import so.SystemOperation;
import so.process.SoProcess;
import so.process.SubProcess;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Priority extends Scheduler {
    private LinkedList<SoProcess> processQueue = new LinkedList<>();
    private LinkedList<SubProcess> subProcessQueue = new LinkedList<>();

    public Priority() {
        super();
    }

    @Override
    public SubProcess execute() {
        orderByPriority();
        return subProcessQueue.poll();
    }

    @Override
    public void finish(SoProcess p) {
        // TODO
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(SoProcess p) {
        this.processQueue.add(p);
    }

    private void orderByPriority() {
        Comparator<SoProcess> comparator = (p1, p2) -> {
            if (p1.getPriority().getValue() < p2.getPriority().getValue()) {
                return 1;
            }

            if (p2.getPriority().getValue() < p1.getPriority().getValue()) {
                return -1;
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
