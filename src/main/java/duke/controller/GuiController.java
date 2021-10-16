package duke.controller;

import duke.exception.DukeException;
import duke.operation.Command;
import duke.tool.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public class GuiController {
	private Storage storage;
	private TaskList taskList;
	private Ui ui = new Ui();
	private Parser parser = new Parser();
	private LogicController logicController = new LogicController();
	private String guiOutput = "";

	public String getGuiOutput(String guiInput, Storage st, TaskList tl) {
		storage = st;
		taskList = tl;
		Command nextGuiCommand = Command.getCommandWordFromString(guiInput);
		while (true) {
			try {
				if (nextGuiCommand == Command.BYE) {
					return ui.showByeMessage();
				} else if (nextGuiCommand == Command.FIND) {
					guiOutput = logicController.findTask(guiInput, tl);
				} else if (nextGuiCommand == Command.TODO) {
					guiOutput = logicController.addTask(nextGuiCommand, guiInput, tl, st);
				} else if (nextGuiCommand == Command.DEADLINE) {
					guiOutput = logicController.addTask(nextGuiCommand, guiInput, tl, st);
				} else if (nextGuiCommand == Command.EVENT) {
					guiOutput = logicController.addTask(nextGuiCommand, guiInput, tl, st);
				} else if (nextGuiCommand == Command.LIST) {
					guiOutput = logicController.getList(tl);
				} else if (nextGuiCommand == Command.DONE) {
					guiOutput = logicController.finishTask(guiInput, tl, st);
				} else if (nextGuiCommand == Command.DELETE) {
					guiOutput = logicController.deleteTask(guiInput, tl, st);
				} else {
					parser.invalidTask();
				}
			} catch (DukeException e) {
				guiOutput = e.getMessage();
			}
			return guiOutput;
		}
	}
}
