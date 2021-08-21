/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * The Deadline class represents a task that has to be done before a specified time, currently represented
 * as a string. It encapsulates the following information:
 * - description
 * - time
 * - isDone
 *
 * @author Lua Yi Da
 */

public class Deadline extends Task {
    protected DateTime datetime;

    public Deadline(String description, String datetimeInput) {
        super(description);

        String[] datetime = datetimeInput.trim().split(", ", 2);
        if (datetime.length == 2) {
            this.datetime = new DateTime(datetime[0], datetime[1]);
        } else {
            this.datetime = new DateTime(datetime[0]);
        }
    }

    public Deadline(String description, String datetimeInput, Boolean isDone) {
        super(description, isDone);

        String[] datetime = datetimeInput.split(", ", 2);
        if (datetime.length == 2)
            this.datetime = new DateTime(datetime[0], datetime[1]);
        else
            this.datetime = new DateTime(datetime[0]);
    }

    @Override
    public String toString() {
        StringBuilder deadlineLine = new StringBuilder();
        if (this.isDone) {
            deadlineLine.append("[D][x] ");
        } else {
            deadlineLine.append("[D][ ] ");
        }
        String deadlineDetails = this.description.replaceFirst("deadline", "") +
                "(by: " + this.datetime + ")";
        deadlineLine.append(deadlineDetails);
        return deadlineLine.toString();
    }
}
