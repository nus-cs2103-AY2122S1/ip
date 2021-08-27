package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private String command;

    public FindCommand(String command) {
        super(command);
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String keyWord = command.split(" +", 2)[1].trim();
        int count = 0;
        String matchingTasks = "";
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).toString().contains(keyWord)) {
                count++;
                matchingTasks += Ui.INDENT_2 + count + ". " + tasks.get(i)
                        + System.lineSeparator();
            }
        }
        if(count == 0) {
            ui.noSuchTask();
        } else {
            ui.findTask();
            System.out.println(matchingTasks);
        }
    }
}
