package me.yukun99.ip.tasks;

public class Deadline extends Task {
	private String date;

	public Deadline(String name, String date) {
		super(name);
		this.date = date;
	}

	public void updateDate(String date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Deadline)) {
			return false;
		}
		Deadline deadline = (Deadline) o;
		return super.equals(o) && this.date.equals(deadline.date);
	}

	@Override
	public String saveString() {
		String save = "D:";
		if (this.done) {
			save += "T:";
		} else {
			save += "F:";
		}
		save += this.name + ":" + this.date;
		return save;
	}

	@Override
	public String toString() {
		return " [D]" + super.toString() + " (by: " + date + ")";
	}
}
