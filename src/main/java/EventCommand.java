public class EventCommand extends Command {
    public static final String COMMAND_WORD = "EVENT";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Adds an event task to the list";

    public EventCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    @Override
    public void execute(String cmd) {
        try {
            if (cmd.length() < 7) {
                throw new DukeException(Ui.emptyDescription("event"));
            } else {
                String[] split = cmd.split("/at ");
                if (split.length > 1) {
                    Event event = new Event(split[0].substring(6), split[1]);
                    taskHandler.addEvent(event);
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


