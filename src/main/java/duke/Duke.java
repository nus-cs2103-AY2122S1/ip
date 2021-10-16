package duke;

import duke.controller.CliController;
import duke.controller.GuiController;
import duke.tool.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

/**
 * @author Yang Yuzhao
 * @version 1.0
 */

/**
 * Duke is the smart assistance to help you track personal tasks.
 */
public class Duke {
	private CliController cliController = new CliController();
	private GuiController guiController = new GuiController();
	private Ui ui = new Ui();
	private Storage storage;
	private TaskList taskList;

	public Duke(){
		storage = new Storage("output/duke.txt");
		try {
			taskList = new TaskList(storage.loadData());
		} catch (IOException e){
			ui.showLoadingErrorMessage();
			taskList = new TaskList();
		}
	}

	public void run() {
		cliController.getCliOutput(storage, taskList);
	}

	public String getResponse(String input) {
		return guiController.getGuiOutput(input, storage, taskList);
	}

	public Ui getUi(){
		return ui;
	}

	public static void main(String[] args) {
		new Duke().run();
	}
}
