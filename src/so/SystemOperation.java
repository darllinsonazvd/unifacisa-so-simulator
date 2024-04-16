package so;

import java.util.List;

import so.memory.MemoryManager;
import so.process.SoProcess;
import so.process.SubProcess;
import so.scheduler.Lottery;
import so.scheduler.Priority;
import so.scheduler.SJF;
import so.scheduler.Scheduler;

public class SystemOperation {

    private static MemoryManager mm;
    private static Scheduler scheduler;

    public static SoProcess systemCall(SystemCallType type, Integer processSize, Integer timeToExecute) {
        switch (type) {
            case CREATE_PROCESS:
                if (mm == null) {
                    mm = new MemoryManager(4, 256);
                }

                if (scheduler == null) {
                    scheduler = new SJF();
                }

                return new SoProcess(processSize, timeToExecute);
            default:
                return null;
        }
    }

    public static List<SubProcess> systemCall(SystemCallType type, SoProcess p) {
        switch (type) {
            case WRITE_PROCESS:
                mm.write(p);
                scheduler.add(p);
                break;
            case CLOSE_PROCESS:
//                mm.delete(p);
                scheduler.finish(p);
                break;
            case READ_PROCESS:
                return mm.read(p);
            default:
                return null;
        }
        return null;
    }
}
