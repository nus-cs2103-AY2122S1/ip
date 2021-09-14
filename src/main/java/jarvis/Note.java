package jarvis;

/**
 * Class to create notes
 */
public class Note {
    private String title;
    private String body;

    /**
     * Creates a note.
     *
     * @param title the title of the note
     * @param body the content to be stored in the note
     */
    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    /**
     * Returns the note as a string that is to be displayed to the user.
     *
     * @return the given note as a string that is to be displayed to the user
     */
    public String toString() {
        return this.title + ": " + this.body;
    }

    /**
     * Returns the note as a string that is to be appended to the contents of the list of
     * notes in user's hard disk.
     *
     * @return the note as a string that is to be appended to the contents of the list of
     * notes in user's hard disk
     */
    public String toPrintToFile() {
        return this.title + ": " + this.body;
    }
}
