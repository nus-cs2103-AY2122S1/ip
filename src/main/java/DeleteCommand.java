/**
 * Delete command to delete task
 */
public class DeleteCommand extends Command {

    /**
     * Constructors for DeleteCommand
     *
     * @param userInput User input array with this structure: [command, details]
     */
    public DeleteCommand(String[] userInput) {
        super(CommandType.DELETE, userInput);
    }

    /**
     * Delete specified task from list
     *
     * @param taskList Task list
     * @param ui       Ui to display
     * @param storage  Storage to save to
     * @throws BiscuitException Invalid input by user
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BiscuitException {
        if (userInput.length == 2) {
            try {
                int index = Integer.parseInt(userInput[1]) - 1;
                Task toDelete = taskList.getTask(index);
                taskList.removeTask(index);
                storage.save();
                ui.showMessage("Noted. I've removed the following task:\n\t" + toDelete);
                ui.showMessage("Now you have " + taskList.size() + " tasks in the list.");
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! Please enter a valid number" +
                        (taskList.size() == 1 ? " of 1" : " from 1 to " + taskList.size()) + ".");
            }
        } else {
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The delete task number cannot be empty.");
        }
    }
}
