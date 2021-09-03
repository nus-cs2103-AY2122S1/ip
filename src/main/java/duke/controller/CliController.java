package duke.controller;

import duke.parser.Parser;
import duke.exception.DukeException;
import duke.operation.Command;
import duke.operation.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.Scanner;

public class CliController {
	private Storage storage = new Storage();
	private TaskList taskList = new TaskList();
	private Ui ui = new Ui();

	public void getCliOutput() {
		storage.createFile();
		ui.showWelcomeMessage();
		Scanner in = new Scanner(System.in);
		String nextLine = in.nextLine();
		Command nextCliCommand = Command.getCommandWordFromString(nextLine);
		while (true) {
			try {
				if (nextCliCommand == Command.BYE) {
					ui.showByeMessage();
					break;
				} else if (nextCliCommand == Command.FIND) {
					taskList.findTask(nextLine).printFilteredList();
				} else if (nextCliCommand == Command.TODO) {
					taskList.addTask(nextCliCommand, nextLine);
				} else if (nextCliCommand == Command.DEADLINE) {
					taskList.addTask(nextCliCommand, nextLine);
				} else if (nextCliCommand == Command.EVENT) {
					taskList.addTask(nextCliCommand, nextLine);
				} else if (nextCliCommand == Command.LIST) {
					taskList.printList();
				} else if (nextCliCommand == Command.DONE) {
					taskList.finishTask(nextLine);
				} else if (nextCliCommand == Command.DELETE) {
					taskList.deleteTask(nextLine);
				} else {
					Parser.invalidTask();
				}
			} catch (DukeException e) {
				System.out.println(e);
			}
			nextLine = in.nextLine();
			nextCliCommand = Command.getCommandWordFromString(nextLine);
			storage.updateFile(taskList);
		}
		in.close();
	}
}
