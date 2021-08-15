import java.util.*;

public class Duke {
	private Scanner sc = new Scanner(System.in);
	private OutputHandler oh = new OutputHandler();
	private List<String> list = new ArrayList<>();

	private void store(String line) {
		list.add(line);
	}

	private void display() {
		int counter = 1;
		for (String item : list) {
			oh.add("" + counter + ". " + item);
			counter++;
		}
		oh.print();
	}

	private void run() {
		oh.add("Hello! I'm Duke");
		oh.add("What can I do for you?");
		oh.print();

		while (true) {
			String input = sc.nextLine();
			if (input.equals("list")) {
				display();

			} else if (input.equals("bye")) {
				break;

			} else {
				store(input);
				oh.add("added: " + input);
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
