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
     * @return the noteList
     */
    public static ArrayList<Note> getNoteList() {
        return NoteList.noteList;
    }

    /**
     * Returns the number of notes currently save in user's hard disk
     * @return the number of notes currently save in user's hard disk (counter member)
     */
    public static int getCounter() {
        return NoteList.counter;
    }

    /**
     * Adds a note to the noteList
     * @param note the note that is to be added to the noteList
     */
    public static void addNote(Note note) {
        NoteList.noteList.add(note);
        NoteList.counter++;
    }

    /**
     * Adds a note to the noteList and update the list of notes in user's hard disk
     * @param currNote the note that is to be added
     * @throws IOException if there is an error in appending the note to the list of notes
     * in user's hard disk
     */
    public static void addNoteAndUpdate(Note currNote) throws IOException {
        NoteList.noteList.add(currNote);
        NoteList.counter++;
        appendToFile("data/notes.txt", (counter) + "." +
                NoteList.getNoteList().get(counter - 1).toPrintToFile()
                + System.lineSeparator());
    }

    /**
     * Deletes a note to the noteList and update the list of notes in user's hard disk
     * @param currNote the note that is to be deleted
     * @throws IOException if there is an error in re-writing the list of notes without the
     * deleted note
     */
    public static void deleteNoteAndUpdate(Note currNote) throws IOException {
        NoteList.noteList.remove(currNote);
        counter--;
        if (NoteList.getNoteList().size() == 0) {
            writeToFile("data/notes.txt", "");
        } else {
            for (int i = 0; i < NoteList.getNoteList().size(); i++) {
                if (i == 0) {
                    writeToFile("data/notes.txt", (i + 1) + "." +
                            NoteList.getNoteList().get(i).toPrintToFile()
                            + System.lineSeparator());
                } else {
                    appendToFile("data/notes.txt", (i + 1) + "." +
                            NoteList.getNoteList().get(i).toPrintToFile()
                            + System.lineSeparator());
                }
            }
        }
    }

    /**
     * Writes to the file in user's hard disk that stores a list of notes or to overwrite
     * the contents of this file
     * @param filePath the relative path to the file
     * @param textToAdd the content that is to be written
     * @throws IOException if there is an error in writing to/overwriting the file
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends content to the file in user's hard disk that stores a list of notes
     * @param filePath the relative path to the file
     * @param textToAppend the content that is to be appended
     * @throws IOException if there is an error in appending to existing content of the file
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
