public interface Command {

    public void execute(TaskList taskList, Ui ui);
    public boolean isRunning();

}
