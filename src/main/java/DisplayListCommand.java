class DisplayListCommand extends Command{
    public DisplayListCommand() {}

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.displayList();
    }
}
