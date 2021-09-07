package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    private String description;
    private LocalDateTime createdDateTime;

    /**
     * Class constructor.
     *
     * @param description The description of the Note.
     * @param createdDateTime The LocalDateTime of when the Note was created.
     */
    public Note(String description, LocalDateTime createdDateTime) {
        this.description = description;
        this.createdDateTime = createdDateTime;
    }

    public String getNoteDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a");
        return this.description + " | created: " + this.createdDateTime.format(dtf);
    }
}
