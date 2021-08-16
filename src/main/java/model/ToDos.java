package model;

/**
 * a task containing description only
 */
public class ToDos extends Task {
	public ToDos(String desc) {
		super(desc);
	}
	
	/**
	 * String representation of todos, marked with [T] and the desc
	 *
	 * @return string
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
