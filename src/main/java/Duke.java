import java.util.*;

public class Duke {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OutputHandler oh = new OutputHandler();
		oh.add("Hello! I'm Duke");
		oh.add("What can I do for you?");
		oh.print();

		while (true) {
			String input = sc.nextLine();
			if (input.equals("bye")) break;
			oh.add(input);
			oh.print();
		}

		oh.add("Bye. Hope to see you again soon!");
		oh.print();
	}
}
