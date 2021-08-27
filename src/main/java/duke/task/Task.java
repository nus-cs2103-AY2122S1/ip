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
    private boolean completed;


    public Task(String input) {
        this.title = input.trim();
        this.completed = false;
    }

    /**
     * Completes the tasl by setting completed to true
     *
     * @return String that states that the task has been completed
     */
    public String completeItem() {
        this.completed = true;
        return "Nice I've marked this task as done!\n" + this.toString();
    }

    /**
     * @return formatted string for uploading to the database
     */
    public String formatTask() {
        int complete = completed ? 1 : 0;
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

    @Override
    public String toString() {
        String complete = this.completed ? "[X]" : "[ ]";
        return complete + " " + this.title + " ";
    }
}
