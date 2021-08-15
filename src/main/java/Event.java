/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * The Event class represents a task that has to be done at a specified time, currently represented
 * as a string. It encapsulates the following information:
 * - description
 * - time
 * - isDone
 *
 * @author Lua Yi Da
 */

public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder deadlineLine = new StringBuilder();
        if (this.isDone) {
            deadlineLine.append("[E][x]");
        } else {
            deadlineLine.append("[E][ ]");
        }
        deadlineLine.append(this.description.replaceFirst("event", "") + "(at:" + this.time + ")");
        return deadlineLine.toString();
    }
}
