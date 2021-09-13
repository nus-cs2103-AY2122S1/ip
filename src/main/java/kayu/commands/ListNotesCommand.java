package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_EMPTY_NOTE_LIST;

import java.util.List;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.note.Note;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;

/**
 * Represents a {@link kayu.commands.Command} that provides the {@link kayu.note.Note}
 * that are present in {@link NoteList}.
 */
public class ListNotesCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "list-notes";

    /**
     * Initializes a List-notes- {@link kayu.commands.Command}.
     */
    public ListNotesCommand() {
        super();
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

        return listNotes(noteList);
    }

    private String listNotes(NoteList noteList) {
        List<Note> notes = noteList.getNotes();
        if (notes.isEmpty()) {
            return MESSAGE_EMPTY_NOTE_LIST;
        }
        return CommandUtils.generateFormattedItemListResponse(notes);
    }
}
