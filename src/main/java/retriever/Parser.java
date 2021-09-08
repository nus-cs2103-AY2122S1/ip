package retriever;

import retriever.exception.IllegalCommandException;
import retriever.exception.RetrieverException;
import retriever.task.TaskList;

/**
 * The Parser class is responsible for parsing
 * various user inputs.
 */
public class Parser {
    private TaskList taskList;
    private boolean isSessionDone;

    /**
     * Initializes the task list and marks the beginning of a session.
     *
     * @param taskList The Object through which various task operations can be performed.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.isSessionDone = false;
    }

    /**
     * Marks the isSessionDone to true, when the user inputs
     * the "bye" keyword, indicating, the end of session.
     */
    public void markSessionDone() {
        isSessionDone = true;
    }

    /**
     * Returns whether the user would like to continue or no.
     *
     * @return A boolean indicating to end the session or not.
     */
    public boolean isSessionDone() {
        return isSessionDone;
    }

    /**
     * Parses the user input, and calls the respective command
     * method to execute it. If the command entered is invalid,
     * an exception is thrown.
     *
     * @param userCommand The command entered by the user.
     * @throws RetrieverException If the command is invalid or unformatted.
     */
    public void parseUserInput(String userCommand) throws RetrieverException {
        // Parsing the user input.
        String[] userInput = userCommand.split(" ");
        String commandEntered = userInput[0].toLowerCase();
        assert commandEntered != null : "Command is Null";

        switch (commandEntered) {
        case "list":
            taskList.printTaskList();
            break;
        case "delete":
            taskList.deleteTask(userInput);
            break;
        case "done":
            taskList.markTaskAsDone(userInput);
            break;
        case "deadline":
            taskList.addDeadlineTask(userCommand);
            break;
        case "event":
            taskList.addEventTask(userCommand);
            break;
        case "todo":
            taskList.addTodoTask(userCommand);
            break;
        case "find":
            taskList.findTaskWithKeyword(userInput);
            break;
        case "help":
            Ui.printHelpSection();
            break;
        case "view":
            taskList.viewScheduleForAParticularDay(userInput);
            break;
        case "bye":
            markSessionDone();
            break;
        default:
            throw new IllegalCommandException("Woof! Command Not Found! Can I Sleep?");
        }
    }
}
