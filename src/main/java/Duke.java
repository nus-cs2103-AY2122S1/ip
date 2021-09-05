import java.util.Scanner;
import java.util.ArrayList;

/**
 * A personal assistant chatbot that helps a person to keep track of various things.
 *
 * @author Samay Sagar
 * @version CS2103 AY21/22 Sem 1
 */
public class Duke implements ChatbotUI, Parser {
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
    private Storage storage;

    private Scanner sc;

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

        Duke duke = new Duke("data/duke_storage.txt");

        duke.greet();
        duke.taskMode();
    }

    /**
     * A constructor for Duke chatbot.
     */
    public Duke(String dataStorage) {
        this.taskList = new TaskList();
        this.storage = new Storage(dataStorage);
        this.loadData();
        this.sc = new Scanner(System.in);
    }

    /**
     * Loads data that is saved in a given filename, and parses the data to load tasks.
     */
    public void loadData() {
        ArrayList<String> lines = this.storage.readLines();
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
        this.storage.overwriteNewFile();
        this.storage.writeToFile(content);
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
     * Handles the logic for managing a user's tasks.
     */
    public void taskMode() {
        String msg = ChatbotUI.acceptUserInput(this.sc).trim();
        if (msg.equals(FAREWELL_COMMAND)) {
            this.endDuke();
            return;
        }
        try {
            String output;
            TaskList tasks = this.taskList;
            if (msg.equals(LIST_COMMAND)) {
                output = tasks.toString();
            } else if (msg.startsWith(COMPLETE_TASK_COMMAND)) {
                output = tasks.completeTask(Parser.getIntFrom(COMPLETE_TASK_COMMAND, msg));
            } else if (msg.startsWith(DELETE_TASK_COMMAND)) {
                output = tasks.deleteTask(Parser.getIntFrom(DELETE_TASK_COMMAND, msg));
            } else if (msg.startsWith(CREATE_TODO_COMMAND)) {
                output = tasks.addNewTodo(Parser.getStringFrom(CREATE_TODO_COMMAND, msg));
            } else if (msg.startsWith(CREATE_EVENT_COMMAND)) {
                output = tasks.addNewEvent(Parser.getStringFrom(CREATE_EVENT_COMMAND, msg));
            } else if (msg.startsWith(CREATE_DEADLINE_COMMAND)) {
                output = tasks.addNewDeadline(Parser.getStringFrom(CREATE_DEADLINE_COMMAND, msg));
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
