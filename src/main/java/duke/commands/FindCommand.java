package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;

public class FindCommand extends Command {
    private String keyword;
    /**
     * Constructor for Command.
     *
     * @param keyword the keywords entered.
     */
    public FindCommand(String desc, String keyword) {
        super(desc);
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        ArrayList<Task> filtered = tasks.filter(keyword);
        System.out.println("-------------------------------------");
        if (filtered.size() == 0) {
            System.out.println("Unfortunately, no match can be found, Master Wayne.");
        } else {
            System.out.println("Here are the matching tasks in your list, Master Wayne:");
            for (int i = 0; i < filtered.size(); i++) {
                System.out.println((i + 1) + ". " + filtered.get(i));
            }
        }
        System.out.println("-------------------------------------");
    }
}
