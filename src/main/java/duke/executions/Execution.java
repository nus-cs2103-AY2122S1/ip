package duke.executions;

import java.util.ArrayList;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TellCommand;
import duke.command.UndoCommand;
import duke.exceptions.DukeException;
import duke.logics.Parser;
import duke.task.TaskList;
import duke.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * Execution will return a Duke response based on the command users take in.
 */
public class Execution {
    //Constant values
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String TELL = "tell";
    private static final String FIND = "find";
    private static final String UNDO = "undo";
    private static final String HELP = "help";
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
    public String executionResponse() {
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
        ArrayList<String> parsedInputs;
        Command command;
        String operationType;
        String task;
        String time;
        String dukeResponse;
        int index;

        try {
            parsedInputs = parser.returnSplitComponent();
        } catch (DukeException e) {
            return e.getErrorMessage();
        }

        assert parsedInputs.size() == 4 : "Error in Parser, should produce 4 key value for duke to execute!!";

        operationType = parsedInputs.get(0);
        task = parsedInputs.get(1);
        time = parsedInputs.get(2);
        index = Integer.parseInt(parsedInputs.get(3));
        command = operationForDuke(index, operationType, task, time);

        dukeResponse = command.returnResponse();

        return dukeResponse;
    }

    /**
     * Returns a command to execute with info of index, operation type, task info and time info.
     * Every time an execution is done, the task will be stored to the local file called tasks.txt
     * via Storage.
     *
     * @param index Index of the task users input.
     * @param commands JavaVarargs Commands users input.
     */
    public Command operationForDuke(int index, String... commands) {
        Command command;
        String operationType = commands[0];
        String task = commands[1];
        String time = commands[2];

        switch (operationType) {
        case BYE: command = new ByeCommand(textUi);
        break;
        case LIST: command = new ListCommand(taskList);
        break;
        case DONE: command = new DoneCommand(taskList, textUi, index);
        break;
        case DELETE: command = new DeleteCommand(taskList, textUi, index);
        break;
        case TELL: command = new TellCommand(taskList, textUi, time);
        break;
        case FIND: command = new FindCommand(taskList, textUi, task);
        break;
        case UNDO: command = new UndoCommand(taskList, textUi);
        break;
        case HELP: command = new HelpCommand(textUi);
        break;
        default: command = new AddCommand(taskList, textUi, operationType, task, time);
        break;
        }
        return command;
    }
}
