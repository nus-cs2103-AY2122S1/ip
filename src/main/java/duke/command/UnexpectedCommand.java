package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class represents the error command class, where if the user input something
 * not recognisable by the parser.
 */
public class UnexpectedCommand extends Command {
    private String error;

    /**
     * Constructor for the UnexpectedCommand, which takes in the type of error.
     *
     * @param error the type of error.
     */
    public UnexpectedCommand(String error) {
        this.error = error;
    }

    /**
     * Returns a String based off the type of error is found.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     * @return the error message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String output = null;
        switch (error) {
        case "date time parse exception":
            output = Ui.dateTimeParseExceptionMessage();
            break;
        case "array out of bounds exception, event":
            output = Ui.arrayIndexOutOfBoundsExceptionMessage() + "\n";
            output += Ui.eventHint();
            break;
        case "array out of bounds exception, todo":
            output = Ui.arrayIndexOutOfBoundsExceptionMessage() + "\n";
            output += Ui.toDoHint();
            break;
        case "array out of bounds exception, deadline":
            output = Ui.arrayIndexOutOfBoundsExceptionMessage() + "\n";
            output += Ui.deadlineHint();
            break;
        case "number format exception":
            output = Ui.numberFormatExceptionMessage();
            break;
        case "array out of bounds exception":
            output = Ui.arrayIndexOutOfBoundsExceptionMessage() + "\n";
            break;
        case "array out of bounds exception, find":
            output = Ui.arrayIndexOutOfBoundsExceptionMessage() + "\n";
            output += Ui.findHint();
            break;
        default:
            output = Ui.defaultMessage();
            break;
        }
        return output;
    }
}
