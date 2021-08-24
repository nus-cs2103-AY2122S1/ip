class List extends Command {
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        tasks.display();
    }
}
