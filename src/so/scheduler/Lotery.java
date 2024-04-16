//package so.scheduler;
//
//import so.SystemCallType;
//import so.SystemOperation;
//import so.process.SoProcess;
//import so.process.SubProcess;
//
//import java.util.List;
//
//public class Lotery extends Scheduler {
//
//    @Override
//    public void execute(SoProcess p) {
//        List<SubProcess> sps = SystemOperation.systemCall(SystemCallType.READ_PROCESS, p);
//        // Implementação específica para Loteria
//    }
//
//    @Override
//    public void finish(SoProcess p) {
//        // Implementação específica para Loteria
//    }
//
//    @Override
//    public boolean isEmpty() {
//		return false;
//        // Implementação específica para Loteria
//    }
//
//	@Override
//	public void add(SoProcess p) {
//		// TODO Auto-generated method stub
//		
//	}
//}
