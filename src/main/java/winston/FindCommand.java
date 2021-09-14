package winston;

public class FindCommand extends Command {
    private String str;

    /**
     * Represents an findString command from the parent abstract class Command.
     */
    public FindCommand(TaskList taskList, String str) {
        super(taskList);
        this.str = str;
    }

    /**
     * Creates a new TaskList containing all tasks with given string as a substring and prints out
     * a list containing all of these tasks.
     */
    @Override
    public String run() {
        TaskList tList = taskList.findString(this.str);
        String result = Ui.printMatchingTasks() + Ui.printList(tList);
//        assert(!result.equals(""));
        return result;
    }
}
