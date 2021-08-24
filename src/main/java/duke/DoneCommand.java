package duke;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Handle user inputs that are out of bounds
        if (index > tasks.getSize() || index <= 0) {
            throw new DukeException("OOPS!!! I'm sorry, but you've entered an invalid index.");
        }

        // Mark task as done, and persist to text file
        Task t = tasks.markAsDone(index);
        storage.save(tasks);

        // Display response to user
        ui.showResponse("Nice! I've marked this task as done: \n\t\t " + t.toString());
    }
}
