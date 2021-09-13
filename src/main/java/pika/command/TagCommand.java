package pika.command;

import java.io.IOException;

import pika.exception.PikaException;
import pika.task.TaskList;
import pika.ui.Storage;
import pika.ui.Ui;

/**
 * TagCommand Class for tagging.
 */
public class TagCommand extends Command {
    private final int index;
    private final String tagName;

    /**
     * Constructor for TagCommand.
     *
     * @param details the index of the task to be tagged and name of the tag.
     * @throws PikaException Throws this is there are anything wrong with the inputs.
     */
    public TagCommand(String details) throws PikaException {
        super(true);
        if (details == null) {
            throw new PikaException("Pika pi!! Please specify which task to tag!");
        }
        int spaceIndex = details.indexOf(" ");
        if (spaceIndex == -1) {
            throw new PikaException("Pika pi!! Your tag command is missing something!");
        }

        this.index = Integer.parseInt(details
                        .substring(0, spaceIndex)
                        .trim());
        this.tagName = details.substring(spaceIndex).trim();
    }
    /**
     * Executes the TagCommand to update the list, and returns the tag.
     *
     * @param taskList The current list of tasks.
     * @param storage  The current storage class to handle the txt file.
     * @return The add tag string for the Pikabot to say.
     * @throws IOException   if the filepath has any issues.
     * @throws PikaException if there are any other format/input issues.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException, PikaException {
        if (index > taskList.getCount() || index <= 0) {
            //Catches if the number is > than the number of task or if its negative
            throw new PikaException("Pika pi!! The number is not in within the number of tasks!");
        } else {
            String s = taskList.get(index - 1).addTag(tagName);
            Storage.updateText(taskList);
            return Ui.addedTagMessage(index, s);
        }
    }
}
