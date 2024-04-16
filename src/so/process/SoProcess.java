package so.process;

import java.util.LinkedList;
import java.util.List;

//import so.memory.AdressMemory;

public class SoProcess {
	private int sizeInMemory;
	private String id;
//	private AdressMemory am;
	private static int count;

	// private int numberOfInstructions;
	private List<String> subProcesses;

	public SoProcess(int sizeInMemory) {
		count++;
		this.id = "P" + count ;
		this.sizeInMemory = sizeInMemory;
		this.subProcesses = this.getSubProcesses();

	}

	public List<String> getSubProcesses() {
		if (this.subProcesses == null || this.subProcesses.isEmpty()) {
			List<String> sp = new LinkedList<>();
			for (int i = 0; i < this.getSizeInMemory(); i++) {
				sp.add(this.getId() + ( "-SP" + i ));
			}
			this.subProcesses = sp;
		}

		return subProcesses;
	}

	public int getSizeInMemory() {
		return sizeInMemory;
	}

	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SoProcess{" +
				"id='" + id + '\'' +
				'}';
	}
}
