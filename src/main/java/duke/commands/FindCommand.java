package duke.commands;

import java.util.ArrayList;

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
    public void execute(TaskList tasks) {
        ArrayList<Task> filtered = tasks.filter(keyword);
        System.out.println("-------------------------------------");
        if (filtered.size() == 0) {
            System.out.println("Unfortunately, no match can be found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < filtered.size(); i++) {
                System.out.println((i + 1) + ". " + filtered.get(i));
            }
        }
        System.out.println("-------------------------------------");
    }
}
