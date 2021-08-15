package me.yukun99.ip.tasks;

public class ToDo extends Task {
	public ToDo(String name) {
		super(name);
	}

	protected void updateDate(String date) throws IllegalStateException {
		throw new IllegalStateException("ToDo tasks do not have a date to update!");
	}

	@Override
	public String toString() {
		return " [T]" + super.toString();
	}
}
