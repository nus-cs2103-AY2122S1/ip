package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the note list and all relevant list methods
 */
public class NoteList {
    private static ArrayList<Note> noteList;
    private static int counter;

    /**
     * Creates the noteList and a counter to keep track of the number of notes saved in the user's
     * hard disk at any point in time
     */
    public NoteList() {
        noteList = new ArrayList<Note>(100);
        counter = 0;
    }

    /**
     * Returns the noteList
     *
     * @return the noteList
     */
    public static ArrayList<Note> getNoteList() {
        return NoteList.noteList;
    }

    /**
     * Returns the number of notes currently save in user's hard disk
     *
     * @return the number of notes currently save in user's hard disk (counter member)
     */
    public static int getCounter() {
        return NoteList.counter;
    }

    /**
     * Adds a note to the noteList
     *
     * @param note the note that is to be added to the noteList
     */
    public static void addNote(Note note) {
        NoteList.noteList.add(note);
        NoteList.counter++;
    }

    /**
     * Adds a note to the noteList and update the list of notes in user's hard disk
     *
     * @param currNote the note that is to be added
     * @throws IOException if there is an error in appending the note to the list of notes
     * in user's hard disk
     */
    public static void addNoteAndUpdate(Note currNote) throws IOException {
        NoteList.noteList.add(currNote);
        NoteList.counter++;
        Storage.appendToNoteFile();
    }

    /**
     * Deletes a note to the noteList and update the list of notes in user's hard disk
     *
     * @param currNote the note that is to be deleted
     * @throws IOException if there is an error in re-writing the list of notes without the
     * deleted note
     */
    public static void deleteNoteAndUpdate(Note currNote) throws IOException {
        NoteList.noteList.remove(currNote);
        counter--;
        Storage.rewriteNoteFile();
    }

}
