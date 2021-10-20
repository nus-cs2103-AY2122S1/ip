package jackie.task;

import java.util.ArrayList;
import java.util.Arrays;

import jackie.JackieException;

/**
 * Abstract class that is extended by Deadline, Event and Todo.
 * It contains methods to obtain/alter information about the Task object.
 *
 * @author Gu Geng
 */
public abstract class Task {
    private String content;
    private boolean ifCompleted;

    /**
     * Initialises the Task with the given information.
     *
     * @param content A String that contains information that can possibly create a Task Object.
     */
    Task(String... content) {
        // manipulate the array given to get the task content
        ArrayList<String> holder = new ArrayList<>(Arrays.asList(content));
        // remove the first element which is indicative of command type
        holder.remove(0);
        holder.trimToSize();
        // dateMarkerIndex = -1 if not found
        int dateMarkIndex = holder.indexOf("/");
        int contentEndIndex = dateMarkIndex == -1 ? holder.size() : dateMarkIndex;
        String taskContent = holder.subList(0, contentEndIndex)
                .stream().reduce("", (a, b) -> a + " " + b).trim();

        this.content = taskContent;
        ifCompleted = false;
    }

    public String getContent() {
        return content;
    }

    public boolean getStatus() {
        return ifCompleted;
    }

    /**
     * Sets the Task as done.
     */
    public void doneTask() {
        ifCompleted = true;
    }

    /**
     * Overrides the toString method.
     *
     * @return A String representation of the Task object in specified format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                ifCompleted ? "x" : " ", content);
    }

    public abstract String record();
    public abstract boolean hasSchedule();
    public abstract String getType();
    public abstract String getTime();
    public abstract <T extends Task> T getCopy() throws JackieException;

}
