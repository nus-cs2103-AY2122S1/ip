abstract class Command {
    // BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT;

    protected TaskList taskList;

    public void setTaskList(TaskList tl) {
        taskList = tl;
    }

    public abstract CommandResult execute();
}
