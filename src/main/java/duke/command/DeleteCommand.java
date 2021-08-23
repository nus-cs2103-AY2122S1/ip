package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {

    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Pattern pattern = Pattern.compile("^delete (\\d+) *?$");
        Matcher m = pattern.matcher(input);

        if (m.find()) {
            taskList.removeFromList(Integer.parseInt(m.group(1)));
        } else {
            System.out.println("Please indicate a task to delete");
        }
    }
}