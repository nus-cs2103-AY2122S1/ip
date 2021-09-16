package duke.data.tasks;

import java.util.HashSet;

/**
 * Represents a task, which has a description, a completion status and an ID.
 */
public abstract class Task implements Comparable<Task> {
    private static int count = 0;
    private boolean completed;
    private final String name;
    private final int id;
    private final HashSet<String> tags;

    public Task(String name) {
        this.name = name;
        this.id = count++;
        this.completed = false;
        this.tags = new HashSet<>();
    }

    public Task(boolean completed, String name) {
        this.name = name;
        this.id = count++;
        this.completed = completed;
        this.tags = new HashSet<>();
    }

    public Task(boolean completed, String name, String tags) {
        this.name = name;
        this.id = count++;
        this.completed = completed;
        this.tags = new HashSet<>();

        this.setTagsFromString(tags);
    }

    public String getName() {
        return this.name;
    }

    public String completeTask() {
        this.completed = true;
        return this.toString();
    }

    protected boolean isCompleted() {
        return this.completed;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public boolean hasTag(String tag) {
        return this.tags.contains(tag);
    }

    public String getTags() {
        StringBuilder sb = new StringBuilder();
        for (String tag : this.tags) {
            sb.append(tag).append("`");
        }

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public void setTagsFromString(String input) {
        String[] tags = input.split("`");
        for (String tag : tags) {
            this.addTag(tag);
        }
    }

    public abstract String getSaveData();

    @Override
    public String toString() {
        if (this.completed) {
            return String.format("[X] %s %s", this.name, this.tags);
        } else {
            return String.format("[ ] %s %s", this.name, this.tags);
        }
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public abstract boolean equals(Object o);
}
