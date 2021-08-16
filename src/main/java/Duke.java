import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private final String BORDER = "\t_________________________________________________\n";
    private final String MESSAGE_GREET =  "Hi, my name is Nee ´･ᴗ･`. How can I help you?";
    private final String MESSAGE_EXIT = "Goodbye!~";
    private final String MESSAGE_DONE = "Nice! (ᵔ.ᵔ) Task done: ";
    private final String MESSAGE_LIST = "Here's your tasks!";

    private final Scanner sc;
    private final ArrayList<Task> tasks;

    /**
     * A private constructor to initialize variables.
     */
    private Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Adds a task to the list of tasks
     * @param taskName the new task to be added.
     */
    private void addTask(String taskName) {
        this.tasks.add(new Task(taskName));
        print("Nee added: " + taskName);
        waitInput();
    }

    /**
     * Prints the list of tasks.
     */
    private void printTasks() {
        System.out.printf(BORDER + "\t" + MESSAGE_LIST + "\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ".\t" + this.tasks.get(i).getTaskName());
        }
        System.out.println(BORDER);
        waitInput();
    }

    /**
     * Prints text between two horizontal lines.
     *
     * @param input the text to be printed.
     */
    public void print(String input) {
        String s = BORDER + "\t%s\n" + BORDER;
        System.out.printf(s, input.replaceAll("\n", "\n\t"));
    }

    /**
     * Marks a task as completed.
     * @param taskNum The task number.
     */
    private void finishTask(String taskNum) {
        int i = Integer.parseInt(taskNum);
        if (i < 0 || i > this.tasks.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        Task task = this.tasks.get(i - 1);
        task.markAsDone();
        print(MESSAGE_DONE + "\n" + task.getTaskName());
        waitInput();
    }

    /**
     * Prints goodbye.
     */
    private void goodbye() {
        print(MESSAGE_EXIT);
    }

    /**
     * Gets a user input and matches it to the respective cases.
     */
    private void waitInput() {
        // User input
        String input = sc.nextLine();
        // Takes in 2 commands
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(input.split("\\s", 2)));

        switch (commands.get(0).toLowerCase()) {
            case "done":
                finishTask(commands.get(1));
                break;
            case "list":
                printTasks();
                break;
            case "bye":
                goodbye();
                break;
            default:
                addTask(input);
                break;
        }
    }


    public void run() {
        // Greet user
        print(MESSAGE_GREET);
        // Get next input
        waitInput();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}