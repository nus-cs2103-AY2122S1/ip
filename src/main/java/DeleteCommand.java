class DeleteCommand extends Command {

    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    void execute(TaskList tasks, Ui ui, Storage storage){
        ui.printDelete(tasks, index);
        tasks.delete(index);
    }
    
}