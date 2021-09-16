package duke.task;

import duke.exception.DukeException;

import java.util.Arrays;
import java.util.List;

public class Task {
    private boolean isDone;
    private String taskSummary;

    /**
     * Constructor for Task class.
     * Protected access modifier so that extending classes can call it.
     *
     * @param taskSummary description of task
     */
    protected Task(String taskSummary) {
        this.taskSummary = taskSummary;
        this.isDone = false;
    }

    /**
     * Factory method of Task class.
     * Takes in a String, parses it and returns the Task it represented
     *
     * @param storageLine string representing task
     * @return Task which the string represented
     */
    public static Task parseLine(String storageLine) {
        String taskSymbol = Character.toString(storageLine.charAt(0));
        switch (taskSymbol) {
        case "T":
            return ToDo.parse(storageLine);
        case "E":
            return Event.parse(storageLine);
        case "D":
            return Deadline.parse(storageLine);
        default:
            throw new IllegalArgumentException("Should not enter here");
        }
    }

    /**
     * @return "✓" or "x" depending on whether task is complete
     */
    public String completeStatus() {
        return this.isDone ? "\u2713" : "\u2718";
    }

    //setter
    public void markCompleted() {
        if (this.isDone) {
            throw DukeException.of(this.toString(),"Task has already been marked as complete");
        }
        this.isDone = true;
    }

    //getters
    public boolean isCompleted() {
        return this.isDone;
    }

    public String getTaskSummary() {
        return this.taskSummary;
    }

    /**
     * Checks if keyword is in a task description.
     * @param keyword
     */
    public boolean isInTaskSummary(String keyword) {
        String[] keywords = this.taskSummary.split(" ");
        List<String> keywordList = Arrays.asList(keywords);
        return keywordList.contains(keyword);
    }

    /**
     * @return String storage format of Task instance
     */
    public String toStorageFormat() {
        return String.format(
            "? | %d | %s ",
            this.completeStatus(),
            this.getTaskSummary()
        );
    }

    @Override
    public String toString() {
        return String.format("[GENERIC TASK] [%s] %s", this.completeStatus(),this.taskSummary);
    }
}
