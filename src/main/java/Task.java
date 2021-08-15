/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * The Task class represents a task that has to be done, currently represented as a string.
 * It encapsulates the following information:
 * - description
 * - time
 * - isDone
 *
 * @author Lua Yi Da
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     *
     * @return String response of Side in response to marking a task as done.
     */
    public String markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return "Fine, I'll mark it for you: " + this.toString();
        } else {
            return "I'm lazy, stop making me mark the same things again...";
        }
    }

    @Override
    public String toString() {
        StringBuilder taskLine = new StringBuilder();
        if (this.isDone) {
            taskLine.append("[T][x]");
        } else {
            taskLine.append("[T][ ]");
        }
        taskLine.append(this.description.replaceFirst("todo", ""));
        return taskLine.toString();
    }
}
