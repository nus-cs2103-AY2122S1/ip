package duke;

import duke.operation.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.Parser.Parser;
import duke.exception.DukeException;
import duke.operation.Command;

import java.util.Scanner;

/**
 * Duke is the smart assistance to help you track personal tasks.
 * This is the Driver class for Duke application.
 */
public class Duke {

	public static void main(String[] args) {
		Ui.showWelcomeMessage();

		// ask for user input
		Scanner in = new Scanner(System.in);
		String nextLine = in.nextLine();
		Command nextCommand = Command.getCommandWordFromString(nextLine);
		Storage storage = new Storage();
		storage.createFile();
		TaskList taskList = new TaskList();

		while (true) {
			try {
				if (nextCommand == Command.BYE) {
					Ui.showByeMessage();
					break;
				} else if (nextCommand == Command.FIND) {
					taskList.findTask(nextLine);
				} else if (nextCommand == Command.TODO) {
					taskList.addTask(nextCommand, nextLine);
				} else if (nextCommand == Command.DEADLINE) {
					taskList.addTask(nextCommand, nextLine);
				} else if (nextCommand == Command.EVENT) {
					taskList.addTask(nextCommand, nextLine);
				} else if (nextCommand == Command.LIST) {
					taskList.printList();
				} else if (nextCommand == Command.DONE) {
					taskList.finishTask(nextLine);
				} else if (nextCommand == Command.DELETE) {
					taskList.deleteTask(nextLine);
				} else {
					Parser.invalidTask();
				}
			} catch (DukeException e) {
				System.out.println(e);
			}
			nextLine = in.nextLine();
			nextCommand = Command.getCommandWordFromString(nextLine);
			storage.updateFile(taskList);
		}
		in.close();
	}
}
