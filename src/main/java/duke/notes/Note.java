package duke.notes;

import duke.exceptions.NoteException;
import duke.item.Item;

/**
 * Represents a Note that is added to the task manager application.
 */
public class Note extends Item {
    private String description;

    /**
     * Instantiates an object of the Note class.
     *
     * @param description Description of the Note to be added to the task manager applciation.
     * @throws NoteException If the description of the note to be added is empty.
     */
    public Note(String description) throws NoteException {
        if(description.equals("note")) {
            throw new NoteException();
        }
        this.description = description;
    }

    /**
     * Stores the note to the hard disk of the task manager application.
     *
     * @return String representation of the note to be stored in the hard disk.
     */
    @Override
    public String storeItem() {
        return "N/" + description;
    }

    /**
     * Returns the String representation of the note.
     *
     * @return String representation of the note created.
     */
    public String toString() {
        return this.description;
    }
}
