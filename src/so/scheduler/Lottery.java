package so.scheduler;

import so.SystemCallType;
import so.SystemOperation;
import so.process.SoProcess;
import so.process.SubProcess;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Lottery extends Scheduler {
    private LinkedList<SoProcess> processQueue = new LinkedList<SoProcess>();
    private LinkedList<SubProcess> subProcessQueue = new LinkedList<SubProcess>();
    public Lottery () {
        super();
    }


    @Override
    public SubProcess execute() {
        lotteryFirstProcess();
        if (this.processQueue != null && !this.processQueue.isEmpty()) {

            if (this.subProcessQueue != null && !this.subProcessQueue.isEmpty()) {
                return subProcessQueue.poll();

            }
        }

        return null;

    }

    private SoProcess lotteryFirstProcess() {
        Random random = new Random();

        if (this.processQueue != null && !this.processQueue.isEmpty()) {

            int randomIndex = random.nextInt(processQueue.size());
            SoProcess process = processQueue.get(randomIndex);



            if (process != null) {
                System.out.println("to aqui");
                List<SubProcess> subProcesses = (List<SubProcess>) SystemOperation.systemCall(
                        SystemCallType.READ_PROCESS,
                        process
                );

                for (SubProcess subProcess : subProcesses) {
                    subProcessQueue.add(subProcess);
                }

                processQueue.removeIf(p -> p.getId() == process.getId());
            }
            return process;
        }
        return null;
    }


    @Override
    public void finish(SoProcess p) {
        // Implementação específica para Loteria
    }

    @Override
    public boolean isEmpty() {

        // Verificar se a fila de processos está vazia
        return this.processQueue.isEmpty();
    }

    @Override
    public void add(SoProcess p) {
        this.processQueue.add(p);
    }


}
