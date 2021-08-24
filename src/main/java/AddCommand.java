class AddCommand extends Command {

    private Task task;

    AddCommand(Task task){
        this.task = task;
    }

    void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.add(task);
        ui.printAdd(task, tasks);
    }
}