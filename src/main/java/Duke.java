import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String BORDER = "\t_________________________________________________\n";
    private final String MESSAGE_GREET =  "Hi, my name is Nee ´･ᴗ･`. How can I help you?";
    private final String MESSAGE_EXIT = "Goodbye!~";

    private ArrayList<String> tasks;

    /**
     * A private constructor to initialize the Arraylist to the given one.
     */
    private Duke() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Adds a task to the list of tasks
     * @param taskName the new task to be added.
     */
    private void addTask(String taskName) {
        this.tasks.add(taskName);
        print("added: " + taskName);
    }

    /**
     * Prints the list of tasks.
     */
    private void printTasks() {
        System.out.printf(BORDER);
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ".\t" + this.tasks.get(i));
        }
        System.out.println(BORDER);
    }

    /**
     * Prints user input sandwiched between two horizontal lines.
     *
     * @param input the user input.
     */
    public void print(String input) {
        String s = BORDER + "\t%s\n" + BORDER;
        System.out.printf(s, input.replaceAll("\n", "\n\t"));
    }

    public void run() {
        // Greet user
        print(MESSAGE_GREET);

        // User input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            switch (input.toLowerCase()) {
                case "list":
                    printTasks();
                    break;
                default:
                    addTask(input);
                    break;

            }
            input = sc.nextLine();

        }
        sc.close();

        // Goodbye message
        print(MESSAGE_EXIT);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
