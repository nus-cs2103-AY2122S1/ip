public class AddCommand extends Command {
    private Task t;

    /**
     * Constructor for AddCommand
     * @param t the task to be added
     */
    public AddCommand(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveData(tasks.add(this.t));
        ui.print(String.format(
                        "Got it. I've added this task:\n %s\nNow you have %d task(s) in the list",
                        this.t.toString(),
                        tasks.count));
    }
}
