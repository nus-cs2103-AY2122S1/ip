public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "DONE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": marks selected task as done in the list";

    public DoneCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    @Override
    public void execute(String cmd) {
        try {
            taskHandler.markTaskAsDone(Integer.parseInt(cmd.substring(5)));
            storage.updateFile(taskHandler.formatTaskToSave());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
