package duke.task;

public class DukeSimpleTask extends DukeTask {
    public DukeSimpleTask(String name) {
        super(name);
    }

    public DukeSimpleTask(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toSerializedString() {
        return String.format("%s/%d/simple", getName(), isDone() ? 1 : 0);
    }
}
