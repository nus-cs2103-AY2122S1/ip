package duke.task;

import java.time.LocalDate;
public class Task {
    private String title;
    private boolean completed;

    public Task(String input) {
        this.title = input.trim();
        this.completed = false;
    }

    public String completeItem() {
        this.completed = true;
        return "Nice I've marked this task as done!\n" + this.toString();
    }

    public String formatTask() {
        int complete = completed ? 1 : 0;
        return complete + " | " + this.title;
    }

    public boolean compareDate(LocalDate compare) {
        return false;
    }

    public boolean compareKeyword(String keyword) {
        return this.title.contains(keyword);
    }

    @Override
    public String toString() {
        String complete = this.completed ? "[X]" : "[ ]";
        return complete + " " + this.title + " ";
    }
}
