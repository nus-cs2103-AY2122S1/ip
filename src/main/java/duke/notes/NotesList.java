package duke.notes;

import duke.notes.Note;

import java.util.ArrayList;

public class NotesList {
    private ArrayList<Note> notes;

    public NotesList(ArrayList<Note> notes) {
        this.notes = notes;
    }
    public Integer getNumberOfNotes() {
        return this.notes.size();
    }
    public ArrayList<Note> getNotes() {
        return this.notes;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void removeNote(Integer noteNumber) {
        this.notes.remove(noteNumber);
    }

    public Note getNote(Integer noteNumber) {
        return this.notes.get(noteNumber);
    }
}
