package model;

public class Task {
	private final String desc;
	private boolean isDone = false;
	
	public Task(String desc) {
		this.desc = desc;
	}
	
	public void checkDone() {
		this.isDone = true;
	}
	
	public boolean isDone() {
		return this.isDone;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	@Override
	public String toString() {
		return (this.isDone() ? "[X] " : "[ ] ") + this.desc;
	}
}
