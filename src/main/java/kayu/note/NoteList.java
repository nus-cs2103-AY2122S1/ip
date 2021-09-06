package kayu.note;

import java.util.ArrayList;
import java.util.List;

import kayu.exception.KayuException;

public class NoteList {

    // Error messages.
    protected static final String ERROR_EMPTY_NOTES = "Notes is empty. Add some notes!";
    protected static final String ERROR_INVALID_NOTE_ID = "Task '%d' does not exist.";
    
    private final List<Note> notes = new ArrayList<>();

    public List<Note> getNotes() {
        return notes;
    }
    
    public int getCurrentCapacity() {
        return notes.size();
    }
    
    public void initializeNotes(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
    }
    
    public Note findNoteById(int id) throws KayuException {
        if (notes.isEmpty()) {
            throw new KayuException(ERROR_EMPTY_NOTES);
        }
        if (id >= notes.size() || id < 0) {
            throw new KayuException(String.format(ERROR_INVALID_NOTE_ID, id));
        }
        return notes.get(id);
    }
    
    public Note addNote(Note note) {
        notes.add(note);
        return note;
    }
    
    public Note deleteNoteById(int id) throws KayuException {
        Note note = findNoteById(id);
        notes.remove(id);
        return note;
    }
}
