package duke.controller;

import duke.exception.DukeException;
import duke.operation.Command;
import duke.operation.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public class GuiController {
	private Storage storage = new Storage();
	private TaskList taskList = new TaskList();
	private Ui ui = new Ui();
	private String guiOutput = "";

	public String getGuiOutput(String guiInput) {
		storage.createFile();
		Command nextGuiCommand = Command.getCommandWordFromString(guiInput);
		while (true) {
			try {
				if (nextGuiCommand == Command.BYE) {
					return ui.showByeMessage();
				} else if (nextGuiCommand == Command.FIND) {
					guiOutput = taskList.findTask(guiInput).getFilteredTaskListAsString();
				} else if (nextGuiCommand == Command.TODO) {
					guiOutput = taskList.addTask(nextGuiCommand, guiInput);
				} else if (nextGuiCommand == Command.DEADLINE) {
					guiOutput = taskList.addTask(nextGuiCommand, guiInput);
				} else if (nextGuiCommand == Command.EVENT) {
					guiOutput = taskList.addTask(nextGuiCommand, guiInput);
				} else if (nextGuiCommand == Command.LIST) {
					guiOutput = taskList.getTaskListAsString();
				} else if (nextGuiCommand == Command.DONE) {
					guiOutput = taskList.finishTask(guiInput);
				} else if (nextGuiCommand == Command.DELETE) {
					guiOutput = taskList.deleteTask(guiInput);
				} else {
					Parser.invalidTask();
				}
			} catch (DukeException e) {
				guiOutput = e.getMessage();
			}
			storage.updateFile(taskList);
			return guiOutput;
		}
	}
}
