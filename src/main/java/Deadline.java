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
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder deadlineLine = new StringBuilder();
        if (this.isDone) {
            deadlineLine.append("[D][x]");
        } else {
            deadlineLine.append("[D][ ]");
        }
        deadlineLine.append(this.description.replaceFirst("deadline", "") + "(by:" + this.time + ")");
        return deadlineLine.toString();
    }
}
