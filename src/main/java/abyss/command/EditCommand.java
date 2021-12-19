package abyss.command;

import abyss.Abyss;
import abyss.exception.InvalidCommandException;

/**
 * Represents a command to add a to-do task to the list of tasks.
 */
public abstract class EditCommand implements Command {
    private static final String EDIT_REGEX = "^\\d*[ ]+\\S[ -~]*$";
    private static final String EDIT_DATE_REGEX = "^\\/d[ ]+\\S[ -~]*$";

    protected static EditCommand makeEditCommand(String content) throws InvalidCommandException {
        if (!content.matches(EDIT_REGEX)) {
            throw new InvalidCommandException("To edit a task, use command 'edit <task index> /d <date>' or "
                                                  + "'edit <task index> <task description>'.");
        }

        int numberOfTasks = Abyss.getTaskManager().getNumberOfTasks();
        if (numberOfTasks == 0) {
            throw new InvalidCommandException("The Abyss is empty.");
        }

        String[] parts = content.split("[ ]+", 2);
        int index = Integer.parseInt(parts[0]);
        if (index < 1 || index > numberOfTasks) {
            throw new InvalidCommandException("Index should be positive and not more than "
                                                  + numberOfTasks);
        }

        String editContent = parts[1];
        EditCommand cmd;
        if (editContent.matches(EDIT_DATE_REGEX)) {
            String[] subParts = editContent.split("[ ]+", 2);
            cmd = new EditDateCommand(index, subParts[1]);
        } else {
            cmd = new EditDescriptionCommand(index, editContent);
        }
        return cmd;
    }
}
