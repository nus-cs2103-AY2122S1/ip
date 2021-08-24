public abstract class Command implements ListNumberPrintable {

    private final TaskList taskList;

    Command(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract CommandResult execute() throws DukeException;

    public TaskList getTaskList() {
        return this.taskList;
    }

    @Override
    public String printListNumber(TaskList taskList) {
        return "You now have "
                + taskList.size() + " tasks in the list.";
    }

}

