package duke;

import java.util.ArrayList;

/**
 * Encapsulates a list of Notes that the user n, with relevant processing methods.
 *
 * @author Hanif Kamal
 */
public class NoteList {
    private final ArrayList<Note> list;

    /**
     * Class constructor to initialize the NoteList instance.
     */
    public NoteList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds the given Note to the NoteList.
     *
     * @param note The Note to be added to the NoteList.
     * @return A String that acts as a confirmation message by Duke for a successfully added Note.
     */
    public String addToList(Note note) {
        this.list.add(note);
        assert !list.isEmpty() : "The Note did not get added!";
        return ("Got it. I've added this note:\n" + "  " + note.toString() + "\n"
                + "Now you have " + list.size() + " note" + (list.size() == 1 ? "" : "s")
                + " in the list.");
    }

    /**
     * Returns, as a String, all Notes in the NoteList for the user.
     *
     * @return A String that lists out all the Notes in the NoteList.
     * @throws DukeException In the case where the NoteList is empty.
     */
    public String printNotes() throws DukeException {
        if (this.list.size() < 1) {
            throw new DukeException("You haven't added any notes to the list yet! Please add a note.");
        } else {
            assert !list.isEmpty() : "NoteList should not be empty, tried to print!";
            String noteQuantifier;
            if (this.list.size() == 1) {
                noteQuantifier = "Here is the sole note in your list:";
            } else {
                noteQuantifier = "Here are the notes in your list:";
            }
            String notes = "";
            for (int i = 1; i <= list.size(); i++) {
                notes = notes + ("\n" + i + ". " + list.get(i - 1));
            }
            return noteQuantifier + notes;
        }
    }

    /**
     * Looks up the Note at the given index in the NoteList and removes the Note from the list.
     *
     * @param index The index of the Note, to be deleted, in the NoteList.
     * @return A String that acts as a confirmation message by Duke for a successfully deleted Note.
     * @throws DukeException In the case where the NoteList is empty, or the index is out of bounds.
     */
    public String deleteNote(int index) throws DukeException {
        if (this.list.size() < 1) {
            throw new DukeException("You haven't added any notes to the list yet! Please add notes before"
                    + " deleting.");
        } else if (index <= this.list.size() && index >= 1) {
            assert !list.isEmpty() : "NoteList should not be empty, tried to delete!";
            Note toDelete = list.get(index - 1);
            list.remove(index - 1);
            return ("Noted. I've removed this note:\n" + "  " + toDelete + "\n" + "Now you have "
                    + list.size() + " note" + (list.size() == 1 ? "" : "s") + " in the list.");
        } else {
            throw new DukeException("Couldn't find that note in the list! Please ensure that the index is valid. "
                    + "Try again.");
        }
    }


    public int size() {
        return this.list.size();
    }

    public Note getNote(int index) {
        return this.list.get(index);
    }

    /**
     * Prints a NoteList that is filtered such that it only consists of Notes that contain the given search term.
     *
     * @param searchTerm The given search term to narrow down the Notes in the NoteList.
     * @return A String that lists out all the Notes that match the search term in the NoteList.
     * @throws DukeException In the case where no Notes matching the search term can be found.
     */
    public String printFilteredNotes(String searchTerm) throws DukeException {
        ArrayList<Note> filteredList = new ArrayList<>();
        for (Note note : list) {
            if (note.getNoteDescription().contains(searchTerm)) {
                filteredList.add(note);
            }
        }
        if (filteredList.size() < 1) {
            throw new DukeException("I couldn't find any notes with that particular search term. Please try again.");
        } else {
            assert !filteredList.isEmpty() : "Filtered NoteList should not be empty, tried to print!";
            assert filteredList.size() <= list.size() : "Filtered NoteList should not be larger than original list!";
            String filteredNoteQuantifier;
            if (filteredList.size() == 1) {
                filteredNoteQuantifier = "Here is the sole matching note in your list:";
            } else {
                filteredNoteQuantifier = "Here are the matching notes in your list:";
            }
            String filteredNotes = "";
            for (int i = 1; i <= list.size(); i++) {
                filteredNotes = filteredNotes + ("\n" + i + ". " + filteredList.get(i - 1));
            }
            return filteredNoteQuantifier + filteredNotes;
        }
    }

}

