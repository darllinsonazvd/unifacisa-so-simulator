package so;

import so.memory.MemoryManager;
import so.process.PriorityEnum;
import so.process.SoProcess;
import so.process.SubProcess;
import so.scheduler.*;

import java.util.List;

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
//                    scheduler = new FCFS();
//                    scheduler = new Lottery();
//                    scheduler = new Priority();
                    scheduler = new SJF();
                }

                return new SoProcess(processSize, timeToExecute);
            default:
                return null;
        }
    }

    public static SoProcess systemCall(SystemCallType type, Integer processSize, Integer timeToExecute, PriorityEnum priority) {
        switch (type) {
            case CREATE_PROCESS:
                if (mm == null) {
                    mm = new MemoryManager(4, 256);
                }

                if (scheduler == null) {
//                    scheduler = new FCFS();
//                    scheduler = new Lottery();
//                    scheduler = new Priority();
                    scheduler = new SJF();
                }

                return new SoProcess(processSize, timeToExecute, priority);
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
