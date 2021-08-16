import java.util.*;

public class Duke {
	private Scanner sc = new Scanner(System.in);
	private OutputHandler oh = new OutputHandler();
	private List<Task> tasks = new ArrayList<>();

	private void printStartUpMessage() {
		oh.add("Hello! I'm Duke");
		oh.add("What can I do for you?");
		oh.print();
	}

	private void printClosingMessage() {
		oh.add("Bye. Hope to see you again soon!");
		oh.print();
	}

	private void store(String line) {
		Task task = null;
		String type = line.split(" ")[0];
		if (type.equals("todo")) {
			task = ToDo.init(line);

		} else if (type.equals("deadline")) {
			task = Deadline.init(line);

		} else if (type.equals("event")) {
			task = Event.init(line);

		} else {
			throw new UnknownCommandException(line);
		}

		tasks.add(task);
		oh.add("Got it. I've added this task:");
		oh.add("  " + task);
		oh.add(String.format("Now you have %d task(s) in the list.", tasks.size()));
		oh.print();
	}

	private void delete(int index) {
		oh.add("Noted. I've removed this task:");
		oh.add("  " + tasks.get(index));
		tasks.remove(index);
		oh.add(String.format("Now you have %d task(s) in the list.", tasks.size()));
		oh.print();
	}

	private void display() {
		if (tasks.isEmpty()) {
			oh.add("Your list of tasks is empty!");
		} else {
			oh.add("Here are the tasks in your list:");
			int counter = 1;
			for (Task task : tasks) {
				oh.add("" + counter + "." + task);
				counter++;
			}
		}
		oh.print();
	}

	private void markDone(int index) {
		tasks.get(index).markDone();
		oh.add("Nice! I've marked this task as done:");
		oh.add("  " + tasks.get(index));
		oh.print();
	}

	private void printErrorMessage(DukeException ex) {
		oh.add(ex.getMessage());
		for (String line : ex.getHelpMessages()) {
			oh.add(line);
		}
		oh.print();
	}

	private void run() {
		printStartUpMessage();

		while (true) {
			try {
				String input = sc.nextLine();
				if (input.equals("list")) {
					display();

				} else if (input.equals("bye")) {
					break;

				} else if (input.split(" ")[0].equals("done")) {
					/* todo: catch format and ioob exceptions */
					markDone(Integer.parseInt(input.split(" ")[1]) - 1);

				} else if (input.split(" ")[0].equals("delete")) {
					/* todo: catch format and ioob exceptions */
					delete(Integer.parseInt(input.split(" ")[1]) - 1);

				} else {
					store(input);

				}
			} catch (DukeException ex) {
				printErrorMessage(ex);
			}
		}

		printClosingMessage();
	}

	public static void main(String[] args) {
		Duke duke = new Duke();
		duke.run();
	}
}
