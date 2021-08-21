public class DeleteCommand implements Command {
    /** Stores the message entered by the . */
    private String message;

    /** Constructor for DeleteCommand.
     *
     * @param message Stores the message entered by the user.
     */
    DeleteCommand(String message) {
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
            taskList.deleteTask(Integer.parseInt(message.substring(Keyword.DELETE.length() + 1)));
        } catch (NumberFormatException e) {
            ui.deleteErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.deleteIndexErrorMessage();
        }
    }
}
