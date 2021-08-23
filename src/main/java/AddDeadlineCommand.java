public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JWBotException {
        try {
            String content = input.split(" ", 2)[1];
            String[] separated = content.split(" /by ");
            Deadline deadline = new Deadline(separated[0], separated[1]);
            tasks.addTask(deadline);
            storage.write(tasks);
            ui.showAddTaskSuccessMessage(deadline);
        } catch (Exception e) {
            throw new JWBotException("Sorry bro, I think you made an error with the deadline format!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
