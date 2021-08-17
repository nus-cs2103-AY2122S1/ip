import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String BORDER = "\t_________________________________________________\n";
    private final String MESSAGE_GREET =  "Hi, my name is Nee ´･ᴗ･`. How can I help you?";
    private final String MESSAGE_EXIT = "Goodbye!~";
    private final String MESSAGE_DONE = "Nice! (ᵔ.ᵔ) Task done:";
    private final String MESSAGE_LIST = "Here's your tasks!";
    private final String MESSAGE_ADD = "Nee added this task:";

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
     * @param task the new task to be added.
     */
    private void addTask(Task task) {
        this.tasks.add(task);
        // Show number of tasks in list
        String str = (tasks.size() > 1) ? " tasks in the list." : " task in the list.";
        print(MESSAGE_ADD + "\n  " + task + "\n" + "Nee has " + tasks.size() + str);
        waitInput();
    }

    /**
     * Prints the list of tasks.
     */
    private void printTasks() {
        System.out.printf(BORDER + "\t" + MESSAGE_LIST + "\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ".\t" + this.tasks.get(i));
        }
        System.out.printf(BORDER);
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
        print(MESSAGE_DONE + "\n" + "  " + task);
        waitInput();
    }

    /**
     * Adds an event to the list of tasks.
     * @param commands the event with a specific time.
     */
    private void addEvent(String[] commands) {
        String[] taskCommands = commands[1].split("/at");
        Task newTask = new Event(taskCommands[0].trim(), taskCommands[1].trim());
        addTask(newTask);

    }

    /**
     * Adds a deadline to the list of tasks.
     * @param commands the deadline with a specific time.
     */
    private void addDeadline(String[] commands) {
        String[] taskCommands = commands[1].split("/by");
        Task newTask = new Deadline(taskCommands[0].trim(), taskCommands[1].trim());
        addTask(newTask);
    }

    /**
     * Adds a todo to the list of tasks.
     * @param commands the todo with a specific time.
     */
    private void addTodo(String[] commands) {
        Task newTask = new Todo(commands[1].trim());
        addTask(newTask);
    }

    /**
     * Echoes the user's input.
     * @param input the user input.
     */
    private void echo(String input) {
        print("Nee said: " + input);
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
        String[] commands = input.split("\\s", 2);

        switch (commands[0].toLowerCase()) {
            case "done":
                finishTask(commands[1]);
                break;
            case "list":
                printTasks();
                break;
            case "event":
                addEvent(commands);
                break;
            case "deadline":
                addDeadline(commands);
                break;
            case "todo":
                addTodo(commands);
                break;
            case "bye":
                goodbye();
                break;
            default:
                echo(input);
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