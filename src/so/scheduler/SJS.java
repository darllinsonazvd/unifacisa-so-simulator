//package so.scheduler;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.PriorityQueue;
//
//import so.SystemCallType;
//import so.SystemOperation;
//import so.cpu.Core;
//import so.process.SoProcess;
//import so.process.SubProcess;
//
//public class SJS extends Scheduler {
//
//	private PriorityQueue<SubProcess> queue;
//	private int order;
//
//	public SJS(int order) {
//		super();
//		this.order = order;
//
//		Comparator<SubProcess> c = new Comparator<SubProcess>() {
//
//			@Override
//			public int compare(SubProcess sp1, SubProcess sp2) {
//				return sp1.getTimeToExecute() >= sp2.getTimeToExecute()
//						? 1 : -1;
//			}
//		};
//		this.queue = new PriorityQueue<>(c);
//	}
//}
//
//
