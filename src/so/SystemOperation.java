package so;

import java.util.List;

import so.memory.MemoryManager;
import so.process.SoProcess;
import so.process.SubProcess;
import so.scheduler.Lottery;
import so.scheduler.Scheduler;

public class SystemOperation {

    private static MemoryManager mm;
    private static Scheduler scheduler;

    public static SoProcess systemCall(SystemCallType type, int processSize) {
        switch (type) {
            case CREATE_PROCESS:
                if (mm == null) {
                    mm = new MemoryManager(4, 256);
                }
                if (scheduler == null) {
                    scheduler = new Lottery();
                }
                return new SoProcess(processSize);
            default:
                return null; // Adicione tratamento para outros tipos de chamadas de sistema, se necessário
        }
    }

    public static List<SubProcess> systemCall(SystemCallType type, SoProcess p) {
        switch (type) {
            case WRITE_PROCESS:
                mm.write(p);
                scheduler.add(p);
                break;
//            case CLOSE_PROCESS:
//                mm.delete(p);
//                scheduler.finish(p);
//                break;
            case READ_PROCESS:
                return mm.read(p);
            default:
                // Adicione tratamento para outros tipos de chamadas de sistema, se necessário
                return null;
        }
        return null;
    }
}
