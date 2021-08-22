import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    private final String pathName = "./data/duke.txt";

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
     *
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
     * @param printMessage toggle to print message
     * @throws IOException File exists but cannot be created or opened.
     */
    private void addTask(Task task, boolean printMessage) throws IOException {
        this.tasks.add(task);
        // Show number of tasks in list
        if (printMessage) {
            String str = (tasks.size() > 1) ? " tasks in the list." : " task in the list.";
            print(MESSAGE_ADD + "\n  " + task + "\n" + "Nee has " + tasks.size() + str);
        }
        waitInput();
    }

    /**
     * Deletes task and prints updated list of tasks.
     *
     * @param taskNum Task to be deleted.
     * @throws DukeException Invalid task number, or task has invalid description.
     */
    private void deleteTask(String taskNum) throws DukeException {
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
     * @throws DukeException List has no tasks.
     * @throws IOException File exists but cannot be created or opened.
     */
    private void printTasks() throws DukeException, IOException {
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
     * @throws DukeException Invalid task number or task has invalid description.
     * @throws IOException File exists but cannot be created or opened.
     */
    private void finishTask(String taskNum) throws DukeException, IOException {
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
     * @throws DukeException Task has invalid description.
     * @throws IOException File exists but cannot be created or opened.
     */
    private void addEvent(String[] commands) throws DukeException, IOException {
        if (commands.length < 2) {
            throw new InvalidTaskException();
        }

        String[] taskCommands = commands[1].split("/at");
        if (taskCommands.length < 2) {
            throw new InvalidTaskException();
        }
        Task newTask = new Event(taskCommands[0].trim(), taskCommands[1].trim());
        addTask(newTask, true);
    }

    /**
     * Adds a deadline to the list of tasks.
     *
     * @param commands the deadline with a specific time.
     * @throws DukeException Task has invalid description.
     * @throws IOException File exists but cannot be created or opened.
     */
    private void addDeadline(String[] commands) throws DukeException, IOException {
        if (commands.length < 2) {
            throw new InvalidTaskException();
        }

        String[] taskCommands = commands[1].split("/by");
        if (taskCommands.length < 2) {
            throw new InvalidTaskException();
        }
        Task newTask = new Deadline(taskCommands[0].trim(), taskCommands[1].trim());
        addTask(newTask, true);
    }


    /**
     * Adds a todo to the list of tasks.
     *
     * @param commands the todo with a specific time.
     * @throws DukeException Task is invalid.
     * @throws IOException File exists but cannot be created or opened.
     */
    private void addTodo(String[] commands) throws DukeException, IOException {
        if (commands.length < 2) {
            throw new InvalidTaskException();
        }

        Task newTask = new Todo(commands[1].trim());
        addTask(newTask, true);
    }

    /**
     * Prints goodbye.
     */
    private void goodbye() {
        print(MESSAGE_EXIT);
    }


    /**
     * Load data into file stored locally. If file does not exist, create a new file,
     * otherwise overwrite it.
     *
     * @throws DukeException Error loading file.
     * @throws IOException File exists but cannot be created or opened.
     */
    private void loadData() throws DukeException, IOException {
        // Initialize save file and parent directory
        File file = new File(pathName);
        file.getParentFile().mkdirs();

        // If file already exists, overwrite it
        if (!file.getParentFile().mkdirs()) {
            file.delete(); // Needed for overwriting, or an error will be thrown.
            file.createNewFile();
        }

        String input;
        BufferedReader br = new BufferedReader(new FileReader(pathName));

        // Read data and add to list of tasks
        while ((input = br.readLine()) != null) {
            Task newTask;
            String description, time;

            switch (input.charAt(1)) {
                case 'T':
                    // todo
                    newTask = new Todo(input.substring(7));
                    break;
                case 'D':
                    // deadline
                    description = input.substring(7, input.indexOf(" ("));
                    time = input.substring(input.indexOf("(by: ") + 5, input.length() - 1);
                    newTask = new Deadline(description, time);
                    break;
                case 'E':
                    // event
                    description = input.substring(7, input.indexOf(" ("));
                    time = input.substring(input.indexOf("(at: ") + 2, input.length() - 1);
                    newTask = new Event(description, time);
                    break;
                default:
                    throw new DukeException("Error loading file!");
            }

            if (input.charAt(4) == 'X') {
                newTask.markAsDone();
            }

            // Add a task, don't print message
            addTask(newTask, false);
        }

        br.close();
    }

    /**
     * Saves data into file stored locally. If file does not exist, create a new file,
     * otherwise overwrite it.
     *
     * @throws IOException File exists but cannot be created or opened.
     */
    private void saveData() throws IOException {
        FileWriter fw = new FileWriter(pathName, false);
        StringBuilder output = new StringBuilder();
        tasks.forEach(task -> output.append(task.toString()).append("\n"));
        fw.write(output.toString());
        fw.close();
    }

    /**
     * Gets a user input and matches it to the respective cases.
     *
     * @throws IOException File exists but cannot be created or opened.
     */
    private void waitInput() throws IOException {
        boolean running = true;
        while (running) {
            try {
                String input = sc.nextLine();
                // Takes in 2 commands
                String[] commands = input.split("\\s", 2);
                switch (this.getCommand(commands[0])) {
                case DONE:
                    if (commands.length < 2) {
                        throw new InvalidTaskException();
                    }
                    finishTask(commands[1]);
                    saveData();
                    break;
                case LIST:
                    printTasks();
                    break;
                case EVENT:
                    addEvent(commands);
                    saveData();
                    break;
                case DEADLINE:
                    addDeadline(commands);
                    saveData();
                    break;
                case TODO:
                    addTodo(commands);
                    saveData();
                    break;
                case DELETE:
                    if (commands.length < 2) {
                        throw new InvalidTaskException();
                    }
                    deleteTask(commands[1]);
                    saveData();
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

    /**
     * Run duke.
     *
     * @throws DukeException Error loading file.
     * @throws IOException File exists but cannot be created or opened.
     */
    public void run() throws DukeException, IOException {
        // Greet user
        print(MESSAGE_GREET);

        // Load storage data
        try {
            loadData();
        } catch (IOException e) {
            print(e.getMessage());
        }

        // Get next input
        waitInput();
    }

    public static void main(String[] args) throws IOException, DukeException {
        Duke duke = new Duke();
        duke.run();
    }
}