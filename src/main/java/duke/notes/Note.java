package duke.notes;

import duke.exceptions.NoteException;
import duke.exceptions.TodoException;
import duke.item.Item;

public class Note extends Item {
    private String description;

    public Note(String description) throws NoteException {
        if(description.equals("note")) {
            throw new NoteException();
        }
        this.description = description;
    }

    @Override
    public String storeItem() {
        return "N/" + description;
    }

    public String toString() {
        return this.description;
    }
}
