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
        // Remove o processo da fila quando terminar sua execução
        processQueue.remove(p);
        System.out.println("Processo " + p.getId() + " concluído.");
    }

    private SoProcess getNextProcess() {
        // Retorna o próximo processo da fila de processos
        return processQueue.poll();
    }

    @Override
    public boolean isEmpty() {
        // Verificar se a fila de processos está vazia
        return this.processQueue.isEmpty();
    }

}
