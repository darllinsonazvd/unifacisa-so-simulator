package so.process;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SubProcess {
    private int instructions;
    private String id;
    private SoProcess process;
    private int timeToExecute;

    public SubProcess(String processId, int numberOfInstructions, SoProcess p) {
        this.id = processId;
        this.setInstructions(numberOfInstructions);
        this.process = p;
        Random rand = new Random();
        List<Integer> givenList = Arrays.asList(100, 200, 300, 400, 500, 600, 700, 800, 900);
        this.timeToExecute = givenList.get(rand.nextInt(givenList.size()));
    }

    public SubProcess() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SoProcess getProcess() {
        return process;
    }

    public int getInstructions() {
        return instructions;
    }

    public void setInstructions(int instructions) {
        this.instructions = instructions;
    }

    public int getTimeToExecute() {
        return timeToExecute;
    }

    public void setTimeToExecute(int timeToExecute) {
        this.timeToExecute = timeToExecute;
    }

    public void executeInstruction() {
        // Aqui você implementa a execução de uma instrução do subprocesso
        // Por exemplo, você pode reduzir o número de instruções restantes em 1
        if (instructions > 0) {
            instructions--;
        }
    }

    public int getTotalInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return "SubProcess{" +
                "id='" + id + '\'' +
                '}';
    }
}
