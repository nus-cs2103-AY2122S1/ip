package kayu.commands;

import static kayu.commands.CommandMessage.ASSERT_FAIL_NULL_PARAMS;

import java.util.List;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.note.Note;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;
import kayu.task.Task;

/**
 * Holds the base logic required for other Command classes to utilise.
 */
public abstract class Command {

    protected final String commandParams;

    /**
     * Initializes the Command instance.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public Command(String commandParams) {
        assert (commandParams != null) : ASSERT_FAIL_NULL_PARAMS;
        this.commandParams = commandParams;
    }

    /**
     * Initializes the Command instance.
     * Overloads the {@link #commandParams} as an empty String.
     */
    public Command() {
        this.commandParams = "";
    }

    /**
     * Executes the command based on the implemented child instances
     * and returns the outcome as a String.
     *
     * @param taskList {@link TaskList} instance to execute on.
     * @param taskStorage {@link kayu.storage.TaskStorage} instance to save {@link kayu.task.Task} with.
     * @param noteList {@link NoteList} instance to execute on.
     * @param noteStorage {@link kayu.storage.NoteStorage} instance to save {@link kayu.note.Note} with.
     * @return String feedback of execution/outcome.
     * @throws KayuException If execution of Command fails.
     * @throws StorageException If saving of information using <code>storage</code> fails.
     */
    public abstract String execute(TaskList taskList,
                                   TaskStorage taskStorage,
                                   NoteList noteList,
                                   NoteStorage noteStorage)
            throws KayuException, StorageException;

    /**
     * Returns the command parameters fed.
     *
     * @return String parameters fed into the command by user.
     */
    public String getCommandParams() {
        return commandParams;
    }

    /**
     * Checks if the Command instance is a {@link kayu.commands.ByeCommand}.
     *
     * @return Boolean true if is {@link kayu.commands.ByeCommand}, else false.
     */
    public boolean isBye() {
        return false;
    }

    /**
     * Updates the file storage with the current {@link TaskList}.
     *
     * @param taskList {@link TaskList} instance to execute on.
     * @param taskStorage {@link kayu.storage.TaskStorage} instance to save information with.
     * @throws StorageException If saving of information using <code>storage</code> fails.
     */
    public void updateTaskFileStorage(TaskList taskList, TaskStorage taskStorage) throws StorageException {
        List<Task> tasks = taskList.getTasks();
        taskStorage.save(tasks);
    }

    /**
     * Updates the file storage with the current {@link NoteList}.
     *
     * @param noteList {@link NoteList} instance to execute on.
     * @param noteStorage {@link kayu.storage.NoteStorage} instance to save information with.
     * @throws StorageException If saving of information using <code>storage</code> fails.
     */
    public void updateNoteFileStorage(NoteList noteList, NoteStorage noteStorage) throws StorageException {
        List<Note> notes = noteList.getNotes();
        noteStorage.save(notes);
    }
}
