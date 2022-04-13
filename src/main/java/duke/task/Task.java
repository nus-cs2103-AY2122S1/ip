package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Task {
    public static final String DONE_STATUS_INDICATOR = "X";
    private final String name;
    private final boolean isDone;
    private final List<String> tags;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.tags = new ArrayList<String>();
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.tags = new ArrayList<String>();
    }

    public Task(String name, boolean isDone, List<String> tags) {
        this.name = name;
        this.isDone = isDone;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns DONE_STATUS_INDICATOR if task is done, and " " otherwise.
     *
     * @return DONE_STATUS_INDICATOR if task is done.
     */
    public String getStatusIcon() {
        return isDone ? DONE_STATUS_INDICATOR : " ";
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    String printTags() {
        return String.format("(tags: %s)",
                tags.stream().map(tag -> "#" + tag).collect(Collectors.joining(" ")));
    }

    /**
     * Returns a Task that is marked as done.
     *
     * @return a Task that is marked as done.
     */
    public abstract Task markAsDone();

    public String toString() {
        String tags = this.tags.size() == 0 ? "" : String.format("%s", printTags());
        return String.format("[%s] %s %s", getStatusIcon(), name, tags);
    }
}
