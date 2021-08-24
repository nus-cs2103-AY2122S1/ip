public class DeleteCommand implements  Command {
    private String text;

    public DeleteCommand(String arr) {
        this.text = arr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Integer.parseInt(text.trim()) - 1;
            String t = taskList.removeTask(i);
            ui.echo("Noted. I removed this task: " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Enter a valid task no. to delete the task.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
