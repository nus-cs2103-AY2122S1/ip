package duke;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Handle user inputs that are out of bounds
        if (index > tasks.getSize() || index <= 0) {
            throw new DukeException("OOPS!!! I'm sorry, but you've entered an invalid index.");
        }

        // Delete task from list of tasks, and persist to text file
        Task t = tasks.delete(index);
        storage.save(tasks);

        // Display response to user
        ui.showResponse("Noted. I've removed this task: \n\t\t "
                + t
                + "\n\t Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
