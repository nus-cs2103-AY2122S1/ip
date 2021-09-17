package hyddd.executions;

import java.util.ArrayList;

import hyddd.command.AddCommand;
import hyddd.command.ByeCommand;
import hyddd.command.Command;
import hyddd.command.DeleteCommand;
import hyddd.command.DoneCommand;
import hyddd.command.FindCommand;
import hyddd.command.HelpCommand;
import hyddd.command.ListCommand;
import hyddd.command.TellCommand;
import hyddd.command.UndoCommand;
import hyddd.exceptions.HydddException;
import hyddd.logics.Parser;
import hyddd.task.TaskList;
import hyddd.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * Execution will return a hyddd response based on the command users take in.
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
     * @param taskList the taskList hyddd uses to do operation.
     * @param textUi Ui of hyddd
     * @param parser Parser that will help split the command.
     */
    public Execution(TaskList taskList, TextUi textUi, Parser parser) {
        this.taskList = taskList;
        this.textUi = textUi;
        this.parser = parser;
    }

    /**
     * Returns a couple of sentences indicates hyddd's Response.
     *
     * @return String value that represents hyddd's response.
     */
    public String executionResponse() {
        String text = dealWithInput();
        assert text.equals("") : "OOPS, hyddd stops responding!";
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
        String hydddResponse;
        int index;

        try {
            parsedInputs = parser.returnSplitComponent();
        } catch (HydddException e) {
            return e.getErrorMessage();
        }

        assert parsedInputs.size() == 4 : "Error in Parser, should produce 4 key value for hyddd to execute!!";

        operationType = parsedInputs.get(0);
        task = parsedInputs.get(1);
        time = parsedInputs.get(2);
        index = Integer.parseInt(parsedInputs.get(3));
        command = operationForhyddd(index, operationType, task, time);

        hydddResponse = command.returnResponse();

        return hydddResponse;
    }

    /**
     * Returns a command to execute with info of index, operation type, task info and time info.
     * Every time an execution is done, the task will be stored to the local file called tasks.txt
     * via Storage.
     *
     * @param index Index of the task users input.
     * @param commands JavaVarargs Commands users input.
     */
    public Command operationForhyddd(int index, String... commands) {
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
