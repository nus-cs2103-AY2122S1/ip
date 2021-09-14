package duke.notes;

import java.util.ArrayList;

public class NotesList {
    private ArrayList<Note> notes;

    public NotesList(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<Note> getNotes() {
        return this.notes;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

}
