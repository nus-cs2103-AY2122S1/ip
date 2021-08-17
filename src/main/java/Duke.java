import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a Duke instance.
 * User inputs are added to the task list, and the "list" command lists all the user's tasks.
 * Users can exit Duke by typing the "bye" command.
 */
public class Duke {
    private final List<Task> list = new ArrayList<>();
    private static final String WELCOME = "Hello, I'm Duke\nWhat can I do for you?";
    private static final String BYE = "Bye. Hope to see you again soon!";

    /**
     * Prints welcome message, then accepts user input until exit command is entered.
     */
    public void start() {
        formatAndPrint(WELCOME);
        Scanner sc = new Scanner(System.in);
        boolean continueListening = true;
        while(continueListening) {
            String input = sc.nextLine();
            continueListening = listen(input);
        }
    }

    /**
     * Accepts user input and runs the appropriate function.
     * @param input String containing user input.
     * @return Boolean that controls whether to continue accepting user input.
     */
    public boolean listen(String input) {
        if (input.equals("bye")) {
            formatAndPrint(BYE);
            return false;
        } else if (input.equals("list")) {
            displayList();
        } else if (input.startsWith("done")) {
            // Split string into "done" and the value inputted by user.
            String[] strArray = input.split(" ", 2);
            // Retrieve value inputted by user and subtract 1 to get the index in the array.
            int index = Integer.parseInt(strArray[1]) - 1;
            markAsDone(index);
        } else {
            addToList(input);
        }
        return true;
    }

    /**
     * Adds the user's input to the list and prints user's input.
     * @param input String that user inputs.
     */
    public void addToList(String input) {
        Task task = new Task(input);
        String[] splitType = input.split(" ", 2);
        String type = splitType[0];
        if (type.equals("todo")) {
            task = new Todo(splitType[1]);
        } else if (type.equals("deadline")) {
            String[] splitMessage = splitType[1].split(" /by ", 2);
            task = new Deadline(splitMessage[0], splitMessage[1]);
        } else if (type.equals("event")) {
            String[] splitMessage = splitType[1].split(" /at ", 2);
            task = new Event(splitMessage[0], splitMessage[1]);
        }
        this.list.add(task);
        formatAndPrint(String.format("Got it. I've added this task:\n%s\nNow you have %d %s in your list.",
                task,
                list.size(),
                list.size() == 1 ? "task" : "tasks"));
    }

    /**
     * Formats the list of tasks for displaying when the user inputs "list".
     */
    public void displayList() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < this.list.size(); i++) {
            output.append(String.format("%d. %s", i + 1, this.list.get(i)));
            // Append new line for all lines except last line.
            if (i != this.list.size() - 1) {
                output.append("\n");
            }
        }
        formatAndPrint(output.toString());
    }

    /**
     * Marks a given task as done.
     * @param itemNo Index of the task in the ArrayList.
     */
    public void markAsDone(int itemNo) {
        Task task = list.get(itemNo);
        task.toggleComplete();
        formatAndPrint("Nice! I've marked this task as done: \n" + task.toString());
    }

    /**
     * Helper function to format output between 2 lines.
     * @param s String to be outputted.
     */
    private static void formatAndPrint(String s) {
        String line = "____________________________________________________________\n";
        System.out.printf("%s%s\n%s", line, s, line);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
