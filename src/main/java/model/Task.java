package model;

/**
 * abstract class of task that represents a thing to be done
 */
public abstract class Task {
	private final String desc;
	private boolean isDone = false;
	
	/**
	 * constructor of the abstract class, to be initialized with the subclass
	 *
	 * @param desc description
	 */
	public Task(String desc) {
		this.desc = desc;
	}
	
	/**
	 * mark the task as done
	 */
	public void checkDone() {
		this.isDone = true;
	}
	
	/**
	 * get the status of the task
	 *
	 * @return boolean representing whether the task is done
	 */
	public boolean isDone() {
		return this.isDone;
	}
	
	/**
	 * get description of the task
	 *
	 * @return string representing the description
	 */
	public String getDesc() {
		return this.desc;
	}
	
	/**
	 * string representation of task class
	 *
	 * @return [X] or [ ] indicating whether the task is done and the description
	 */
	@Override
	public String toString() {
		return (this.isDone() ? "[X] " : "[ ] ") + this.desc;
	}
}
