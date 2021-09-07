package duke.notes;

public class Note {
    private String description;

    public Note(String description) {
        this.description = description;
    }

    public String storeNote() {
        return "N/" + description;
    }

    public String toString() {
        return this.description;
    }
}
