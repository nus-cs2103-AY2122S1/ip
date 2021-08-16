package Model;

public class ToDos extends Task {
	public ToDos(String desc) {
		super(desc);
	}
	
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
