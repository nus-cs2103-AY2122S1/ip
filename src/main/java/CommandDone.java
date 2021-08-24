public class CommandDone extends Command{
    CommandDone(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException {
        checkCompleteCommand(this.inputTokens);
        try {
            int taskIndex = Integer.parseInt(inputTokens[1]) - 1;
            tasks.get(taskIndex).toggleDone();
            ui.printMarkTaskMessage(tasks, taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new YoyoException.YoyoTaskIndexException("Please enter A valid task index!");
        }
    }
}
