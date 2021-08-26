public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "DEADLINE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Adds a deadline task to the list";

    public DeadlineCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    @Override
    public void execute(String cmd) {
        try {
            if (cmd.length() < 10) {
                throw new DukeException(Ui.emptyDescription("deadline"));
            } else {
                String[] split = cmd.split("/by ");
                if (split.length > 1) {
                    Deadline deadline = new Deadline(split[0].substring(9), split[1]);
                    taskHandler.addDeadline(deadline);
                    taskHandler.printNoOfTasks();
                    storage.updateFile(taskHandler.formatTaskToSave());
                } else {
                    throw new DukeException(Ui.dateMissing());
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}

