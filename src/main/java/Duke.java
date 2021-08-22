import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String LINE = "-----------------------------------------------------------------------\n";
    private ArrayList<Task> tasks = new ArrayList<>();
    private final String DIRECTORY = "data";
    private final String FILENAME = "duke.txt";

    /**
     * Starts the Duke application.
     */
    private void startApp() {
        // Print welcome text
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE + logo + "\nHello! I'm Duke :)\nWhat can I do for you?\n" + LINE);

        this.loadFromFile();

        // Handle user input
        Scanner sc = new Scanner(System.in);
        while (true) {
            this.handleCommand(sc.nextLine());
        }
    }

    /**
     * Handles input commands to the Duke application.
     *
     * @param command The input command from the user.
     */
    private void handleCommand(String command) {
        try {
            if (command.equals("")) {
                // If user inputs empty string, do nothing
                return;
            } else if (command.equals("bye")) {
                this.handleBye();
            } else if (command.equals("list")) {
                this.handleList();
            } else if (command.matches("^done -?[0-9]+$")) {
                this.handleDone(command);
            } else if (command.matches("^todo( .*)?")) {
                this.handleTodo(command);
            } else if (command.matches("^deadline( .*)?")) {
                this.handleDeadline(command);
            } else if (command.matches("^event( .*)?")) {
                this.handleEvent(command);
            } else if (command.matches("^delete -?[0-9]+$")) {
                this.handleDelete(command);
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            System.out.println(LINE + e.getMessage() + "\n" + LINE);
        }
    }

    /**
     * Handles the "bye" command.
     */
    private void handleBye() {
        System.out.println(LINE + "Bye. Hope to see you again soon!\n" + LINE);
        System.exit(0);
    }

    /**
     * Handles the "list" command.
     */
    private void handleList() {
        System.out.println(LINE + "Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, this.tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Handles the "done {taskIndex}" command.
     *
     * @param input The input command from the user.
     * @throws InvalidTaskIndexException
     */
    private void handleDone(String input) throws InvalidTaskIndexException {
        String[] splitInput = input.split(" ");
        int taskIdx = -1;

        // We should not have an error here as we have performed regex string matching above
        // But just to be safe
        try {
            taskIdx = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            System.out.println(LINE + "Invalid input!\n" + LINE);
        }

        // Handle invalid index
        if (taskIdx >= 1 && taskIdx <= tasks.size()) {
            Task t = tasks.get(taskIdx - 1);
            t.markAsDone();
            System.out.println(LINE + String.format("Nice! I've marked this task as done:\n  %s\n", t) + LINE);
            this.saveToFile();
        } else {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Handles the "todo {description}" command.
     *
     * @param command The input command from the user.
     * @throws InvalidFormatException
     */
    private void handleTodo(String command) throws InvalidFormatException, EmptyTodoDescriptionException {
        this.validateRegex(command, "^todo .+", "todo {description}");
        String description = command.substring(4).trim();
        if (description.equals("")) {
            throw new EmptyTodoDescriptionException();
        }
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        this.saveToFile();
        System.out.println(this.formatAddTaskString(newTodo));
    }

    /**
     * Handles the "deadline {description} /by {time}" command.
     *
     * @param command The input command from the user.
     * @throws InvalidFormatException
     */
    private void handleDeadline(String command) throws InvalidFormatException {
        this.validateRegex(command, "^deadline .+ /by .+", "deadline {description} /by {time}");
        String[] info = command.substring(8).split("/by");
        Deadline newDeadline = new Deadline(info[0].trim(), info[1].trim());
        tasks.add(newDeadline);
        this.saveToFile();
        System.out.println(formatAddTaskString(newDeadline));
    }

    /**
     * Handles the "event {description} /at {time}" command.
     *
     * @param command The input command from the user.
     * @throws InvalidFormatException
     */
    private void handleEvent(String command) throws InvalidFormatException {
        this.validateRegex(command, "^event .+ /at .+", "event {description} /at {time}");
        String[] info = command.substring(5).split("/at");
        Event newEvent = new Event(info[0].trim(), info[1].trim());
        tasks.add(newEvent);
        this.saveToFile();
        System.out.println(formatAddTaskString(newEvent));
    }

    /**
     * Handles the "delete {taskIndex}" command.
     *
     * @param input
     * @throws InvalidTaskIndexException
     */
    private void handleDelete(String input) throws InvalidTaskIndexException {
        String[] splitInput = input.split(" ");
        int taskIdx = -1;

        // We should not have an error here as we have performed regex string matching above
        // But just to be safe
        try {
            taskIdx = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            System.out.println(LINE + "Invalid input!\n" + LINE);
        }

        // Handle invalid index
        if (taskIdx >= 1 && taskIdx <= tasks.size()) {
            Task t = tasks.get(taskIdx - 1);
            tasks.remove(taskIdx - 1);
            this.saveToFile();
            System.out.println(LINE +
                    String.format("Noted. I've removed this task:\n  %s\n%s\n", t, formatNumTaskString()) + LINE);
        } else {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Formats the inputted task as a string to be displayed back to the user.
     *
     * @param task The Task created.
     * @return The string to be displayed to the user during addition of a new task.
     */
    private String formatAddTaskString(Task task) {
        return LINE +
                String.format("Got it. I've added this task:\n  %s\n%s\n",
                        task, formatNumTaskString()) + LINE;
    }

    /**
     * Formats the number of tasks as a string that is to be displayed to the user.
     *
     * @return The formatted string containing number of tasks.
     */
    private String formatNumTaskString() {
        return String.format("Now you have %d task%s in the list.",
                this.tasks.size(), this.tasks.size() == 1 ? "" : "s");
    }

    /**
     * Validates the given string against the provided regex. Also validates that the given string
     * does not contain commas.
     *
     * @param str         The given string.
     * @param regex       The given regex.
     * @param validFormat The correct input format to be displayed to the user when this regex fails.
     * @throws InvalidFormatException
     */
    private void validateRegex(String str, String regex, String validFormat) throws InvalidFormatException {
        if (str.contains(",")) {
            throw new InvalidFormatException("Please do not include commas!");
        }
        if (!str.matches(regex)) {
            throw new InvalidFormatException(validFormat);
        }
    }

    /**
     * Saves the current tasks list to file.
     */
    private void saveToFile() {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            FileWriter fw = new FileWriter(DIRECTORY + "/" + FILENAME);
            StringBuilder str = new StringBuilder();
            this.tasks.forEach(e -> str.append(e.toSaveFormat() + "\n"));
            fw.write(str.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving.");
        }
    }

    /**
     * Loads the tasks list from file.
     */
    private void loadFromFile() {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        File file = new File(DIRECTORY + "/" + FILENAME);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                Task newTask;
                String[] values = line.split(",", -1);

                // Checks to ensure correct line format
                if (!((line.matches("T,[0,1],.*") && values.length == 3) ||
                        (line.matches("[D,E],[0,1],.*") && values.length == 4))) {
                    throw new InvalidFileFormatException();
                }

                switch (values[0]) {
                    case "T":
                        newTask = new Todo(values[2], values[1].equals("1"));
                        break;
                    case "D":
                        newTask = new Deadline(values[2], values[3], values[1].equals("1"));
                        break;
                    case "E":
                        newTask = new Event(values[2], values[3], values[1].equals("1"));
                        break;
                    default:
                        // Should not reach this case due to regex check above
                        throw new InvalidFileFormatException();
                }
                loadedTasks.add(newTask);
            }
            this.tasks = loadedTasks;
        } catch (IOException e) {
            System.out.println("Seems like you do not have a previous save file. " +
                    "I will create one for you once you input a task!\n");
        } catch (InvalidFileFormatException e) {
            System.out.println(LINE + e.getMessage() + "\n" + LINE);
        }
    }

    public static void main(String[] args) {
        new Duke().startApp();
    }
}
