/**
 * Done command to mark task as done
 */
public class DoneCommand extends Command {

    /**
     * Constructors for DoneCommand
     *
     * @param userInput User input array with this structure: [command, details]
     */
    public DoneCommand(String[] userInput) {
        super(CommandType.DONE, userInput);
    }

    /**
     * Marks specified task as done
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
                Task current = taskList.getTask(Integer.parseInt(userInput[1]) - 1);
                current.setDone(true);
                storage.save();
                ui.showMessage("Nice! I've marked this task as done, woof!\n\t" + current);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! Please enter a valid number" +
                        (taskList.size() == 1 ? " of 1" : " from 1 to " + taskList.size()) + ".");
            }
        } else {
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The done task number cannot be empty.");
        }
    }
}
