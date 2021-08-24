package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandDelete extends Command {
    public static final String KEYWORD = "delete";
    private static final String ARG_FORMAT = "\\d|\\d\\d|100";
    private ArrayList<String> arguments;


    public CommandDelete(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isArgumentValid() {
        if (arguments.size() == 1) {
            Pattern pattern = Pattern.compile(ARG_FORMAT);
            Matcher matcher = pattern.matcher(arguments.get(0));
            return matcher.matches();
        } else {
            return false;
        }
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        if (isArgumentValid()) {
            int number = Integer.parseInt(arguments.get(0)) - 1;
            if (number + 1 <= tl.numberOfTasks() && number + 1 > 0) {
                ui.printout("Noted, I've removed this task\n" + tl.getTaskString(number));
                tl.removeTask(number);
                ui.printout("Now you have " + tl.numberOfTasks() + " tasks in the list.");
            } else {
                throw new DukeException("That task does not exist!");
            }
        } else {
            throw new DukeException("Invalid argument for duke.command: delete");
        }
    }

}
