class DoneCommand extends Command {
    
    private int toComplete;

    DoneCommand(int toComplete) {
        this.toComplete = toComplete;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDone(tasks, toComplete);
        tasks.get(toComplete).complete();
    }

}