package Model;

public class Task {
	private final String desc;
	private boolean isDone = false;
	
	public Task(String desc) {
		this.desc = desc;
	}
	
	public void checkDone() {
		this.isDone = true;
	}
	
	public void uncheckDone() {
		this.isDone = false;
	}
	
	public boolean isDone() {
		return this.isDone;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
