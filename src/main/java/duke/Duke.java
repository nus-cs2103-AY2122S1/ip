package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.exception.DukeException;

//TODO: Refactor the commands into their own separate classes.
public class Duke {
    
    protected static final String COMMAND_TODO = "todo";
    protected static final String COMMAND_EVENT = "event";
    protected static final String COMMAND_DEADLINE = "deadline";

    private static final String STORAGE_DIRECTORY = "FergusChatBot.txt";

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_RESET = "reset";

    private static final String READ_SUCCESS = "A saved file has been found! It will now be loaded :)";
    private static final String READ_FAILURE = "No saved file has been found :(";

    private static final List<String> GREETING = new ArrayList<>(Arrays.asList("Hello! I'm Fergus' Chatbot!", "What can I do for you?"));
    private static final String FAREWELL = "Bye. Hope to see you again soon!";
    private static final String ERROR_UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private Storage storage;
    private TaskList taskArray;

    /**
     * Returns an instance of the Chatbot that has attempted to read the txt file.
     */
    public Duke() {
        this.storage = new Storage(STORAGE_DIRECTORY);
        this.taskArray = null;
    }

    /**
     * Handles the user commands using a switch.
     */
    // prettier-ignore
    public Response handleCommands(String input) {
        String[] commands = input.split(" ");
        String command = commands[0];
        assert !command.isEmpty() : "The given input command is empty or invalid";
        String message = "";
        boolean isBye = false;
        try {
            switch (command) {
            case COMMAND_BYE: {
                message = Formatter.getResponseString(FAREWELL);
                isBye = true;
                break;
            }
            case COMMAND_LIST: {
                message = this.taskArray.handleList();
                break;
            }
            case COMMAND_EVENT: {
                message = this.taskArray.handleAddEvent(commands);
                break;
            }
            case COMMAND_DEADLINE: {
                message = this.taskArray.handleAddDeadline(commands);
                break;
            }
            case COMMAND_TODO: {
                message = this.taskArray.handleAddToDo(commands);
                break;
            }
            case COMMAND_DONE: {
                assert Arrays.asList(commands).size() >= 2 : "Invalid format of index for command done given";
                int taskIndex = Integer.parseInt(commands[1]);
                message = this.taskArray.handleDone(taskIndex);
                break;
            }
            case COMMAND_DELETE: {
                assert Arrays.asList(commands).size() >= 2 : "Invalid format of index for command delete given";
                int taskIndex = Integer.parseInt(commands[1]);
                message = this.taskArray.handleDelete(taskIndex);
                break;
            }
                case COMMAND_SAVE: {
                message = this.taskArray.handleSave(this.storage);
                break;
            }
            case COMMAND_FIND: {
                message = this.taskArray.handleFind(commands[1]);
                break;
            }
            case COMMAND_RESET: {
                message = this.taskArray.handleReset();
                break;
            }
            case COMMAND_HELP: {
                message = this.taskArray.handleHelp();
                break;
            }    
            default: {
                message = Formatter.getResponseString(ERROR_UNKNOWN_COMMAND);
                break;
            }
            }
            return new Response(isBye, message);
        } catch (DukeException e) {
            return new Response(isBye, e.getMessage());
        }
    }

    /**
     * Returns the response for start up.
     * @return
     */
    public String handleStart() throws DukeException {
        List<String> startMessage = GREETING;

        List<String> taskArrayAsString = storage.load();
        this.taskArray = new TaskList(taskArrayAsString);
        if (taskArrayAsString.isEmpty()) {
            startMessage.add(READ_FAILURE);
        } else {
            startMessage.add(READ_SUCCESS);
        }
        return Formatter.getResponseString(startMessage);
    }

    public static String[] getListOfCommands() {
        return new String[] { COMMAND_TODO, COMMAND_EVENT, COMMAND_DEADLINE, COMMAND_BYE,
            COMMAND_LIST, COMMAND_DONE, COMMAND_DELETE, COMMAND_SAVE, COMMAND_FIND, COMMAND_HELP, COMMAND_RESET };
    }

}
