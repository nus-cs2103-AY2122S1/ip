import java.io.*;
import java.util.*;

public class Duke {
	private TaskList taskList;
	private Storage storage;
	private UI ui;

	/* the exeption is hard-prevented for minimal cases */
	public Duke(String dirName, String fileName) throws IOException {
		this.storage = new Storage(dirName, fileName);
		this.taskList = new TaskList(storage.loadTasks());
		this.ui = new UI();
	}

	private void run() throws IOException {
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
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Duke duke = new Duke("data", "duke.txt");
		duke.run();
	}
}
