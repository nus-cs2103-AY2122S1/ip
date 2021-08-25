public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String USAGE_MESSAGE = "To add a new event, use 'event <name> /at <event-timestamp>'.";

    private final String commandArguments;

    public AddEventCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        String[] eventDetails = commandArguments.split("\\s+/at\\s+", 2);
        if (eventDetails.length < 2) {
            throw new DukeException("Invalid use of the 'event' command.\n\n" + USAGE_MESSAGE);
        }
        String eventName = eventDetails[0];
        DukeDateTime eventTimestamp = DukeDateTime.parseUserInputDateTime(eventDetails[1]);
        Event event = new Event(eventName, eventTimestamp);
        ui.print(taskManager.addTask(event));
        storage.saveTasks(taskManager.toText());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
