package task;

import ui.Ui;

import java.util.ArrayList;

/**
 * Encapsulates the creation of tasks found in the to-do list
 *
 * @author: Wei Yangken
 */

public class Task {
    private String name;
    private boolean isDone;
    private String taskCat;
    private ArrayList<Tag> tags;

    /**
     * Construct a generic task yet to be completed
     * @param name The name of the task
     */
    public Task(String name, String taskCat, boolean isDone, ArrayList<Tag> tags) {
        this.name = name.trim();
        assert(name != "");
        this.isDone = isDone;
        this.taskCat = taskCat;
        this.tags = tags;
    }

    public Task(String name, String taskCat, boolean isDone) {
        this.name = name.trim();
        assert(name != "");
        this.isDone = isDone;
        this.taskCat = taskCat;
        this.tags = new ArrayList<>();
    }

    /**
     * @return Category of task
     */
    public String getTaskCat() {
        return taskCat;
    }

    /**
     * @return The name of the task
     */
    public String getName() {
        return this.name.trim();
    }

    /**
     * @return Whether the task has been completed
     */
    public boolean isDone() {
        return this.isDone;
    }

    public void setToCompleted() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        Ui.printBreakline();
    }

    /**
     * @return Name of task
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * @return Gets additional details of task
     */
    public String getDetail() {
        return "";
    }

    /**
     * @return List of tags
     */
    public ArrayList<Tag> getTagList() {
        return this.tags;
    }

    /**
     * @return Whether the two task share the same name
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task task = (Task) o;
        // Compare the data members and return accordingly
        return this.name.equals(task.getName());
    }

    public void addTag(String tagName) {
        System.out.println("Tag added successfully");
        this.tags.add(new Tag(tagName));
        System.out.println("Current tags: " + viewTags());
        Ui.printBreakline();
    }

    public String viewTags() {
        String allTags = "";
        for(int i = 0; i < tags.size(); i++) {
            allTags += tags.get(i) + " ";
        }
        return allTags;
    }

}
