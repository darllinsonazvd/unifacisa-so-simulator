package so;

import so.process.SoProcess;

public class Execute {
    public static void main(String[] args) {
        SoProcess process1 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 8, 10);
        SoProcess process2 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 8, 5);
        SoProcess process3 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 8, 15);

        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process1);
        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process2);
        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process3);
    }
}

