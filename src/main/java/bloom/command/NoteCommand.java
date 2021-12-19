package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Task;

/**
 * Represents a note command which
 * adds note to a specific note.
 */
public class NoteCommand extends Command {

    /** The index of the task to be added new note. */
    private final int index;

    /** The description of the note. */
    private final String description;

    /**
     * Constructor for a NoteCommand.
     *
     * @param index       the index of the task to be added new note
     * @param description the description of the note
     */
    public NoteCommand(int index, String description) {
        this.index = index;
        this.description = description;
    }

    /**
     * Creates note for a specific task.
     */
    @Override
    public String run() {
        Task task = TaskList.get(this.index);
        task.setNote(description);
        return Message.COMMAND_NOTE.getMessage() + "\t   " + task;
    }
}
