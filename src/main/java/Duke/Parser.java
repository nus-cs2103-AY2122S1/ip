package duke;

import duke.exception.*;
import duke.task.Task;

public class Parser {

	private final TaskList tasks;
	private boolean isExit;

	/**
	 * Adds user inputted String to list and prints the user added tasks and the current number of tasks, else
	 * print error message for the error
	 *
	 * @param userInput user inputted String
	 */
	public void commands(String userInput) {
		Task newTask;
		String[] tokens = userInput.split(" ");
		try {
			switch (tokens[0]) {
			case "done":
				tasks.completeAndPrintTask(userInput);
				return;
			case "delete":
				tasks.deleteAndPrintTask(userInput);
				return;
			case "todo":
				newTask = tasks.makeToDoTask(userInput);
				break;
			case "deadline":
				newTask = tasks.makeDeadlineTask(userInput);
				break;
			case "event":
				newTask = tasks.makeEventTask(userInput);
				break;
			case "bye":
				if (tokens.length > 1) {
					throw new InvalidCommandException();
				}
				UI.bye();
				this.isExit = true;
				return;
			case "list":
				if (tokens.length > 1) {
					throw new InvalidCommandException();
				}
				tasks.printList();
				return;
			case "find":
				tasks.findTasks(userInput);
				return;
			default:
				throw new InvalidCommandException();
			}
		} catch (InvalidCommandException | NoTaskDescriptionException | NoDateIndicatorException
				| NoDateException | InvalidFindException err) {
			UI.printErrorMessage(err);
			return;
		}
		tasks.addTask(newTask);
		UI.printLine();
		UI.printRobotMsg("You have added this following task to the list:");
		UI.printMsg(newTask.toString());
		UI.printNoOfTasks(tasks.noOfTasks());
		UI.printLine();
	}

	/**
	 * Return true if parser read user input of "bye".
	 *
	 * @return true if parser has exited else false
	 */
	public boolean isExit() {
		return this.isExit;
	}

	/**
	 * Constructs a parser class that parsers user input.
	 *
	 * @param tasks list of tasks
	 */
	public Parser(TaskList tasks) {
		this.tasks = tasks;
		this.isExit = false;
	}


}
