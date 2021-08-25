import java.util.ArrayList;

public class TaskList {

	private ArrayList<Task> tasks;

	TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	public boolean addTask(String[] taskArray) throws DukeTaskDetailsException {
		Task task;
		if (taskArray.length < 2) {
			throw new DukeTaskDetailsException("Please provide task details");
		}
		if (taskArray[0].equals("todo")) {
			task = new Todo(taskArray[1]);
		} else if (taskArray[0].equals("deadline")) {
			String[] deadlineDetails = taskArray[1].split("/by");
			if (deadlineDetails.length != 2) {
				throw new DukeTaskDetailsException("Please provide both task title and deadline, separated by \"/by\"");
			}
			task = new Deadline(deadlineDetails[0], deadlineDetails[1]);
		} else {
			String[] eventDetails = taskArray[1].split("/at");
			if (eventDetails.length != 2) {
				throw new DukeTaskDetailsException("Please provide both event title and date, separated by \"/at\"");
			}
			task = new Event(eventDetails[0], eventDetails[1]);
		}
		this.tasks.add(task);
		System.out.println("\tGot it. I\'ve added this task:");
		System.out.println("\t  " + task.toString());
		System.out.println("\tNow you have " + this.tasks.size() +
						   " tasks in the list.");
		return true;
	}

	public boolean indexCommand(String[] taskArray) throws DukeIndexInputException {
		if (taskArray.length < 2) {
			throw new DukeIndexInputException("Please enter index of the task");
		}
		try {
			int index = Integer.parseInt(taskArray[1]);
			if (taskArray[0].equals("done")) {

				this.tasks.get(index - 1).markAsDone();
				System.out.println("\tNice! I\'ve marked this task as done:");
				System.out.println(" \t" + this.tasks.get(index - 1).toString());
			} else {
				Task removed = this.tasks.remove(index - 1);
				System.out.println("\tNoted. I've removed this task: ");
				System.out.println("\t" + removed.toString());
				System.out.println("\tNow you have " + this.tasks.size() + " tasks in the list");
			}
			return true;
		} catch (NumberFormatException e){
			throw new DukeIndexInputException("Please enter index of the task");
		} catch (IndexOutOfBoundsException e) {
			throw new DukeIndexInputException("Sorry! Unable to find task number " + taskArray[1]);
		}
	}

	public ArrayList<Task> getTasks() {
		return this.tasks;
	}

	public int size() {
		return this.tasks.size();
	}

	public Task get(int index) {
		return this.tasks.get(index);
	}
}
