package so.scheduler;

import so.SystemCallType;
import so.SystemOperation;
import so.process.SoProcess;
import so.process.SubProcess;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Lottery extends Scheduler {
    private LinkedList<SoProcess> processQueue = new LinkedList<>();
    private LinkedList<SubProcess> subProcessQueue = new LinkedList<>();

    public Lottery () {
        super();
    }

    @Override
    public SubProcess execute() {
        lotteryFirstProcess();
        return subProcessQueue.poll();
    }

    private void lotteryFirstProcess() {
        Random random = new Random();

        if (this.processQueue != null && !this.processQueue.isEmpty()) {
            int randomIndex = random.nextInt(processQueue.size());
            SoProcess process = processQueue.get(randomIndex);

            if (process != null) {
                List<SubProcess> subProcesses = SystemOperation.systemCall(
                        SystemCallType.READ_PROCESS,
                        process
                );

                for (SubProcess subProcess : subProcesses) {
                    subProcessQueue.add(subProcess);
                }

                processQueue = new LinkedList<>(processQueue.stream().filter(p -> p.getId() != process.getId()).collect(Collectors.toList()));
            }
        }
    }


    @Override
    public void finish(SoProcess p) {
        // TODO Implementação específica para Loteria
    }

    @Override
    public boolean isEmpty() {
        return this.processQueue.isEmpty();
    }

    @Override
    public void add(SoProcess p) {
        this.processQueue.add(p);
    }
}
