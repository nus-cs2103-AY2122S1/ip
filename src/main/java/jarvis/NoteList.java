package jarvis;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the note list and all relevant list methods
 */
public class NoteList {
    private static ArrayList<Note> noteList; // The array list containing all the user's notes
    private static int counter; // Keeps track of the total number of tasks

    /**
     * Creates the noteList and a counter to keep track of the number of notes saved in the user's
     * hard disk at any point in time
     */
    public NoteList() {
        noteList = new ArrayList<Note>(100);
        counter = 0;
    }

    /**
     * Returns the noteList.
     *
     * @return the noteList
     */
    public static ArrayList<Note> getNoteList() {
        return NoteList.noteList;
    }

    /**
     * Returns the number of notes currently save in user's hard disk.
     *
     * @return the number of notes currently save in user's hard disk (counter member)
     */
    public static int getCounter() {
        return NoteList.counter;
    }

    /**
     * Adds a note to the noteList and increments the total number of notes by 1.
     *
     * @param note the note that is to be added to the noteList
     */
    public static void addNote(Note note) {
        NoteList.noteList.add(note);
        NoteList.counter++;
    }

    /**
     * Adds a note to the noteList and update the list of notes in user's hard disk.
     *
     * @param currNote the note that is to be added
     * @throws IOException if there is an error in appending the note to the list of notes
     * in user's hard disk
     */
    public static String addNoteAndUpdate(Note currNote) throws IOException {
        // Add a note to the noteList and increments the total number of notes by 1
        NoteList.noteList.add(currNote);
        NoteList.counter++;

        Storage.appendToNoteFile(); // Add the new note to the note file in user's hard disk

        return Ui.noteAdded(currNote); // Display Jarvis' response after adding the note
    }

    /**
     * Deletes a note to the noteList and update the list of notes in user's hard disk.
     *
     * @param noteNum the index of the note that is to be deleted
     * @throws IOException if there is an error in re-writing the list of notes without the
     * deleted note
     */
    public static String deleteNoteAndUpdate(int noteNum) throws IOException {
        // Delete the note from the noteList and decrement the total number of notes by 1
        Note currNote = NoteList.getNoteList().get(noteNum);
        NoteList.getNoteList().remove(currNote);
        NoteList.counter--;

        Storage.rewriteNoteFile(); // Delete the new note from the note file in user's hard disk

        return Ui.noteDeleted(currNote); // Display Jarvis' response after deleting the note
    }

}
