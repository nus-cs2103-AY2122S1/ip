class DeleteCommand extends Command{
    private final String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int delId = Integer.parseInt(fullCommand.split(" ")[1]);
        taskList.deleteTask(delId);
    }
}
