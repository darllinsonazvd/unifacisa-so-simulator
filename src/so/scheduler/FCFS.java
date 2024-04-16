package so.scheduler;

import so.process.SoProcess;
import so.process.SubProcess;

public class FCFS extends SchedulerQueue {
    public FCFS() {
        super();
    }

    @Override
    public SubProcess execute() {
        if (this.processQueue != null && !this.processQueue.isEmpty()) {

            if (this.subProcessQueue != null && !this.subProcessQueue.isEmpty()) {
                return subProcessQueue.poll();

            }
        }

        return null;
    }

    @Override
    public void finish(SoProcess p) {
        processQueue.remove(p);
        System.out.println("Processo " + p.getId() + " concluído.");
    }

    private SoProcess getNextProcess() {
        return processQueue.poll();
    }

    @Override
    public boolean isEmpty() {
        return this.processQueue.isEmpty();
    }

}
