package duke.task;

import java.time.LocalDate;
public class Task {
    /**
     * title of task.
     */
    private String title;
    /**
     * boolean showing whether the task has been completed.
     */
    private boolean isCompleted;


    /**
     * Constructor for Task object.
     *
     * @param input title of task.
     */
    public Task(String input) {
        this.title = input.trim();
        this.isCompleted = false;
    }

    /**
     * Completes the task by setting completed to true
     *
     * @return String that states that the task has been completed
     */
    public String completeItem() {
        this.isCompleted = true;
        return "Nice! I've marked this task as done:\n" + this.toString();
    }

    /**
     * @return formatted string for uploading to the database
     */
    public String formatTask() {
        int complete = isCompleted ? 1 : 0;
        return complete + " | " + this.title;
    }

    /**
     * Checks if the input date is the same as this task's
     *
     * @param compare Date to compare.
     * @return boolean value of whether the date is the same.
     */
    public boolean compareDate(LocalDate compare) {
        return false;
    }

    public boolean compareKeyword(String keyword) {
        return this.title.contains(keyword);
    }

    @Override
    public String toString() {
        String complete = this.isCompleted ? "[X]" : "[ ]";
        return complete + " " + this.title + " ";
    }
}
