package duke.controller;

import duke.exception.DukeException;
import duke.operation.Command;
import duke.tool.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.Scanner;

public class CliController {
	private Storage storage;
	private TaskList taskList;
	private final Ui ui = new Ui();
	private final Parser parser = new Parser();
	private final LogicController logicController = new LogicController();

	public void getCliOutput(Storage st, TaskList tl) {
		storage = st;
		taskList = tl;
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
					logicController.findTask(nextLine, tl);
				} else if (nextCliCommand == Command.TODO) {
					logicController.addTask(nextCliCommand, nextLine, tl, st);
				} else if (nextCliCommand == Command.DEADLINE) {
					logicController.addTask(nextCliCommand, nextLine, tl, st);
				} else if (nextCliCommand == Command.EVENT) {
					logicController.addTask(nextCliCommand, nextLine, tl, st);
				} else if (nextCliCommand == Command.LIST) {
					logicController.listTask();
				} else if (nextCliCommand == Command.DONE) {
					logicController.finishTask(nextLine, tl, st);
				} else if (nextCliCommand == Command.DELETE) {
					logicController.deleteTask(nextLine, tl, st);
				} else {
					parser.invalidTask();
				}
			} catch (DukeException e) {
				System.out.println(e);
			}
			nextLine = in.nextLine();
			nextCliCommand = Command.getCommandWordFromString(nextLine);
		}
		in.close();
	}
}
