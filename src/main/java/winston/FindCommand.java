package winston;

public class FindCommand extends Command {
    private String str;

    /**
     *  Represents an find command from the parent abstract class Command.
     */
    public FindCommand(TaskList taskList, String str) {
        super(taskList);
        this.str = str;
    }

    /**
     * Creates a new TaskList containing all tasks with given string as a substring and prints out
     * a list containing all of these tasks
     */
    @Override
    public void run() {
        TaskList tList = taskList.find(this.str);
        Ui.printMatchingTasks();
        Ui.printList(tList);
    }
}
