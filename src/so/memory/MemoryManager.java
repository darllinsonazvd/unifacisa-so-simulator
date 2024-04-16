package so.memory;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import so.process.SoProcess;
import so.process.SubProcess;

public class MemoryManager {
    private SubProcess[][] memoryPhysical;
    private Strategy strategy;
    private int sizePage;
    private Hashtable<String, FrameMemory> memoryLogical;
    private static int INSTRUCTIONS_PER_PROCESS = 7;

    public MemoryManager(int sizePage, int physicalMemory) {
        int pages = physicalMemory / sizePage;
        this.memoryPhysical = new SubProcess[pages][sizePage];
        this.memoryLogical = new Hashtable<>();
        this.sizePage = sizePage;
    }

    public void write(SoProcess p) {
        writeWithPaging(p);
    }

    private void writeWithPaging(SoProcess p) {
        List<Integer> frames = findPages(p);
        double spaces = Math.ceil((double) p.getSubProcesses().size() / this.sizePage);

        if (spaces <= frames.size()) {
            int cont2 = 0;
            for (int i = 0; i < frames.size(); i++) {
                int indexFrame = frames.get(i);
                SubProcess pages[] = this.memoryPhysical[indexFrame];
                int cont = 0;
                while (cont < pages.length && cont2 < p.getSizeInMemory()) {
                    String subProcessesIds = p.getSubProcesses().get(cont2);
                    this.memoryPhysical[indexFrame][cont] = new SubProcess(subProcessesIds, INSTRUCTIONS_PER_PROCESS, p);
                    this.memoryLogical.put(subProcessesIds, new FrameMemory(indexFrame, cont));
                    cont++;
                    cont2++;
                }
            }
        } else {
            System.out.println("Sem memória");
        }

        printMemoryState();
    }

    private List<Integer> findPages(SoProcess p) {
        List<Integer> frames = new LinkedList<>();
        for (int i = 0; i < this.memoryPhysical.length; i++) {
            if (this.memoryPhysical[i][0] == null) {
                frames.add(i);
            }
        }
        return frames;
    }

    private void printMemoryState() {
        System.out.println("|----------------------- MEMORIA -----------------------|\n");

        for (int i = 0; i < memoryPhysical.length; i++) {
            for (int j = 0; j < this.memoryPhysical[i].length; j++) {
                SubProcess sp = this.memoryPhysical[i][j];
                String spId = null;
                if (sp != null) {
                    spId = sp.getId();
                }
                if (j == this.memoryPhysical[i].length - 1) {
                    System.out.println(spId);
                    System.out.println();
                } else {
                    System.out.print(spId + " | ");
                }
            }
        }
    }

    public List<SubProcess> read(SoProcess p) {
        List<String> spsIds = p.getSubProcesses();
        List<SubProcess> sps = new LinkedList<>();
        for (String spId : spsIds) {
            FrameMemory fm = this.memoryLogical.get(spId);
            if (fm != null && fm.getPageNumber() < memoryPhysical.length && fm.getDisplacement() < memoryPhysical[0].length) {
                SubProcess sp = this.memoryPhysical[fm.getPageNumber()][fm.getDisplacement()];
                if (sp != null) {
                    sps.add(sp);
                } else {
                    System.out.println("Erro: Subprocesso " + spId + " não encontrado na memória física.");
                }
            } else {
                System.out.println("Erro: FrameMemory para o Subprocesso " + spId + " é inválido ou fora dos limites da memória física.");
            }
        }
        return sps;
    }

}
