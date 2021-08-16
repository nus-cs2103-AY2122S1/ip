package Model;

public class Task {
	private String desc;
	
	Task(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
