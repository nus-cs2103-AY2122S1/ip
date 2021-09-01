package duke;

import java.util.List;
import java.util.Scanner;

public class Duke {

    protected static final String COMMAND_TODO = "todo";
    protected static final String COMMAND_EVENT = "event";
    protected static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_RESET = "reset";

    private static final List<String> GREETING = List.of("Hello! I'm Fergus' Chatbot!", "What can I do for you?");
    private static final String FAREWELL = "Bye. Hope to see you again soon!";
    private static final String ERROR_UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private Storage storage;
    private TaskList taskArray;

    /**
     * Returns an instance of the Chatbot that has attempted to read the txt file.
     * @param filePath
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        List<String> taskArrayAsString = storage.load();
        this.taskArray = new TaskList(taskArrayAsString);
    }

    /**
     * Handles the user commands using a switch.
     */
    // prettier-ignore
    public void handleCommands() {
        Scanner scanner = new Scanner(System.in);
        boolean hasCommands = true;

        while (hasCommands) {
            String commandLine = scanner.nextLine();
            String[] commands = commandLine.split(" ");
            String command = commands[0];
            switch (command) {
            case COMMAND_BYE: {
                scanner.close();
                hasCommands = false;
                break;
            }
            case COMMAND_LIST: {
                this.taskArray.handleList();
                break;
            }
            case COMMAND_EVENT: {
                this.taskArray.handleAddEvent(commands);
                break;
            }
            case COMMAND_DEADLINE: {
                this.taskArray.handleAddDeadline(commands);
                break;
            }
            case COMMAND_TODO: {
                this.taskArray.handleAddToDo(commands);
                break;
            }
            case COMMAND_DONE: {
                int taskIndex = Integer.parseInt(commands[1]);
                this.taskArray.handleDone(taskIndex);
                break;
            }
            case COMMAND_DELETE: {
                int taskIndex = Integer.parseInt(commands[1]);
                this.taskArray.handleDelete(taskIndex);
                break;
            }
            case COMMAND_SAVE: {
                this.taskArray.handleSave(this.storage);
                break;
            }
            case COMMAND_FIND: {
                this.taskArray.handleFind(commands[1]);
                break;
            }
            case COMMAND_RESET: {
                this.taskArray.handleReset();
                break;
            }
            default: {
                Printer.print(ERROR_UNKNOWN_COMMAND);
            }
            }
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        Printer.print(GREETING);
        this.handleCommands();
        Printer.print(FAREWELL);
    }

    public static void main(String[] args) {
        new Duke("FergusChatBot.txt").run();
    }
}
