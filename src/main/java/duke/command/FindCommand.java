package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String results = "Here are the matching tasks in your list:";
        boolean hasMatches = false;
        for (int i = 0; i < tasks.getSize(); i++) {
            String taskDesc = tasks.getTask(i).getDescription();

            if (!taskDesc.contains(query)) {
                continue;
            }

            results += System.lineSeparator() + " " + (i + 1) + "." + tasks.getTask(i);
            hasMatches = true;
        }

        if (hasMatches) {
            ui.printTemplate(results);
        } else {
            ui.printTemplate("No matches found.");
        }
    }
}
