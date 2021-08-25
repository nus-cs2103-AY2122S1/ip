public class Ui {

	public static String DIVIDER_LINE = "\t____________________________";

	Ui() {}

	public void greet() {
		String logo = " ____        _\n"
			+ "|  _ \\ _   _| | _____\n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
	}

	public void exit() {
		System.out.println("\tBye. Hope to see you again soon!");
		System.out.println(DIVIDER_LINE);
	}

	public void unknownCommand() {
		System.out.println("\tSorry, I do not know this command!");
	}

	public void list(TaskList tasks) {
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
		}
	}
}
