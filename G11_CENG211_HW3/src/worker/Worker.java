package worker;

public abstract class Worker implements IWorker {
	
	public Worker() {
		
	}
	
	public abstract String toString();
	
	public abstract boolean equals(Object other);

}
