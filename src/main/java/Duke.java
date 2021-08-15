import java.util.*;

public class Duke {
	private Scanner sc = new Scanner(System.in);
	private OutputHandler oh = new OutputHandler();
	private List<Task> tasks = new ArrayList<>();

	private Task store(String line) {
		Task task = null;
		String type = line.split(" ")[0];
		if (type.equals("todo")) {
			task = new ToDo(line.substring(5));

		} else if (type.equals("deadline")) {
			int pos = line.indexOf("/by");
			task = new Deadline(line.substring(9, pos), line.substring(pos+4));

		} else if (type.equals("event")) {
			int pos = line.indexOf("/at");
			task = new Event(line.substring(6, pos), line.substring(pos+4));

		} /* else error */

		tasks.add(task);
		return task;
	}

	private void display() {
		oh.add("Here are the tasks in your list:");
		int counter = 1;
		for (Task task : tasks) {
			oh.add("" + counter + "." + task);
			counter++;
		}
		oh.print();
	}

	private void markDone(int index) {
		tasks.get(index).markDone();
		oh.add("Nice! I've marked this task as done:");
		oh.add("  " + tasks.get(index));
		oh.print();
	}

	private void run() {
		oh.add("Hello! I'm Duke");
		oh.add("What can I do for you?");
		oh.print();

		/**
		 * There are some serious error catching to do for most 
		 * of these with regards to input format validation.
		 */
		while (true) {
			String input = sc.nextLine();
			if (input.equals("list")) {
				display();

			} else if (input.equals("bye")) {
				break;

			} else if (input.split(" ")[0].equals("done")) {
				markDone(Integer.parseInt(input.split(" ")[1]) - 1);

			} else {
				Task task = store(input);
				oh.add("Got it. I've added this task:");
				oh.add("  " + task);
				oh.add(String.format("Now you have %d task(s) in the list.", tasks.size()));
				oh.print();

			}
		}

		oh.add("Bye. Hope to see you again soon!");
		oh.print();
	}

	public static void main(String[] args) {
		Duke duke = new Duke();
		duke.run();
	}
}
