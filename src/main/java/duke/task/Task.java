package duke.task;

import duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class represents a task to be done in the TaskList.
 */
public class Task {
    private String taskName;
    private boolean isDone;
    private boolean isTagged;
    private ArrayList<String> tags;

    /**
     * Constructor for a task, which takes in a task name.
     *
     * @param taskName name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.isTagged = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Gives the type of the Task (i.e Event, ToDo, Deadline). To be overriden.
     *
     * @return empty string
     */
    public String getType() {
        return "";
    }

    /**
     * Gives the description of the task.
     *
     * @return description of the task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Shows the state of the task.
     *
     * @return the state of the task
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Gives a save-file friendly version of the task. To be overriden in child classes.
     *
     * @return save information
     */
    public String getSaveInfo() {
        String info = this.getType() + " | " + (isDone ? "1 | " : "0 | ")
                + (isTagged ? "1 | " : "0 | ") + taskName + "| ";
        if (isTagged) {
            for (String tag: tags) {
                info += "#" + tag;
            }
        }
        return info;
    }

    /**
     * Marks the task as done.
     *
     * @param shouldPrint determines if the task being marked as done should be printed.
     * @return output
     */
    public String doneTask(boolean shouldPrint) {
        this.isDone = true;
        String output = null;
        if (shouldPrint) {
            output = Ui.finishTask(this);
        }
        return output;
    }

    /**
     * Returns whether the name contains the keyword. Note this matches the keyword with anything infront
     * and behind it. (e.g. four in fourteen will return true)
     *
     * @param keyword to be searched in the task name.
     * @return true if the task name contains the keyword, false if not.
     */
    public boolean doesNameContain(String keyword) {
        return taskName.matches("(.*)" + keyword + "(.*)");
    }

    /**
     * Adds to the list of tags for the task
     *
     * @param tag tag to be added
     */
    public void tag(String tag) {
        if (!isTagged) {
            isTagged = true;
        }
        tags.add(tag);
    }

    /**
     * Overriden toString method.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        String str = (isDone ? "[X] " : "[ ] ") + this.taskName;
        if (tags.size() == 0) {
            return str;
        }
        str += " (tags:";
        for (String tag: tags) {
            str += " #" + tag;
        }
        str += ")";
        return str;
    }

    /**
     * Method overloaded toString method for subclasses to use.
     *
     * @param date date
     * @param type type of subclass
     * @return String representation
     */
    public String toString(LocalDateTime date, String type) {
        String str = (isDone ? "[X] " : "[ ] ") + this.taskName;
        if (tags.size() == 0) {
            return str;
        }
        str += "(" + type + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        str += " (tags:";
        for (String tag: tags) {
            str += " #" + tag;
        }
        str += ")";
        return str;
    }
}
