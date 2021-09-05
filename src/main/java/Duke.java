import java.util.Scanner;
import java.util.ArrayList;

/**
 * A personal assistant chatbot that helps a person to keep track of various things.
 *
 * @author Samay Sagar
 * @version CS2103 AY21/22 Sem 1
 */
public class Duke {
    private static final String GREETING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String FAREWELL_MESSAGE = "See you soon! :)";
    private static final String FAREWELL_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String COMPLETE_TASK_COMMAND = "done";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String CREATE_TODO_COMMAND = "todo";
    private static final String CREATE_EVENT_COMMAND = "event";
    private static final String CREATE_DEADLINE_COMMAND = "deadline";

    private TaskList taskList;

    private Scanner sc;
    private final String DATA_STORAGE_PATH = "data/duke_storage.txt";

    /**
     * The entrypoint of the Duke chat bot.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.greet();
        duke.taskMode();
    }

    /**
     * A constructor for Duke chatbot.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.loadData();
        this.sc = new Scanner(System.in);
    }

    /**
     * Loads data that is saved in a given filename, and parses the data to load tasks.
     */
    public void loadData() {
        ArrayList<String> lines = DataHandler.readLinesFromFile(this.DATA_STORAGE_PATH);
        for (int i = 0; i < lines.size(); i++) {
            Task task = Task.parseTaskFromSavedText(lines.get(i));
            this.taskList.addTask(task);
        }
        ChatbotUI.printMessage("Loaded tasks from save file!" + this.taskList.countTasks());
    }

    /**
     * Saves Chatbot data to a given filename.
     */
    public void saveData() {
        String content = this.taskList.toSaveData();
        DataHandler.overwriteNewFile(this.DATA_STORAGE_PATH);
        DataHandler.writeToFile(content, this.DATA_STORAGE_PATH);
    }
    public void endDuke() {
        this.saveData();
        ChatbotUI.printMessage(FAREWELL_MESSAGE);
    }
    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        ChatbotUI.printMessage(GREETING_MESSAGE);
    }
    /**
     * Echoes the user's input, until the user says "bye".
     */
    public void echo() {
        String message = ChatbotUI.acceptUserInput(this.sc);
        if (message.equals("bye")) {
            ChatbotUI.printMessage(FAREWELL_MESSAGE);
        } else {
            ChatbotUI.printMessage(message);
            echo();
        }
    }
    /**
     * Gets the description supplied by the user after a command.
     * @param command a String that describes the command (e.g. "event")
     * @param message the String that is input by the user (e.g. "event Meeting /at tomorrow")
     * @return a String representing the description after the command (e.g. "Meeting /at tomorrow")
     * @throws DukeException indicates that the description is invalid
     */
    public String getStringFrom(String command, String message) throws DukeException {
        if (message.length() <= command.length()) {
            throw new DukeException(
                    String.format("The description of a %s cannot be empty.", command)
            );
        }
        String description = message.substring(command.length()).trim();
        if (description.length() == 0) {
            throw new DukeException(
                    String.format("The description of a %s cannot be empty.", command)
            );
        }
        return description;
    }
    /**
     * Parses a user-input for a number supplied after a given command.
     * @param command A String command that comes before the desired number. (e.g. "delete")
     * @param message The String that is the full input by the user. (e.g. "delete 2")
     * @return an int representing the description of the command. (e.g. "2")
     * @throws DukeException indicates that the description is invalid
     */
    public int getIntFrom(String command, String message) throws DukeException {
        return Integer.parseInt(getStringFrom(command, message));
    }
    /**
     * Handles the logic for managing a user's tasks.
     */
    public void taskMode() {
        String message = ChatbotUI.acceptUserInput(this.sc).trim();
        if (message.equals(FAREWELL_COMMAND)) {
            this.endDuke();
            return;
        }
        try {
            String output;
            if (message.equals(LIST_COMMAND)) {
                output = this.taskList.toString();
            } else if (message.startsWith(COMPLETE_TASK_COMMAND)) {
                output = this.taskList.completeTask(getIntFrom(COMPLETE_TASK_COMMAND, message));
            } else if (message.startsWith(DELETE_TASK_COMMAND)) {
                output = this.taskList.deleteTask(getIntFrom(DELETE_TASK_COMMAND, message));
            } else if (message.startsWith(CREATE_TODO_COMMAND)) {
                output = this.taskList.addNewTodo(getStringFrom(CREATE_TODO_COMMAND, message));
            } else if (message.startsWith(CREATE_EVENT_COMMAND)) {
                output = this.taskList.addNewEvent(getStringFrom(CREATE_EVENT_COMMAND, message));
            } else if (message.startsWith(CREATE_DEADLINE_COMMAND)) {
                output = this.taskList.addNewDeadline(getStringFrom(CREATE_DEADLINE_COMMAND, message));
            } else {
                throw new DukeException("I don't know what that command means." +
                        "\nPlease input a valid command.");
            }
            ChatbotUI.printMessage(output);
        } catch (DukeException e) {
            ChatbotUI.printMessage(e.getMessage());
        } finally {
            taskMode();
        }
    }
}
