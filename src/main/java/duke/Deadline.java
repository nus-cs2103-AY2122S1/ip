package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    private LocalDate deadline;

    /**
     *
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.deadline = LocalDate.parse(by);
        this.by = by;
    }

    /**
     *
     * @return
     */
    @Override
    public char getTaskType() { return 'D'; }

    /**
     *
     * @return
     */
    @Override
    public String getTime() {
        return this.by;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String formattedDate = this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "  [D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
