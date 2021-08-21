public class EventCommand implements Command {
    /** Stores the message entered by the user. */
    private String message;

    /** Constructor for EventCommand.
     *
     * @param message Stores the message entered by the user.
     */
    EventCommand(String message) {
        this.message = message;
    }

    /**
     * Main codes to run for the chat.
     *
     * @param taskList TaskList to execute the command.
     * @param ui To interact with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            String[] details = message.split(Keyword.EVENTS.getSeparator());
            taskList.addEvent(details[0].substring(Keyword.EVENTS.length() + 1), details[1]);
        } catch (IndexOutOfBoundsException e) {
            ui.eventErrorMessage();
        }
    }
}
