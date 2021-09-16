package duke.notes;

import duke.item.Item;

public class Note extends Item {
    private String description;

    public Note(String description) {
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
