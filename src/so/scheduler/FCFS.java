package so.scheduler;

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
}
