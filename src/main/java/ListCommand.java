class ListCommand extends Command {

    private TaskList tasks;
    private Ui ui;

    ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }
    @Override
    protected void execute(){
        ui.showList(tasks);
    }
}
