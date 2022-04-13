package duke;

/**
 * Finds task from TaskList.
 */
public class Find extends Command {

    /** The name of the input command by the user. */
    private String name;


    public Find(String name) {
        this.name = name;
    }

    /**
     * Runs the find command.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui to display to the user.
     * @param storage Storage to store tasks.
     * @return String to be displayed.
     */
    public String run(TaskList tasks, Ui ui, Storage storage) {
        String res = "";
        boolean isNotFound = true;
        int i = 1;
        for (int j = 0; j < tasks.numberOfTasks(); j++) {
            if (!tasks.getTaskFromList(j).getPreExisting()) {
                if (tasks.getTaskFromList(j).toString().contains(name)) {
                    isNotFound = false;
                    res = res + "    " + (i++) + ". " + tasks.getTaskFromList(j).toString() + "\n";
                }
            } else {
                if (tasks.getTaskFromList(j).getDescription().contains(name)) {
                    isNotFound = false;
                    res = res + "    " + (i++) + ". " + tasks.getTaskFromList(j).getDescription() + "\n";
                }
            }
        }
        if (isNotFound) {
            return "oops! sorry you do not have any matching tasks";
        } else {
            return ui.showFind(res);
        }
    }
}
