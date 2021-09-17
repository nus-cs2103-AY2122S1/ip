package command;

import bot.DukeException;
import bot.Storage;
import bot.TaskList;
import bot.UserInterface;
import task.Task;

/**
 * A class that encapsulates a Tag Command given to Duke.
 */
public class TagCommand extends Command {

    /**
     * Constructor for the TagCommand class.
     *
     * @param input The input given by the user.
     */
    public TagCommand(String input) {
        super(input);
    }

    /**
     * Returns the proper response according to the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @return A String representation of Duke's response according to the input given by the user.
     * @throws DukeException if the input given is not of the correct format.
     */
    @Override
    public String execute(TaskList list, UserInterface ui) throws DukeException {
        try {
            if (input.trim().length() == 3) {
                throw new DukeException(
                        "It looks like you did not enter a valid integer for the \"tag\" command. Please try again!");
            }
            int index = Integer.parseInt(input.trim().substring(4)) - 1;
            if (index >= list.getSize() || index < 0) {
                throw new DukeException("That task doesn't exist. Please try again!");
            }
            Task newTask = list.getTask(index);
            if (!tags.isEmpty()) {
                String[] rawTags = tags.split(" ");
                for (int i = 0; i < rawTags.length; i++) {
                    newTask.addTag(rawTags[i]);
                }
                list.setTask(index, newTask);
                Storage.save(list);
                return ui.showTaskTagged(list.getTask(index));
            } else {
                throw new DukeException(
                        "It looks like you did not enter any tags for the \"tag\" command. Please try again!");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(
                    "It looks like you did not enter a valid integer for the \"tag\" command. Please try again!");
        }
    }
}
