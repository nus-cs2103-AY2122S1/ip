package kayu.service;

import java.util.ArrayList;
import java.util.List;

import kayu.exception.KayuException;
import kayu.note.Note;

/**
 * Manages {@link kayu.note.Note}s held by the {@link kayu.Kayu}.
 */
public class NoteList {

    // Error messages.
    protected static final String ERROR_EMPTY_NOTES = "Notes is empty. Add some notes!";
    protected static final String ERROR_INVALID_NOTE_ID = "Note '%d' does not exist.";

    // Assertion format.
    protected static final String ASSERT_FAIL_DESC_EMPTY = "Note cannot be empty";

    private final List<Note> notes = new ArrayList<>();

    /**
     * Returns the List of {@link kayu.note.Note}s.
     *
     * @return A list of {@link kayu.note.Note}s.
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Gets the number of {@link kayu.note.Note}s current stored.
     *
     * @return Number of notes stored currently.
     */
    public int getCurrentCapacity() {
        return notes.size();
    }

    /**
     * Initializes the {@link #notes} list with the specified {@link kayu.note.Note} list.
     *
     * @param notes List of {@link kayu.note.Note} to initialise {@link #notes} with.
     */
    public void initializeNotes(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
    }

    /**
     * Gets the {@link kayu.note.Note} based on the input <code>id</code>.
     *
     * @param id {@link kayu.note.Note} number to find.
     * @return Associated {@link kayu.note.Note}.
     * @throws KayuException If the {@link #notes} List is empty or the <code>id</code> is not valid.
     */
    public Note findNoteById(int id) throws KayuException {
        int index = id - 1; // 0-indexing
        if (notes.isEmpty()) {
            throw new KayuException(ERROR_EMPTY_NOTES);
        }
        if (index >= notes.size() || index < 0) {
            throw new KayuException(String.format(ERROR_INVALID_NOTE_ID, id));
        }
        return notes.get(index);
    }

    /**
     * Saves the new {@link kayu.note.Note} into {@link #notes} List.
     *
     * @param note New Note to save.
     */
    public void addNote(Note note) {
        assert (!note.getDescription().isBlank()) : ASSERT_FAIL_DESC_EMPTY;
        notes.add(note);
    }

    /**
     * Deletes the {@link kayu.note.Note} based on the input <code>id</code>.
     *
     * @param id {@link kayu.note.Note} number to delete.
     * @return Associated {@link kayu.note.Note} that was deleted.
     * @throws KayuException If the {@link #notes} List is empty or the <code>id</code> is not valid.
     */
    public Note deleteNoteById(int id) throws KayuException {
        Note note = findNoteById(id);
        notes.remove(note);
        return note;
    }
}
