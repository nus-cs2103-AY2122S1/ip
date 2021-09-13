package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_NOT_AN_INT_PARAM;
import static kayu.commands.CommandMessage.MESSAGE_DELETED_NOTE;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.note.Note;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;

/**
 * Represents a {@link kayu.commands.Command} that deletes a certain {@link kayu.note.Note}
 * in {@link NoteList}.
 */
public class DeleteNoteCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "delete-note";

    /**
     * Initializes a Delete- {@link kayu.commands.Command}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public DeleteNoteCommand(String commandParams) {
        super(commandParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList,
                          TaskStorage taskStorage,
                          NoteList noteList,
                          NoteStorage noteStorage)
            throws KayuException, StorageException {

        try {
            int noteId = Integer.parseInt(commandParams);
            Note selectedNote = noteList.deleteNoteById(noteId);
            super.updateNoteFileStorage(noteList, noteStorage);
            return String.format(MESSAGE_DELETED_NOTE, selectedNote, noteList.getCurrentCapacity());

        } catch (NumberFormatException exception) {
            throw new KayuException(String.format(ERROR_NOT_AN_INT_PARAM, commandParams));
        }
    }
}
