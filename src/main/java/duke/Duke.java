package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.command.CommandEnum;
import duke.exception.DukeException;

public class Duke {
    
    private static final String STORAGE_DIRECTORY = "FergusChatBot.txt";

    private static final String READ_SUCCESS = "A saved file has been found! It will now be loaded. Enter 'list' to view";
    private static final String READ_FAILURE = "No saved file has been found :(";

    private static final List<String> GREETING = new ArrayList<>(Arrays.asList("Hello! I'm Fergus' Chatbot!", "What can I do for you?"));
    private static final String FAREWELL = "Bye. Hope to see you again soon! The program will close in 3 seconds!";
    private static final String ERROR_UNKNOWN_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";

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
        String userInputCommand = commands[0];
        assert !userInputCommand.isEmpty() : "The given input command is empty or invalid";

        String message = "";
        boolean isTerminate = false;

        try {
            CommandEnum command = CommandEnum.getCommand(userInputCommand);
            switch (command) {
            case BYE: {
                message = Formatter.getResponseString(FAREWELL);
                isTerminate = true;
                break;
            }
            case LIST: {
                message = this.taskArray.handleList();
                break;
            }
            case EVENT: {
                message = this.taskArray.handleAddEvent(commands);
                break;
            }
            case DEADLINE: {
                message = this.taskArray.handleAddDeadline(commands);
                break;
            }
            case TODO: {
                message = this.taskArray.handleAddToDo(commands);
                break;
            }
            case DONE: {
                assert Arrays.asList(commands).size() >= 2 : "Invalid format of index for command done given";
                int taskIndex = Integer.parseInt(commands[1]);
                message = this.taskArray.handleDone(taskIndex);
                break;
            }
            case DELETE: {
                assert Arrays.asList(commands).size() >= 2 : "Invalid format of index for command delete given";
                int taskIndex = Integer.parseInt(commands[1]);
                message = this.taskArray.handleDelete(taskIndex);
                break;
            }
            case SAVE: {
                message = this.taskArray.handleSave(this.storage);
                break;
            }
            case FIND: {
                message = this.taskArray.handleFind(commands[1]);
                break;
            }
            case RESET: {
                message = this.taskArray.handleReset();
                break;
            }
            case HELP: {
                message = this.taskArray.handleHelp();
                break;
            }    
            default: {
                throw new DukeException(ERROR_UNKNOWN_COMMAND);
            }
            }
        } catch (DukeException e) {
            return new Response(false, true, e.getMessage());
        }
        return new Response(isTerminate, false, message);
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

}
