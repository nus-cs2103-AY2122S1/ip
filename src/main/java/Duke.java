import java.util.Scanner;
import java.nio.file.Paths;

public class Duke {

	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	Duke() {
		this.storage = new Storage(Paths.get("..", "..", "..", "data"),
		        Paths.get("..", "..", "..", "data", "tasks.txt"));
		this.tasks = new TaskList(this.storage.getTasks());
		this.ui = new Ui();
	}

	public static void main(String[] args) {
		Duke duke = new Duke();
		this.ui.greet();
		duke.run();
	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			System.out.println(Ui.DIVIDER_LINE);
			input = input.strip();
			String[] splitInput = input.split(" ", 2);
			try {

				if (input.equals("bye") || input.equals("exit")) {
					this.ui.exit();
					break;
				} else if (input.equals("list")) {
					this.ui.list(this.tasks);
				} else if (splitInput[0].equals("done") || splitInput[0].equals("delete")) {
					this.tasks.indexCommand(splitInput);
				} else if (splitInput[0].equals("todo") || splitInput[0].equals("deadline") || splitInput[0].equals("event")) {
					this.tasks.addTask(splitInput);
				} else {
					this.ui.unknownCommand();
				}
			} catch (DukeTaskDetailsException | DukeIndexInputException e) {
				System.out.println("\t" + e.toString());
			}
			System.out.println(Ui.DIVIDER_LINE);
			this.storage.saveTasks(this.tasks.getTasks());
		}
		sc.close();
	}

}
