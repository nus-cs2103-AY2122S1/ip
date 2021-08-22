package duke;

import java.io.*;
import duke.util.UI;
import duke.util.Parser;
import duke.util.Storage;
import duke.task.TaskList;
import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
	private TaskList taskList;
	private Storage storage;
	private UI ui;

	/* the exeption is hard-prevented for minimal cases */
	public Duke(String dirName, String fileName) {
		this.storage = new Storage(dirName, fileName);
		this.taskList = new TaskList(storage.loadTasks());
		this.ui = new UI();
	}

	private void run() {
		ui.printStartUpMessage();
		boolean isExit = false;

		while (!isExit) {
			try {
				String input = ui.readLine();
				Command command = Parser.parse(input);
				command.execute(taskList, ui, storage);
				isExit = command.isExit();
			} catch (DukeException ex) {
				ui.printErrorMessage(ex);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Duke duke = new Duke("data", "duke.txt");
		duke.run();
	}
}
