package duke.data.tasks;

public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    public ToDos(boolean completed, String name) {
        super(completed, name);
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return "T~1~" + this.getName();
        } else {
            return "T~0~" + this.getName();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
