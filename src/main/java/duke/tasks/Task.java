package duke.tasks;

import java.time.LocalDate;

public abstract class Task implements Comparable<Task>{

    private String type;
    private String description;
    private boolean isDone;
    private String statusSymbol;

    public Task(String description, String type, boolean status) {
        this.description = description;
        this.isDone = status;
        this.statusSymbol = status ? "[X]" : "[ ]";
        this.type = type;
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
        this.statusSymbol = "[X]";
    }

    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns the data of the event separated be "|"
     *
     * @return formatted data
     */
    public abstract String getFormattedData();

    /**
     * Return the due date of the task
     *
     * @return due date
     */
    public abstract LocalDate getDueDate();

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public int compareTo(Task otherTask) {
        if (this == otherTask) {
            return 0;
        } else {
            return this.getDueDate().compareTo(otherTask.getDueDate());
        }
    }

    @Override
    public String toString() {
        assert isDone ? statusSymbol.equals("[X]") : statusSymbol.equals("[ ]");
        return this.type + this.statusSymbol + " " + this.description;
    }

}


