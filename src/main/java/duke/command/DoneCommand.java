public class DoneCommand extends Command {
    private final String fullCommand;

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        char lastDigit = fullCommand.charAt(fullCommand.length() - 1);
        int index = Integer.parseInt(String.valueOf(lastDigit));
        taskList.setAsDone(index);
    }

}