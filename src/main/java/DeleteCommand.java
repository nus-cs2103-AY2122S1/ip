public class DeleteCommand extends Command {
    private final int  index;
    
    public DeleteCommand(String rest) {
        this.index = Integer.parseInt(rest.strip());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        Task t = tasks.get(index - 1);
        storage.saveData(tasks.delete(index - 1));
        ui.print(String.format(
                "Noted. I've removed this task:\n %s\nNow you have %d task(s) in the list",
                t.toString(),
                tasks.count));
    }
}
