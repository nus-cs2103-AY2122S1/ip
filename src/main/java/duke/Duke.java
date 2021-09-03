package duke;

import duke.controller.CliController;
import duke.controller.GuiController;

/**
 * Duke is the smart assistance to help you track personal tasks.
 */
public class Duke {
	private CliController cliController = new CliController();
	private GuiController guiController = new GuiController();

	public void run() {
		cliController.getCliOutput();
	}

	public String getResponse(String input) {
		return guiController.getGuiOutput(input);
	}

	public static void main(String[] args) {
		new Duke().run();
	}
}
