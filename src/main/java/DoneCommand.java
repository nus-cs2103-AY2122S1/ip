public class DoneCommand extends Command {

    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JWBotException {
        try {
            String[] separated = input.split(" ");
            int index = Integer.parseInt(separated[1]);
            tasks.getTask(index - 1).markAsDone();
            storage.write(tasks);
            ui.showDoneSuccessMessage(tasks.getTask(index - 1));
        } catch (Exception e) {
            throw new JWBotException("Sorry bro, I think you chose an incorrect index number to mark done!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
