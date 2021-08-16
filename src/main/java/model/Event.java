package model;

public class Event extends Task {
	private final String time;
	
	public Event(String desc, String time) {
		super(desc);
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at : " + time + ")";
	}
}
