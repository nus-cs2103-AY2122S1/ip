package duke;

import duke.controller.CliController;
import duke.controller.GuiController;
import duke.ui.Ui;

/**
 * Duke is the smart assistance to help you track personal tasks.
 */
public class Duke {
	private CliController cliController = new CliController();
	private GuiController guiController = new GuiController();
	private Ui ui = new Ui();

	public void run() {
		cliController.getCliOutput();
	}

	public String getResponse(String input) {
		return guiController.getGuiOutput(input);
	}

	public Ui getUi(){
		return ui;
	}

	public static void main(String[] args) {
		new Duke().run();
	}
}
