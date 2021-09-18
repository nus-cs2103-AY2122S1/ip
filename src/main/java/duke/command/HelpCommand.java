package duke.command;

import duke.exception.InvalidInputException;
import duke.util.Help;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class HelpCommand extends Command {

    String keyword = "";

    public HelpCommand(String input) {
        String[] arr = input.split(" ");
        if (arr.length > 2) {
            keyword = "excess";
        } else if (arr.length == 2) {
            keyword = arr[1];
        } else {

        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        switch (keyword.toLowerCase()) {
        case "":
            return Help.format(Help.generalHelp());
        case "list":
            return Help.format(Help.listHelp());
        case "todo":
            return Help.format(Help.toDoHelp());
        case "deadline":
            return Help.format(Help.deadlineHelp());
        case "event":
            return Help.format(Help.eventHelp());
        case "done":
            return Help.format(Help.doneHelp());
        case "delete":
            return Help.format(Help.deleteHelp());
        case "find":
            return Help.format(Help.findHelp());
        case "excess":
            return "You have entered too many arguments. Follow instructions.";
        default:
            return "Your keyword is wrong, fool. Make sure you type in a valid command.";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
