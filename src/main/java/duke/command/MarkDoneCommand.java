package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkDoneCommand extends Command {

    private final String input;

    public MarkDoneCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Pattern pattern = Pattern.compile("^done (\\d+) *?$");
        Matcher m = pattern.matcher(input);

        if (m.find()) {
            taskList.markAsDone(Integer.parseInt(m.group(1)));
        } else {
            System.out.println("Please indicate a task to mark as done");
        }
    }
}