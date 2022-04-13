package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;


public class EditCommand extends Command {
	private final String editedContent;
	String field;
	Task originalTask;
	int taskNo;


	public EditCommand(Task originalTask, String field, String content, int taskNo) {
		this.originalTask = originalTask;
		this.field = field;
		this.editedContent = content;
		this.taskNo = taskNo;
	}


	@Override
	public String execute(TaskList taskList, Ui ui) {
		Task returnedTask;
		if (originalTask instanceof Deadline) {
			returnedTask = executeDeadline(field, editedContent);
		} else if (originalTask instanceof ToDo) {
			returnedTask = executeTodo(field, editedContent);
		} else if (originalTask instanceof Event) {
			returnedTask = executeEvent(field, editedContent);
		} else {
			returnedTask = originalTask;
		}
		taskList.set(taskNo, returnedTask);
		return ui.displayEdit(originalTask, returnedTask);
	}

	public Task executeTodo(String field, String editedContent) {
		Task task = new ToDo(originalTask.getDescription());
		if (field.equalsIgnoreCase("date") || field.equalsIgnoreCase("datetime")) {
			throw new DukeException("The task you wish to edit is a ToDo task which doesn't have a date or datetime field");
		} else if (field.equalsIgnoreCase("desc")) {
			task.setDescription(editedContent);
		} else {
			throw new DukeException("Please specify a valid field - desc");
		}
		return task;
	}

	public Task executeDeadline(String field, String editedContent) {
		Deadline temp = (Deadline) originalTask;
		Deadline deadline = new Deadline(originalTask.getDescription(), temp.getDate());
		if (field.equalsIgnoreCase("datetime")) {
			throw new DukeException("The task you wish to edit is a Deadline task which doesn't have a datetime field");
		} else if (field.equalsIgnoreCase("desc")) {
			deadline.setDescription(editedContent);
		} else if (field.equalsIgnoreCase("date")) {
			deadline.setBy(editedContent);
		} else {
			throw new DukeException("Please specify a valid field - desc or date");
		}
		return deadline;
	}

	public Task executeEvent(String field, String editedContent) {
		Event temp = (Event) originalTask;
		Event event = new Event(originalTask.getDescription(), temp.getAt());
		if (field.equalsIgnoreCase("date")) {
			throw new DukeException("Please use the dateTime command since the task you wish to edit is an event");
		} else if (field.equalsIgnoreCase("desc")) {
			event.setDescription(editedContent);
		} else if (field.equalsIgnoreCase("datetime")) {
			event.setAt(editedContent);
		} else {
			throw new DukeException("Please specify a valid field - desc or datetime");
		}
		return event;
	}
}

