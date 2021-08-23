import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Duke {

	private ArrayList<Task> tasks;

	Duke() {
		this.tasks = new ArrayList<>();
		this.getTasks();
	}

	public static void main(String[] args) {
		String logo = " ____        _\n"
			+ "|  _ \\ _   _| | _____\n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
		Duke duke = new Duke();
		duke.run();
	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			System.out.println("\t____________________________");
			input = input.strip();
			String[] splitInput = input.split(" ", 2);
			try {

				if (input.equals("bye") || input.equals("exit")) {
					this.exit();
					break;
				} else if (input.equals("list")) {
					this.list();
				} else if (splitInput[0].equals("done") || splitInput[0].equals("delete")) {
					this.indexCommand(splitInput);
				} else if (splitInput[0].equals("todo") || splitInput[0].equals("deadline") || splitInput[0].equals("event")) {
					this.addTask(splitInput);
				} else {
					System.out.println("\tSorry, I do not know this command!");
				}
			} catch (DukeTaskDetailsException | DukeIndexInputException e) {
				System.out.println("\t" + e.toString());
			}
			System.out.println("\t____________________________");
		}
		sc.close();
		this.saveTasks();
	}

	private void getTasks() {
		try {
			Path path = Paths.get("..", "..", "..", "data", "tasks.txt");
			File tasks = new File(path.toString());
			Scanner sc = new Scanner(tasks);
			while (sc.hasNextLine()) {
				String[] taskArray = sc.nextLine().split(" : ");
				Task task;
				if (taskArray[0].equals("T")) {
					task = new Todo(taskArray[2], taskArray[1].equals("1"));
				} else  if (taskArray[0].equals("D")) {
					task = new Deadline(taskArray[2], taskArray[1].equals("1"), taskArray[3]);
				} else {
					task = new Event(taskArray[2], taskArray[1].equals("1"), taskArray[3]);
				}
				this.tasks.add(task);
			}
			sc.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	private void saveTasks() {
		try {
			Path path = Paths.get("..", "..", "..", "data");
			File taskDir = new File(path.toString());
			if (!taskDir.exists()) {
				taskDir.mkdir();
			}
			path = Paths.get("..", "..", "..", "data", "tasks.txt");
			File tasks = new File(path.toString());
			if (tasks.createNewFile()) {
				System.out.println("File created");
			} else {
				System.out.println("File updated");
			}
			FileWriter writer = new FileWriter(path.toString());
			for (Task task : this.tasks) {
				writer.write(task.saveString() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	private void addTask(String[] taskArray) throws DukeTaskDetailsException {
		Task task;
		if (taskArray.length < 2) {
			throw new DukeTaskDetailsException("Please provide task details");
		}
		if (taskArray[0].equals("todo")) {
			task = new Todo(taskArray[1]);
		} else if (taskArray[0].equals("deadline")) {
			String[] deadlineDetails = taskArray[1].split("/by");
			if (deadlineDetails.length != 2) {
				throw new DukeTaskDetailsException("Please provide both task title and deadline, separated by \"/by\"");
			}
			task = new Deadline(deadlineDetails[0], deadlineDetails[1]);
		} else {
			String[] eventDetails = taskArray[1].split("/at");
			if (eventDetails.length != 2) {
				throw new DukeTaskDetailsException("Please provide both event title and date, separated by \"/at\"");
			}
			task = new Event(eventDetails[0], eventDetails[1]);
		}
		this.tasks.add(task);
		System.out.println("\tGot it. I\'ve added this task:");
		System.out.println("\t  " + task.toString());
		System.out.println("\tNow you have " + this.tasks.size() +
						   " tasks in the list.");
	}

	private void list() {
		for (int i = 0; i < this.tasks.size(); i++) {
			System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i).toString());
		}
	}

	private void exit() {
		System.out.println("\tBye. Hope to see you again soon!");
		System.out.println("\t____________________________");
	}

	private void indexCommand(String[] taskArray) throws DukeIndexInputException {
		if (taskArray.length < 2) {
			throw new DukeIndexInputException("Please enter index of the task");
		}
		try {
			int index = Integer.parseInt(taskArray[1]);
			if (taskArray[0].equals("done")) {

				this.tasks.get(index - 1).markAsDone();
				System.out.println("\tNice! I\'ve marked this task as done:");
				System.out.println(" \t" + this.tasks.get(index - 1).toString());
			} else {
				Task removed = this.tasks.remove(index - 1);
				System.out.println("\tNoted. I've removed this task: ");
				System.out.println("\t" + removed.toString());
				System.out.println("\tNow you have " + this.tasks.size() + " tasks in the list");
			}
		} catch (NumberFormatException e){
			throw new DukeIndexInputException("Please enter index of the task");
		} catch (IndexOutOfBoundsException e) {
			throw new DukeIndexInputException("Sorry! Unable to find task number " + taskArray[1]);
		}
	}
}
