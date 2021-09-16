package duke.notes;

import java.util.ArrayList;

/**
 * Represent the list of notes in the task manager.
 */
public class NotesList {
    private ArrayList<Note> notes;

    /**
     * Instantiates an object of the NotesList class.
     *
     * @param notes ArrayList of notes.
     */
    public NotesList(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public Integer getNumberOfNotes() {
        return this.notes.size();
    }

    public ArrayList<Note> getNotes() {
        return this.notes;
    }

    /**
     * Adds a note the existing list of notes.
     *
     * @param note Note to be added to the list of notes.
     */
    public void addNote(Note note) {
        this.notes.add(note);
    }

    public Note getNote(Integer noteNumber) {
        return this.notes.get(noteNumber);
    }

}
