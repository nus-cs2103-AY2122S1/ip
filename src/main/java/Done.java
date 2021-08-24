class Done extends Command {
    private int index;

    public Done(int index) {
        this.index = index;
    }

    public void exec(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(this.index - 1).markAsDone();
    }
}
