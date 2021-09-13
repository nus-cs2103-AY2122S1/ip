package duke.executions;

import duke.command.Command;
import duke.command.UndoCommand;
import duke.command.TellCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.exceptions.DukeException;
import duke.logics.Parser;
import duke.task.TaskList;
import duke.uimanager.TextUi;

import java.util.ArrayList;

/**
 * @@author Hang Zelin
 *
 * Execution will return a Duke response based on the command users take in.
 */
public class Execution {
    //Constant values
    private final static String BYE = "bye";
    private final static String LIST = "list";
    private final static String DELETE = "delete";
    private final static String DONE = "done";
    private final static String TELL = "tell";
    private final static String FIND = "find";
    private final static String UNDO = "undo";
    private final TaskList taskList;
    private final TextUi textUi;
    private final Parser parser;

    /**
     * Constructor of Execution
     *
     * @param taskList the taskList Duke uses to do operation.
     * @param textUi Ui of Duke
     * @param parser Parser that will help split the command.
     */
    public Execution(TaskList taskList, TextUi textUi, Parser parser) {
        this.taskList = taskList;
        this.textUi = textUi;
        this.parser = parser;
    }

    /**
     * Returns a couple of sentences indicates Duke's Response.
     *
     * @return String value that represents Duke's response.
     */
    public String ExecutionResponse() {
        String text = dealWithInput();
        assert text.equals("") : "OOPS, Duke stops responding!";
        return text;
    }

    /**
     * Returns a String provided by user's specific command.
     * It makes use of Parse to split a line of command into 3 small pieces,
     * and put in specific command to get response.
     *
     * @return String value from a specifc Command.
     */
    private String dealWithInput() {
        ArrayList<String> parsedMessages;
        Command command;
        String operationType, task, time;
        String dukeResponse;
        int index;

        try {
            parsedMessages = parser.returnSplitComponent();
        } catch (DukeException e) {
            return e.getErrorMessage();
        }

        assert parsedMessages.size() == 4 : "Error in Parser, should produce 4 key value for duke to execute!!";

        operationType = parsedMessages.get(0);
        task = parsedMessages.get(1);
        time = parsedMessages.get(2);
        index = Integer.parseInt(parsedMessages.get(3));
        command = operationForDuke(index, operationType, task, time);

        dukeResponse = command.returnResponse();

        return dukeResponse;
    }

    /**
     * Chooses a specific task to execute via tasks type and add to the tasklists.
     * Every time an execution is done, the task will be stored to the local file called tasks.txt
     * via Storage.
     *
     * @param index Index of the task users input.
     * @param commands JavaVarargs Commands users input.
     */
    public Command operationForDuke(int index, String... commands) {
        Command command;
        String operationType = commands[0], task = commands[1], time = commands[2];

        command = switch (operationType) {
            case BYE -> new ByeCommand(textUi);
            case LIST -> new ListCommand(taskList);
            case DONE -> new DoneCommand(taskList, textUi, index);
            case DELETE -> new DeleteCommand(taskList, textUi, index);
            case TELL -> new TellCommand(taskList, textUi, time);
            case FIND -> new FindCommand(taskList, textUi, task);
            case UNDO -> new UndoCommand(taskList, textUi);
            default -> new AddCommand(taskList, textUi, operationType, task, time);
        };

        return command;
    }
}
