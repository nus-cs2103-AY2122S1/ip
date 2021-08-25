package duke;

/**
 * Duke is a simple bot that allows users to keep track of different types of tasks.
 */
public class Duke {
	private final Storage storage;
	private final TaskList tasks;
	private final UI ui;

	public Duke(String filePath) {
		this.storage = new Storage(filePath);
		this.tasks = this.storage.loadTasks();
		this.ui = new UI();
	}

	/**
	 * Runs Duke and parses input using Parser class.
	 */
	public void run() {
		UI.greet();
		Parser parser = new Parser(this.tasks);
		while (!parser.isExit()) {
			String userInput = ui.readInput();
			parser.commands(userInput);
			storage.saveData(tasks.encodeTasks());
		}
	}

	public static void main(String[] args) {
		new Duke("data/Duke.txt").run();

	}
}


