package me.yukun99.ip.tasks;

import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

public class ToDo extends Task {
	public ToDo(String name) {
		super(name);
	}

	public void updateDate(String date) throws HelpBotInvalidTaskTypeException {
		throw new HelpBotInvalidTaskTypeException(Type.TODO);
	}

	@Override
	public String saveString() {
		String save = "T:";
		if (this.done) {
			save += "T:";
		} else {
			save += "F:";
		}
		save += this.name;
		return save;
	}

	@Override
	public String toString() {
		return " [T]" + super.toString();
	}
}
