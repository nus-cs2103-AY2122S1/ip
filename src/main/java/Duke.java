import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String BORDER = "\t_________________________________________________\n";
    private final String MESSAGE_GREET = "Hi, my name is Nee ´･ᴗ･`. How can I help you?";
    private final String MESSAGE_EXIT = "Goodbye!~";
    private final String MESSAGE_DONE = "Nice! (ᵔ.ᵔ) Task done:";
    private final String MESSAGE_LIST = "Here's your tasks!";
    private final String MESSAGE_ADD = "Nee added this task:";
    private final String MESSAGE_DELETE = "Nee has deleted this task:";

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
     * Gets commands from enum.
     * @param input The user input.
     * @return A command corresponding to the enum.
     * @throws UnknownCommandException Invalid command.
     */
    private Commands getCommand(String input) throws UnknownCommandException {
        try {
            return Commands.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new UnknownCommandException();
        }
    }

    /**
     * Adds a task to the list of tasks
     *
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
     * Deletes task and prints updated list of tasks.
     *
     * @param taskNum Task to be deleted.
     * @throws TaskNotFoundException Invalid task number.
     * @throws InvalidTaskException  Task has invalid description.
     */
    private void deleteTask(String taskNum) throws TaskNotFoundException, InvalidTaskException {
        int i = Integer.parseInt(taskNum);
        if (i <= 0 || i > this.tasks.size()) {
            throw new TaskNotFoundException();
        }
        Task task = this.tasks.get(i - 1);
        this.tasks.remove(i - 1);
        // Show number of tasks in list
        String str = (tasks.size() == 1) ? " task in the list." : " tasks in the list.";
        print(MESSAGE_DELETE + "\n  " + task + "\n" + "Nee has " + tasks.size() + str);
    }

    /**
     * Prints the list of tasks.
     *
     * @throws EmptyListException List has no tasks.
     */
    private void printTasks() throws EmptyListException {
        if (tasks.size() == 0) {
            throw new EmptyListException();
        }
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
     *
     * @param taskNum The task number.
     * @throws TaskNotFoundException Invalid task number.
     * @throws InvalidTaskException  Task has invalid description.
     */
    private void finishTask(String taskNum) throws TaskNotFoundException, InvalidTaskException {
        int i = Integer.parseInt(taskNum);
        if (i <= 0 || i > this.tasks.size()) {
            throw new TaskNotFoundException();
        }
        Task task = this.tasks.get(i - 1);
        task.markAsDone();
        print(MESSAGE_DONE + "\n" + "  " + task);
        waitInput();
    }

    /**
     * Adds an event to the list of tasks.
     *
     * @param commands the event with a specific time.
     * @throws InvalidTaskException Task has invalid description.
     */
    private void addEvent(String[] commands) throws InvalidTaskException {
        if (commands.length < 2) {
            throw new InvalidTaskException();
        }

        String[] taskCommands = commands[1].split("/at");
        if (taskCommands.length < 2) {
            throw new InvalidTaskException();
        }
        Task newTask = new Event(taskCommands[0].trim(), taskCommands[1].trim());
        addTask(newTask);
    }

    /**
     * Adds a deadline to the list of tasks.
     *
     * @param commands the deadline with a specific time.
     * @throws InvalidTaskException Task has invalid description.
     */
    private void addDeadline(String[] commands) throws InvalidTaskException {
        if (commands.length < 2) {
            throw new InvalidTaskException();
        }

        String[] taskCommands = commands[1].split("/by");
        if (taskCommands.length < 2) {
            throw new InvalidTaskException();
        }
        Task newTask = new Deadline(taskCommands[0].trim(), taskCommands[1].trim());
        addTask(newTask);
    }

    /**
     * Adds a todo to the list of tasks.
     *
     * @param commands the todo with a specific time.
     */
    private void addTodo(String[] commands) throws InvalidTaskException {
        if (commands.length < 2) {
            throw new InvalidTaskException();
        }

        Task newTask = new Todo(commands[1].trim());
        addTask(newTask);
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
        boolean running = true;
        while (running) {
            try {
                // User input
                String input = sc.nextLine();
                // Takes in 2 commands
                String[] commands = input.split("\\s", 2);
                switch (this.getCommand(commands[0])) {
                    case DONE:
                        if (commands.length < 2) {
                            throw new InvalidTaskException();
                        }
                        finishTask(commands[1]);
                        break;
                    case LIST:
                        printTasks();
                        break;
                    case EVENT:
                        addEvent(commands);
                        break;
                    case DEADLINE:
                        addDeadline(commands);
                        break;
                    case TODO:
                        addTodo(commands);
                        break;
                    case DELETE:
                        if (commands.length < 2) {
                            throw new InvalidTaskException();
                        }
                        deleteTask(commands[1]);
                        break;
                    case BYE:
                        goodbye();
                        // Close scanner
                        running = false;
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                print(e.getMessage());
            }
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