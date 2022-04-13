package kayu.commands;

import static kayu.commands.CommandMessage.ASSERT_FAIL_NULL_TASK;
import static kayu.commands.CommandMessage.MESSAGE_CREATED_NOTE;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.note.Note;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;

/**
 * Represents an {@link Command} that creates a {@link kayu.note.Note}
 * and saves it in {@link NoteList}.
 */
public class NoteCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "note";

    /**
     * Initializes a Note- {@link Command}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public NoteCommand(String commandParams) {
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

        Note note = createNote();
        updateNotes(noteList, noteStorage, note);

        return String.format(MESSAGE_CREATED_NOTE, note, noteList.getCurrentCapacity());
    }

    private Note createNote() throws KayuException {
        String desc = extractDesc(commandParams);
        return new Note(desc);
    }

    // call on wrapper method in util class
    private String extractDesc(String params) throws KayuException {
        return CommandUtils.extractDesc(new String[] {params}, COMMAND_WORD);
    }

    private void updateNotes(NoteList noteList, NoteStorage noteStorage, Note note) throws StorageException {
        assert (note != null) : ASSERT_FAIL_NULL_TASK;
        noteList.addNote(note);
        super.updateNoteFileStorage(noteList, noteStorage);
    }
}
