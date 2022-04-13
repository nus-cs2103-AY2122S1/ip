package task;

import java.time.format.DateTimeFormatter;

import bot.Tag;
import bot.TagList;

/**
 * A class that encapsulates a general Task stored by Duke.
 */
public class Task {

    protected String task;
    protected boolean isDone;
    protected TagList tags;
    protected DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy - hh:mm a");

    /**
     * Constructor for the Task class.
     *
     * @param task The task to be stored within this Task object.
     */
    public Task(String task) {
        assert !task.trim().equals("");
        this.task = task;
        this.isDone = false;
        this.tags = new TagList();
    }

    /**
     * Constructor for the Task class.
     *
     * @param task The task to be stored within this Task object.
     */
    public Task(String task, String tags) {
        assert !task.trim().equals("");
        this.task = task;
        this.isDone = false;
        this.tags = new TagList();

        String[] rawTags = tags.split(" ");
        for (int i = 0; i < rawTags.length; i++) {
            this.tags.addTag(new Tag(rawTags[i]));
        }
    }

    /**
     * Constructor for the Task class.
     * This constructor is invoked when reading from the local data, in order to show the correct Task state.
     *
     * @param task The task to be stored within this Task object.
     * @param isDone The state of the Task object.
     */
    public Task(String task, String tags, boolean isDone) {
        assert !task.trim().equals("");
        this.task = task;
        this.isDone = isDone;
        this.tags = new TagList();

        String[] rawTags = tags.split(" ");
        for (int i = 0; i < rawTags.length; i++) {
            this.tags.addTag(new Tag(rawTags[i]));
        }
    }

    /**
     * Adds the given Task to the end of the TaskList.
     *
     * @param tag The String to be added to TaskList as a new Tag.
     */
    public void addTag(String tag) {
        this.tags.addTag(new Tag(tag));
    }

    /**
     * Returns the String representation of the Task object, showing the state and the task.
     *
     * @return A String enumerating this Task object.
     */
    public String getTaskState() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

    /**
     * Returns the task currently stored within this Task object.
     *
     * @return A String corresponding to the task stored in this Task object.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns the type of this Task object.
     *
     * @return A String corresponding to the type of this Task object.
     */
    public String getTaskType() {
        return "task";
    }

    /**
     * Sets the current task state as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the Task object that has been parsed from the given string.
     * A static method.
     *
     * @param task The String serialisation of the task from local data.
     * @return A new Task object initialised from given local data.
     */
    public static Task parseFromStorage(String task) {
        System.out.println(task);
        String[] splitTask = task.split(",");
        System.out.println(splitTask.length);
        String taskType = splitTask[0];

        switch (taskType) {
        case "T":
            return new TodoTask(splitTask[2], splitTask[3], splitTask[1].equals("1"));
        case "D":
            return new DeadlineTask(splitTask[2], splitTask[4], splitTask[3], splitTask[1].equals("1"));
        case "E":
            return new EventTask(splitTask[2], splitTask[4], splitTask[3], splitTask[1].equals("1"));
        default:
            return new Task("");
        }
    }

    /**
     * Returns the storage format of this Task object.
     *
     * @return A String representation of the Task object, formatted for storage inside local hard disk.
     */
    public String convertToStorageFormat() {
        return "";
    }
}
